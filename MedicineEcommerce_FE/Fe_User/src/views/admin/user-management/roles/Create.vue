<script setup>
import { useRoleStore } from '@/stores/role';
import { required } from '@/utils/validators';
import { useVuelidate } from '@vuelidate/core';
import { storeToRefs } from 'pinia';
import { computed } from "vue";

const props = defineProps({ visible: Boolean})
const roleStore = useRoleStore()
const { role, form, errors } = storeToRefs(roleStore)
const formVisible = computed(()=>props.visible)
const title = computed(()=>role.value.id?'Cập nhật vai trò' : 'Tạo mới vai trò')
const status = [
  { key: 1, name: 'Hoạt động' },
  { key: 0, name: 'Không hoạt động' },
];
const rules = {
  role: {
    name: { required },
    active: { required },
    description: { required },
  }
}
const v$ = useVuelidate(rules, {role}, { $autoDirty: true, $externalResults: errors})

function onCancel() {
  roleStore.$patch({ form: { visible: false } })
}
function onHide() {
  roleStore.resetForm();
  v$.value.$reset()
}
function onShow() {
  if (role.value.id) {
    roleStore.edit(1, role.value.id)
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
    ...role.value,
    permissions: []
  };

  if (!role.value.id) roleStore.store(payload);
  else roleStore.update(payload);
}
</script>
<template>
  <FormDialog :visible="formVisible" :title="title" :loading="form.isLoading" @save="onSave" @cancel="onCancel"
    @hide="onHide" @show="onShow" :style="{ width: '40rem' }">
    <div class="flex flex-col md:flex-col gap-4">
      <div class="flex flex-col gap-1 w-full">
        <label for="name">Tên vai trò</label>
        <div class="flex flex-col gap-1">
          <InputText autoFocus id="name" v-model="role.name" placeholder="Nhập tên vai trò" class="w-full"
            :invalid="v$.role.name.$error" />
          <Message v-if="v$.role.name.$error"
            v-for="(error, index) of v$.role.name.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
      <div class="flex flex-col gap-2">
        <label for="active">Trạng thái</label>
        <div class="flex flex-col md:flex-row gap-4">
          <div class="flex items-center gap-2" v-for="statusItem in status" :key="statusItem">
            <RadioButton v-model="role.active" :value="statusItem.key" />
            <label for="key">{{ statusItem.name }}</label>
          </div>
        </div>
        <Message v-if="v$.role.active.$error"
          v-for="(error, index) of v$.role.active.$errors" :key="index" severity="error" size="small"
          variant="simple">
          {{ error.$message }}
        </Message>
      </div>
      <div class="flex flex-col gap-1 w-full">
        <label for="description">Ghi chú</label>
        <div class="flex flex-col gap-1">
          <Textarea  autoFocus id="description" v-model="role.description" placeholder="Nhập ghi chú vai trò" class="w-full"
            :invalid="v$.role.description.$error" ></Textarea>
          <Message v-if="v$.role.description.$error"
            v-for="(error, index) of v$.role.description.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
    </div>
  </FormDialog>
</template>