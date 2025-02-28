<script setup>
import { dataTablePagination, filterTypes } from '@/constants';
import { getPerPage, setPerPage } from "@/services/localStorage";
import { useUserStore } from '@/stores/user';
import { storeToRefs } from 'pinia';
import { useConfirm } from 'primevue';
import { onMounted, ref, watch } from 'vue';
import CreateForm from './Create.vue';

const confirm = useConfirm()
const userStore = useUserStore()
const { isLoading, reLoaded, users, form, pagination, lazyParams, visibleColumns } = storeToRefs(userStore)
const checkedAllColumns= ref(false)
const tableRef = ref()
const menuRef = ref('menuRef')
const dataTablePaginator = ref(dataTablePagination)
const perPage = ref(parseInt(getPerPage()??dataTablePaginator.value.perPage))
const filters = ref(null)
const selectedUser = ref(null)

const menuItems = ref([
  {
    label: 'Cập nhật',
    icon: 'pi pi-pencil',
    command: () => openForm(selectedUser.value?.id),
  },
  {
    label: 'Nhân bản',
    icon: 'pi pi-copy',
    command: () => openForm(selectedUser.value?.id, true),
  },
  {
    label: 'Xoá',
    icon: 'pi pi-trash',
    command: () => deleteUser(selectedUser.value?.id),
  },
  {
    label: 'Phân quyền',
    icon: 'pi pi-cog',
    command: () => assignPermission(selectedUser.value?.id),
  },
])

function initFilters() {
  filters.value = {
    global: filterTypes.CONTAINS,
    FullName: filterTypes.CONTAINS,
    Mobile: filterTypes.CONTAINS,
    Email: filterTypes.CONTAINS,
    Address: filterTypes.CONTAINS,
    Created_by: filterTypes.IN,
    Updated_by: filterTypes.IN,
    Created_at: filterTypes.DATE_IS,
    Updated_at: filterTypes.DATE_IS,
  }
  userStore.$patch({ lazyParams: { ...lazyParams.value, filters: filters.value }, reLoaded: true })
}
function onFilterChange(event) {
  userStore.$patch({ lazyParams: { ...lazyParams.value, filters: event.filters }, pagination: { ...pagination.value, page: 1 }, reLoaded: true });
}
function handleSearchDataTable() {
  setTimeout(() => {
    userStore.search({
      ...lazyParams.value,
      ...pagination.value,
      page: 1,
    });
  }, 500);
}
function onPage(event) {
  const newPagination = {
    ...pagination.value,
    first: event.first,
    page: event.page + 1,
  };
  userStore.$patch({ pagination: newPagination, reLoaded: true });
}
function onRows(event) {
  const newPagination = {
    ...pagination.value,
    page: 1,
    perPage: event,
  };
  setPerPage(event);
  userStore.$patch({ pagination: newPagination, reLoaded: true });
}
function onSort(event) {
  const newlazyParams = {
    ...lazyParams.value,
    ...pagination.value,
    sortField: event.sortField,
    sortOrder: event.sortOrder == -1 ? 'desc' : 'asc',
  };
  userStore.$patch({ lazyParams: newlazyParams, reLoaded: true });
}
function openForm(selectedUser=null) {
  if (selectedUser) { 
    userStore.edit(selectedUser, { module_code: 'admin' })
  }

  userStore.$patch({ form: { visible: true } });
}
function deleteUser(id) {
  confirm.require({
    message: 'Bạn có chắc chắn muốn xóa bản ghi này không?',
    header: 'Xác nhận',
    icon: 'pi pi-info-circle',
    rejectLabel: 'Huỷ',
    acceptLabel: 'Đồng ý',
    rejectClass: 'p-button-secondary p-button-outlined',
    acceptClass: 'p-button-danger',
    accept: () => {
      userStore.remove(id);
    },
  });
}
function assignPermission(selectedUser) {

}

function openMenu(event, data) {
  menuRef.value.toggle(event)
  selectedUser.value = data
}

watch(reLoaded, (newValue)=> {
  console.log(lazyParams.value)
  if(newValue) {
    userStore.search({...lazyParams.value, ...pagination.value})
  }
})

onMounted(()=>{
  initFilters()
  
})
</script>
<template>
  <div class="card">
    <ConfirmDialog></ConfirmDialog>
    <DataTable ref="tableRef" :value="users" dataKey="id" :paginator="true" filterDisplay="menu"
      :loading="isLoading" lazy v-model:rows="perPage"
      :paginatorTemplate="dataTablePaginator.paginatorTemplate" showCurrentPageReport
      :currentPageReportTemplate="dataTablePaginator.currentPageReportTemplate"
      :rowsPerPageOptions="dataTablePaginator.rowsPerPageOptions" rowHover scrollable responsiveLayout
      v-model:filters="lazyParams.filters" @filter="onFilterChange" :first="pagination.first"
      :totalRecords="pagination.total" resizableColumns @page="onPage($event)" @sort="onSort($event)"
      @update:rows="onRows($event)">
      <template #header>
        <div class="flex flex-wrap gap-2 items-center justify-between">
          <span class="font-semibold text-xl whitespace-nowrap">Quản lý người dùng</span>
          <IconField class="w-full sm:w-80 order-1 sm:order-none">
            <InputIcon class="pi pi-search" />
            <InputText v-model="lazyParams.filters['global'].value" placeholder="Tìm kiếm..." class="w-full"
              @update:modelValue="handleSearchDataTable" />
          </IconField>
          <Button type="button" icon="pi pi-user-plus" label="Thêm mới" class="w-full sm:w-auto order-none sm:order-1"
            outlined @click="openForm(null)" />
        </div>
      </template>
      <Column field="id" frozen header="id" exportFooter=" " key="id" sortable headerClass="bg-white"
        style="min-width: 18rem;">
        <template #body="{ data }">
          {{ data.id }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm id" />
        </template>
      </Column>
      <Column field="fullName" frozen header="Họ và tên" exportFooter=" " key="fullName" sortable headerClass="bg-white"
        style="min-width: 18rem;">
        <template #body="{ data }">
          {{ data.fullName }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm họ và tên" />
        </template>
      </Column>
      <Column field="mobile" frozen header="Số điện thoại" exportFooter=" " key="mobile" sortable headerClass="bg-white"
        style="min-width: 18rem;">
        <template #body="{ data }">
          {{ data.mobile }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm theo mobile" />
        </template>
      </Column>
      <Column v-if="visibleColumns?.includes('created_at')" filterLocale="vi_VN" field="created_at"
        header="Ngày tạo" sortable exportFooter=" " key="Created_at" dataType="date" style="min-width: 13rem">
        <template #body="{ data }">
          {{ data.Created_at }}
        </template>
        <template #filter="{ filterModel }">
          <DatePicker v-model="filterModel.value" dateFormat="dd/mm/yy" placeholder="Ngày/Tháng/Năm" mask="99/99/9999" />
        </template>
      </Column>
      <Column field="action" header="Tác vụ" :exportable="false" headerClass="w-20" bodyClass="flex justify-end"
        alignFrozen="right" frozen style="min-width: 50px">
        <template #body="{ data }">
          <Button icon="pi pi-ellipsis-v" outlined @click="(e) => openMenu(e, data)" severity="secondary"
            variant="outlined" />
        </template>
      </Column>
    </DataTable>
    <Menu :model="menuItems" ref="menuRef" popup class="w-20rem" />
    <CreateForm v-model:visible="form.visible" />
  </div>  
</template>