<script setup>
import { dataTablePagination, filterTypes } from '@/constants';
import { getPerPage, setPerPage } from "@/services/localStorage";
import { useTicketPriceStore } from '@/stores/ticketprice';
import { storeToRefs } from 'pinia';
import { useConfirm } from 'primevue';
import { onMounted, ref, watch } from 'vue';
import CreateForm from './Create.vue';
const confirm = useConfirm()
const ticketPriceStore = useTicketPriceStore();
const { isLoading, giaVes, reLoaded, ticketPriceStores, form, pagination, lazyParams, visibleColumns, loaiChoItems, gaDiItems, gaDenItems } = storeToRefs(ticketPriceStore);
const checkedAllColumns= ref(false);
const tableRef = ref();
const menuRef = ref('menuRef');
const dataTablePaginator = ref(dataTablePagination)
const perPage = ref(parseInt(getPerPage()??dataTablePaginator.value.perPage))
const filters = ref(null)
const selectedGiaVe = ref(null)
//const gaDiItems = ref([]);
//const gaDenItems = ref([]);
// Lọc theo ngày
const startDate = ref(null);
const endDate = ref(null);
// Bộ lọc tìm kiếm
const searchText = ref("");
const loaiCho = ref("");

const menuItems = ref([
  {
    label: 'Cập nhật',
    icon: 'pi pi-pencil',
    command: () => openForm(selectedGiaVe.value?.id),
  },
  {
    label: 'Nhân bản',
    icon: 'pi pi-copy',
    command: () => openForm(selectedGiaVe.value?.id, true),
  },
  {
    label: 'Xoá',
    icon: 'pi pi-trash',
    command: () => deleteGiaVe(selectedGiaVe.value?.id),
  }
])
function initFilters() {
    filters.value = {
        global: filterTypes.CONTAINS,
        Id: filterTypes.EQUALS,
        TenBangGia: filterTypes.CONTAINS,
        ListMacTau: filterTypes.CONTAINS,                
        KhoangThoiGianTu: filterTypes.DATE_IS,
        KhoangThoiGianDen: filterTypes.DATE_IS,
    };
    ticketPriceStore.$patch({ lazyParams: { ...lazyParams.value, filters: filters.value }, reLoaded: true })
}
function initDirectory() {
    ticketPriceStore.getLoaiChoItems('');
    ticketPriceStore.getGaDiItems('');
    ticketPriceStore.getGaDenItems('');
}
function search() {
    console.log(lazyParams.value);
}
function onFilterChange(event) {
    ticketPriceStore.$patch({ lazyParams: { ...lazyParams.value, filters: event.filters }, pagination: { ...pagination.value, page: 1 }, reLoaded: true });
}
function handleSearchDataTable() {
  setTimeout(() => {
    ticketPriceStore.search({
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
  ticketPriceStore.$patch({ pagination: newPagination, reLoaded: true });
}
function onRows(event) {
  const newPagination = {
    ...pagination.value,
    page: 1,
    perPage: event,
  };
  setPerPage(event);
  ticketPriceStore.$patch({ pagination: newPagination, reLoaded: true });
}
function onSort(event) {
  const newlazyParams = {
    ...lazyParams.value,
    ...pagination.value,
    sortField: event.sortField,
    sortOrder: event.sortOrder == -1 ? 'desc' : 'asc',
  };
  ticketPriceStore.$patch({ lazyParams: newlazyParams, reLoaded: true });
}
function openForm(selectedGiaVe=null) {
  if (selectedGiaVe) { 
    ticketPriceStore.edit(selectedGiaVe, { module_code: 'admin' })
  }
  else {
    ticketPriceStore.create();
  }
  ticketPriceStore.$patch({ form: { visible: true } });
}
function deleteGiaVe(id) {
  confirm.require({
    message: 'Bạn có chắc chắn muốn xóa bản ghi này không?',
    header: 'Xác nhận',
    icon: 'pi pi-info-circle',
    rejectLabel: 'Huỷ',
    acceptLabel: 'Đồng ý',
    rejectClass: 'p-button-secondary p-button-outlined',
    acceptClass: 'p-button-danger',
    accept: () => {
        ticketPriceStore.remove(id);
    },
  });
}

function openMenu(event, data) {
  menuRef.value.toggle(event)
  selectedGiaVe.value = data
}
function clearFilter () {
    initFilters()
}
function formatDate(value){
    return value.toLocaleDateString('en-US', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    });
};
watch(reLoaded, (newValue)=>{
    console.log(lazyParams.value)
    if(newValue){
        ticketPriceStore.search({...lazyParams.value, ...pagination.value})
    }
});
onMounted(()=>{
   // initDirectory();
    initFilters()
  
})
</script>
<template>
    <!-- <div class="container mx-auto p-6">        
            <div class="bg-white p-4 shadow-md rounded-lg">
                
                <div class="mb-4">
                        <InputText v-model="lazyParams.filters['global'].value" placeholder="Nhập từ khóa tìm kiếm..." class="p-inputtext-sm w-full" />
                </div>
                <div class="mb-4">
                    <Dropdown v-model="lazyParams.filters['LoaiCho'].value" :options="loaiChoItems" optionLabel="dienGiai"  optionValue="loaiCho" placeholder="Loại chỗ" class="p-dropdown-sm w-full" :filter="true"/>
                </div>
                
                <div class="grid grid-cols-2 gap-4 mb-4">
                    <Dropdown v-model="lazyParams.filters['MaGaDi'].value" :options="gaDiItems" optionLabel="tenGa"  optionValue="maGa" placeholder="Chọn ga đi" class="p-dropdown-sm w-full" :filter="true"/>
                    <Dropdown  v-model="lazyParams.filters['MaGaDen'].value" :options="gaDenItems" optionLabel="tenGa"  optionValue="maGa"  placeholder="Chọn ga đến" class="p-dropdown-sm w-full" :filter="true"/>
                </div>
                  
                <div class="grid grid-cols-2 gap-4 mb-4">
                    <Calendar v-model="startDate" placeholder="Ngày bắt đầu" class="p-calendar-sm w-full" />
                    <Calendar v-model="endDate" placeholder="Ngày kết thúc" class="p-calendar-sm w-full" />
                </div>
                
                <Button label="Tìm kiếm" icon="pi pi-search" class="p-button-sm p-button-primary" @click="search()" />
            </div>
    </div> -->
    <div class="container mx-auto p-6">
        <div class="bg-white p-4 shadow-md rounded-lg">
            <div class="card">
                <ConfirmDialog></ConfirmDialog>
                <DataTable ref="tableRef" showGridlines :value="giaVes" dataKey="id" :paginator="true" filterDisplay="menu"
      :loading="isLoading" lazy v-model:rows="perPage"
      :paginatorTemplate="dataTablePaginator.paginatorTemplate" showCurrentPageReport
      :currentPageReportTemplate="dataTablePaginator.currentPageReportTemplate"
      :rowsPerPageOptions="dataTablePaginator.rowsPerPageOptions" rowHover scrollable responsiveLayout
      v-model:filters="lazyParams.filters" @filter="onFilterChange" :first="pagination.first"
      :totalRecords="pagination.total" resizableColumns @page="onPage($event)" @sort="onSort($event)"
      @update:rows="onRows($event)">
     
      <template #header>
                <div class="flex justify-between">
                    <Button type="button" icon="pi pi-filter-slash" label="Clear"  outlined   @click="clearFilter()"/>
                    <IconField>
                        <InputIcon>
                            <i class="pi pi-search" />
                        </InputIcon>
                        <InputText v-model="lazyParams.filters['global'].value" placeholder="Từ khóa tìm kiếm"  @update:modelValue="handleSearchDataTable"/>
                    </IconField>
                    <Button type="button" icon="pi pi-user-plus" label="Thêm mới" class="w-full sm:w-auto order-none sm:order-1"
                    outlined @click="openForm(null)" />
                </div>
            </template>
      <template #empty> Không tìm thấy giá vé. </template>
      <template #loading> Đang tải dữ liệu. Vui lòng chờ. </template>
      <Column field="Id" frozen header="Id" exportFooter=" " key="id" dataType="numeric" sortable headerClass="bg-white"
        style="min-width: 10rem;">
        <template #body="{ data }">
          {{ data.id }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm id" />
        </template>
      </Column>
      <Column field="tenBangGia" frozen filterField="TenBangGia" header="Bảng giá" exportFooter=" " key="tenBangGia" sortable headerClass="bg-white"
        style="min-width: 10rem;">
        <template #body="{ data }">
          {{ data.tenBangGia }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm tên bảng giá" />
        </template>
      </Column>
      <Column field="listMacTau" filterField="ListMacTau" frozen header="Mác tàu" exportFooter=" " key="listMacTau" sortable headerClass="bg-white"
        style="min-width: 10rem;">
        <template #body="{ data }">
          {{ data.listMacTau }}
        </template>
        <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" placeholder="Tìm kiếm mác tàu" />
        </template>
      </Column>
      <Column header="Thời gian bắt đầu" filterField="KhoangThoiGianTu" dataType="date" sortable style="min-width: 10rem">
                <template #body="{ data }">
                    {{ data.khoangThoiGianTu}}
                </template>
                <template #filter="{ filterModel }">
                    <DatePicker v-model="filterModel.value" dateFormat="dd/mm/yy" placeholder="Ngày/Tháng/Năm" mask="99/99/9999"/>
                </template>
            </Column>
            <Column  header="Thời gian kết thúc" filterField="KhoangThoiGianDen" dataType="date" sortable style="min-width: 10rem">
                <template #body="{ data }">
                    {{ data.khoangThoiGianDen }}
                </template>
                <template #filter="{ filterModel }">
                    <DatePicker v-model="filterModel.value" dateFormat="dd/mm/yy"  placeholder="Ngày/Tháng/Năm" mask="99/99/9999" />
                </template>
            </Column>
          
            <Column field="action" header="Tác vụ" :exportable="false" headerClass="w-20" bodyClass="flex justify-end"
        alignFrozen="right" frozen style="min-width: 10rem">
        <template #body="{ data }">
          <Button icon="pi pi-ellipsis-v" outlined @click="(e) => openMenu(e, data)" severity="secondary"
            variant="outlined" />
        </template>
      </Column>
      
                </DataTable>
                <Menu :model="menuItems" ref="menuRef" popup class="w-20rem" />
                <CreateForm v-model:visible="form.visible" />
            </div>
        </div>
    </div>
</template>