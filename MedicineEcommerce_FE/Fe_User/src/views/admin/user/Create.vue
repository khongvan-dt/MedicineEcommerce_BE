<script>
import { ref } from "vue";
import { useOrganizationStore } from '@/stores/organization';
import { required, requiredIf } from '@/utils/validators';
import { useVuelidate } from '@vuelidate/core';
import { storeToRefs, mapState } from 'pinia';
import FormDialog from '@/components/FormDialog.vue';

export default {
  props: {
    visible: {
      type: Boolean,
      default: false
    },
  },
  components: [FormDialog],
  setup() {
    const organizationStore = useOrganizationStore();
    const { errors } = organizationStore;
    const v$ = useVuelidate({ $externalResults: errors });

    return {
      organizationStore,
      errors,
      v$
    }
  },

  computed: {
    formVisible() {
      return this.visible;
    },
    title() {
      return this.organization.id ? 'Cập nhật khách hàng' : 'Tạo mới khách hàng';
    },
    ...mapState(useOrganizationStore, ['organization', 'form']),
  },
  methods: {
    onCancel(event) {
      this.organizationStore.$patch({ form: { visible: false } });
    },
    onHide() {
      this.organizationStore.resetForm();
    },
    onShow() {
      if (this.organization.id) {
        this.organizationStore.edit(1, this.organization.id)
      }
    },
    async onSave(event) {
      this.organizationStore.$patch({ form: { submitted: true } });

      const isFormCorrect = await this.v$.$validate();
      if (!isFormCorrect) {
        const elms = document.querySelectorAll('.p-invalid');
        elms[0].scrollIntoView({ behavior: 'smooth' });
        return;
      }

      const payload = {
        ...this.organization,
        permission_type: 1,
        company_id: 1,
      };

      if (!this.organization.id) this.organizationStore.store(1, payload);
      else this.organizationStore.update(1, payload);
    },
  },

  validations() {
    return {
      organization: {
        name: { required, $autoDirty: true },
        phone: { required, $autoDirty: true },
      }
    }
  }
}

</script>
<template>
  <FormDialog :visible="formVisible" :title="title" :loading="form.isLoading" @save="onSave" @cancel="onCancel"
    @hide="onHide" @show="onShow">
    <div class="flex flex-col md:flex-col gap-4">
      <div class="flex flex-col gap-1 w-full">
        <label for="name">Tên khách hàng</label>
        <div class="flex flex-col gap-1">
          <InputText autoFocus id="name" v-model="organization.name" placeholder="Nhập tên khách hàng" class="w-full"
            :invalid="v$.organization.name.$invalid && form.submitted" />
          <Message v-if="v$.organization.name.$error && form.submitted"
            v-for="(error, index) of v$.organization.name.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
      <div class="flex flex-col gap-1 w-full">
        <label for="phone">Điện thoại</label>
        <div class="flex flex-col gap-1">
          <InputText autoFocus id="phone" v-model="organization.phone" placeholder="Nhập điện thoại" class="w-full"
            :invalid="v$.organization.phone.$invalid && form.submitted" />
          <Message v-if="v$.organization.phone.$error && form.submitted"
            v-for="(error, index) of v$.organization.phone.$errors" :key="index" severity="error" size="small"
            variant="simple">
            {{ error.$message }}
          </Message>
        </div>
      </div>
    </div>
  </FormDialog>
</template>