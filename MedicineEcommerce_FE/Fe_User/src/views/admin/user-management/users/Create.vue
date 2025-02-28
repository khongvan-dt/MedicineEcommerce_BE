<script setup>
import { useUserStore } from '@/stores/user';
import { required } from '@/utils/validators';
import { useVuelidate } from '@vuelidate/core';
import { storeToRefs } from 'pinia';
import { computed } from "vue";

const props = defineProps({ visible: Boolean})
const userStore = useUserStore()
const { user, form, errors } = storeToRefs(userStore)
const formVisible = computed(()=>props.visible)
const title = computed(()=>user.value.id?'Cập nhật người dùng' : 'Tạo mới người dùng')
const status = [
  { key: 1, name: 'Hoạt động' },
  { key: 0, name: 'Không hoạt động' },
];
const rules = {
  user: {
    name: { required },
    active: { required },
    description: { required },
  }
}
const v$ = useVuelidate(rules, {user}, { $autoDirty: true, $externalResults: errors})

function onCancel() {
  userStore.$patch({ form: { visible: false } })
}
function onHide() {
  userStore.resetForm();
  v$.value.$reset()
}
function onShow() {
  if (user.value.id) {
    userStore.edit(1, user.value.id)
  }
}
async function onSave(event) {
  const isFormCorrect = await v$.value.$validate();
  if (!isFormCorrect) {
    const elms = document.querySelectorAll('.p-invalid');
    elms[0].scrollIntoView({ behavior: 'smooth' });
    return;
  }

  const payload = {
    ...user.value,
    permissions: []
  };

  if (!user.value.id) userStore.store(payload);
  else userStore.update(payload);
}
</script>
<template>
  <FormDialog :visible="formVisible" :title="title" :loading="form.isLoading" @save="onSave" @cancel="onCancel"
    @hide="onHide" @show="onShow" :style="{ width: '40rem' }">
    <div class="flex flex-col md:flex-col gap-4">
      <div class="flex flex-col gap-1 w-full">
        <label for="name">Tên vai trò</label>
        <div class="flex flex-col gap-1">
          <InputText autoFocus id="name" v-model="user.name" placeholder="Nhập tên vai trò" class="w-full"
            :invalid="v$.user.name.$error" />
          <Message v-if="v$.user.name.$error"
            v-for="(error, index) of v$.user.name.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
      <div class="flex flex-col gap-2">
        <label for="active">Trạng thái</label>
        <div class="flex flex-col md:flex-row gap-4">
          <div class="flex items-center gap-2" v-for="statusItem in status" :key="statusItem">
            <RadioButton v-model="user.active" :value="statusItem.key" />
            <label for="key">{{ statusItem.name }}</label>
          </div>
        </div>
        <Message v-if="v$.user.active.$error"
          v-for="(error, index) of v$.user.active.$errors" :key="index" severity="error" size="small"
          variant="simple">
          {{ error.$message }}
        </Message>
      </div>
      <div class="flex flex-col gap-1 w-full">
        <label for="description">Ghi chú</label>
        <div class="flex flex-col gap-1">
          <Textarea  autoFocus id="description" v-model="user.description" placeholder="Nhập ghi chú vai trò" class="w-full"
            :invalid="v$.user.description.$error" ></Textarea>
          <Message v-if="v$.user.description.$error"
            v-for="(error, index) of v$.user.description.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
    </div>
  </FormDialog>
</template>