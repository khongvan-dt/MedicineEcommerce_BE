<script>
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { dataTablePagination, filterTypes } from '@/constants';
import { useOrganizationStore } from '@/stores/organization';
import { mapState } from 'pinia';
// import CreateForm from './Create.vue';

export default {
  // components: {
  //   CreateForm
  // },
  setup() {
    const router = useRouter();
    const organizationStore = useOrganizationStore();
    const columns = ref([
      {
        field: 'avatar',
        label: 'Ảnh đại diện',
        visible: true,
        disabled: true,
      },
      { field: 'name', label: 'Khách hàng', visible: true, disabled: true },
      { field: 'phone', label: 'Điện thoại', visible: true, disabled: false },
      { field: 'website', label: 'Website', visible: true, disabled: false },
      {
        field: 'facebook_profile',
        label: 'Facebook',
        visible: false,
        disabled: false,
      },
      {
        field: 'linkedin_profile',
        label: 'LinkedIn',
        visible: false,
        disabled: false,
      },
      {
        field: 'twitter_profile',
        label: 'Twitter',
        visible: false,
        disabled: false,
      },
      {
        field: 'billing_address',
        label: 'Địa chỉ thanh toán',
        visible: false,
        disabled: false,
      },
      {
        field: 'shipping_address',
        label: 'Địa chỉ vận chuyển',
        visible: false,
        disabled: false,
      },
      { field: 'owner_id', label: 'Chủ sở hữu', visible: false, disabled: false },
      { field: 'tags', label: 'Thẻ gắn vào', visible: false, disabled: false },
      {
        field: 'created_at',
        label: 'Ngày tạo',
        visible: false,
        disabled: false,
      },
      {
        field: 'created_by',
        label: 'Người tạo',
        visible: false,
        disabled: false,
      },
      {
        field: 'updated_at',
        label: 'Ngày cập nhật',
        visible: false,
        disabled: false,
      },
      {
        field: 'updated_by',
        label: 'Người cập nhật',
        visible: false,
        disabled: false,
      },
      { field: 'follow', label: 'Theo dõi', visible: true, disabled: false },
    ]);
    const filterFollow = ref([
      { label: 'Theo dõi', value: true },
      { label: 'Không theo dõi', value: false },
    ]);
    const checkedAll = ref(false);
    const tableRef = ref();

    return {
      organizationStore,
      columns,
      filterFollow,
      checkedAll,
      perPage: dataTablePagination.perPage,
      tableRef
    };
  },

  data() {
    const menuItems = ref([
      {
        label: 'Cập nhật',
        icon: 'pi pi-pencil',
        command: () => this.openForm(this.organizationSelected?.id),
      },
      {
        label: 'Đổi ảnh đại diện',
        icon: 'pi pi-image',
        command: () => this.handleShow('change_image'),
      },
      {
        label: 'Nhân bản',
        icon: 'pi pi-copy',
        command: () => this.openForm(this.organizationSelected?.id, true),
      },
      {
        label: 'Xoá',
        icon: 'pi pi-trash',
        command: () => this.deleteOrganization(this.organizationSelected?.id),
      },
    ]);

    return {
      dataTablePagination,
      organizationSelected: null,
      menuItems,
      filters: null
    }
  },

  computed: {
    ...mapState(useOrganizationStore, [
      'isLoading',
      'organizations',
      'form',
      'pagination',
      'reLoaded',
      'lazyParams',
      'visibleColumns',
    ]),
  },

  watch: {
    reLoaded(newValue) {
      if (newValue) {
        this.organizationStore.search(1, { ...this.lazyParams, ...this.dataTablePagination });
      }
    },
  },

  created() {
    this.initFilters();

  },

  mounted() {
    this.organizationStore.search(1, { ...this.lazyParams, ...this.dataTablePagination });
    // this.organizationStore.getFilterAndSortData(1, { entity: 'organization', property: 'crm_organizations.owner_id' }, 'owner_id');
    // this.organizationStore.getFilterAndSortData(1, { entity: 'organization', property: 'crm_organizations.created_by' }, 'created_by');
    // this.organizationStore.getFilterAndSortData(1, { entity: 'organization', property: 'crm_organizations.updated_by' }, 'updated_by');
  },

  methods: {
    initFilters() {
      const filters = {
        global: filterTypes.CONTAINS,
        // owner_id: filterTypes.IN,
        // created_by: filterTypes.IN,
        // updated_by: filterTypes.IN,
        created_at: filterTypes.DATE_IS,
        updated_at: filterTypes.DATE_IS,
        follow: filterTypes.IN,
      };
      this.filters = filters;
      this.organizationStore.$patch({ lazyParams: { ...this.lazyParams, filters: filters } });
    },

    onFilterChange(e) {
      this.organizationStore.$patch({ lazyParams: { ...this.lazyParams, filters: e.filters }, pagination: { page: 1 }, reLoaded: true });
    },
    clearFilter() {
      this.initFilters();
      this.filters.global.value = '';
    },
    clearSearch() {
      this.filters.global.value = '';
      this.organizationStore.$patch((state) => (state.reLoaded = true));
    },

    openForm(id = null, clone = false) {
      this.organizationStore.$patch({ form: { visible: true } });
      // if (id) {
      //   if (clone) this.$router.push({ name: 'CrmOrganizationCreate', query: { id: id, clone: true } })
      //   else this.$router.push({ name: 'CrmOrganizationEdit', params: { id: id } })
      // } else this.$router.push({ name: 'CrmOrganizationCreate' })
    },

    hideForm() {
      this.organizationStore.$patch({ form: { visible: false } });
    },

    deleteOrganization(id) {
      this.$confirm.require({
        message: 'Bạn có chắc chắn muốn xóa bản ghi này không?',
        header: 'Xác nhận',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          this.organizationStore.delete(1, id);
        },
      });
    },

    toggleFollow(e, data) {
      e.preventDefault();
      const id = data.id;

      if (data.follow) {
        this.$confirm.require({
          message: 'Bạn có chắc chắn muốn hủy theo dõi khách hàng này không?',
          header: 'Xác nhận',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
            this.organizationStore.follow(1, id);
          },
        });
      } else {
        this.organizationStore.follow(1, id);
      }
    },

    toggleColumn(event) {
      this.$refs.opColumn.toggle(event);
    },

    toggle(ev, data) {
      this.$refs.menuRef.toggle(ev);
      this.organizationSelected = data;
    },
    toggleSearch(event) {
      this.$refs.searchPanel.toggle(event);
    },
    handleShow(type) {
      this.dialogType = type;
      this.organizationStore.$patch({ form: { showDialog: true } });
    },
    hideDialog() {
      this.organizationStore.resetForm();
      this.dialogStore.resetForm();
    },
    handleRowClick(data) {
      this.$router.push({ name: 'OrganizationDetail', params: { id: data.id } });
    },

    print() {
      // this.$router.push({
      //   name: 'CrmOrganizationPrint',
      //   params: { id: this.organizationSelected?.id },
      // });
    },
    exportData() {
      // const visibleColumns = this.visibleColumns.columns.filter((value) => !['avatar', 'follow'].includes(value));
      // const columns = this.columns.filter((value) => visibleColumns.includes(value.field));

      // const data_fields_report = columns.map((item) => item.field) || [];
      // const headings = columns.map((item) => item.label) || [];

      // const { filters, ...items } = this.exportParams;
      // const payload = {
      //   data_fields_report: data_fields_report,
      //   headings: headings,
      //   filters: filters,
      //   total: items.total,
      //   entity_type: 'organization',
      // };
      // this.exportStore.export(1, payload);
    },

    handleSortOderChange(oder) {
      this.organizationStore.$patch({ lazyParams: { ...this.lazyParams, sortOrder: oder == -1 ? 'desc' : 'asc' }, reLoaded: true });
    },
    // Lazy Params
    onPage(event) {
      const pagination = {
        ...this.dataTablePagination,
        first: event.first,
        page: event.page + 1,
      };
      this.organizationStore.$patch({ pagination: pagination });
      this.organizationStore.search(1, {
        ...this.lazyParams,
        ...pagination,
      });
    },
    onSort(event) {
      const lazyParams = {
        ...this.lazyParams,
        ...this.dataTablePagination,
        sortField: event.sortField,
        sortOrder: event.sortOrder == -1 ? 'desc' : 'asc',
      };
      this.organizationStore.$patch({ lazyParams: lazyParams, reLoaded: true });
    },
    onRows(event) {
      const pagination = {
        ...this.dataTablePagination,
        page: 1,
        perPage: event,
      };
      this.organizationStore.$patch({ pagination: pagination });
    },
    handleSearchDataTable() {
      clearTimeout(this.timeoutId);
      this.timeoutId = setTimeout(() => {
        this.organizationStore.search(1, {
          ...this.lazyParams,
          ...this.dataTablePagination,
          page: 1,
        });
      }, 500);
    },

    randomBackground() {
      const maxColor = 17;
      const randomIndex = Math.floor(Math.random() * maxColor);
      return randomIndex;
    },

    onSave() {
      this.organizationStore.$patch({ form: { visible: false } });
    }
  },
}
</script>

<template>
  <div class="card">
    <DataTable ref="tableRef" :value="organizations" dataKey="id" :paginator="true" filterDisplay="menu"
      :loading="isLoading" v-model:rows="perPage" :paginatorTemplate="dataTablePagination.paginatorTemplate"
      showCurrentPageReport :currentPageReportTemplate="dataTablePagination.currentPageReportTemplate"
      :rowsPerPageOptions="dataTablePagination.rowsPerPageOptions" rowHover scrollable responsiveLayout
      v-model:filters="lazyParams.filters" @filter="onFilterChange" :first="pagination.first"
      :totalRecords="pagination.total" resizableColumns @page="onPage($event)" @sort="onSort($event)"
      @update:rows="onRows($event)">
      <template #header>
        <div class="flex flex-wrap gap-2 items-center justify-between">
          <IconField class="w-full sm:w-80 order-1 sm:order-none">
            <InputIcon class="pi pi-search" />
            <InputText v-model="lazyParams.filters['global'].value" placeholder="Tìm kiếm..." class="w-full"
              @update:modelValue="handleSearchDataTable" />
          </IconField>
          <Button type="button" icon="pi pi-user-plus" label="Thêm mới" class="w-full sm:w-auto order-none sm:order-1"
            outlined @click="openForm" />
        </div>
      </template>
      <Column field="name" frozen header="Khách hàng" exportFooter=" " key="name" sortable headerClass="bg-white"
        style="min-width: 18rem;">
        <template #body="{ data }">
          <div class="flex gap-2 items-center">
            <Avatar :label="!data?.avatar ? data.name.charAt(0) : ''" :image="data?.avatar"
              :class="`flex-none uppercase avatar_class_${randomBackground()}`"
              :pt="{ image: { class: 'border-round' } }" size="large" />
            <span class="text-primary cursor-pointer text-wrap" @click="handleRowClick(data)">
              {{ data.name }}
            </span>
          </div>
        </template>
      </Column>
      <Column v-if="visibleColumns.columns?.includes('phone')" field="phone" header="Điện thoại" exportFooter=" "
        key="phone" style="min-width: 12rem"> </Column>
      <Column v-if="visibleColumns.columns?.includes('website')" field="website" header="Website" exportFooter=" "
        key="website" style="min-width: 14rem"> </Column>
      <Column v-if="visibleColumns.columns?.includes('facebook_profile')" field="facebook_profile" header="Facebook"
        exportFooter=" " key="facebook_profile" style="min-width: 12rem">
      </Column>
      <Column v-if="visibleColumns.columns?.includes('linkedin_profile')" field="linkedin_profile" header="LinkedIn"
        exportFooter=" " key="linkedin_profile" style="min-width: 12rem">
      </Column>
      <Column v-if="visibleColumns.columns?.includes('twitter_profile')" field="twitter_profile" header="Twitter"
        exportFooter=" " key="twitter_profile" style="min-width: 12rem">
      </Column>
      <Column v-if="visibleColumns.columns?.includes('billing_address')" field="billing_address"
        header="Địa chỉ thanh toán" exportFooter=" " key="billing_address" style="min-width: 14rem">
      </Column>
      <Column v-if="visibleColumns.columns?.includes('shipping_address')" field="shipping_address"
        header="Địa chỉ vận chuyển" exportFooter=" " key="shipping_address" style="min-width: 14rem">
      </Column>
      <!-- <Column v-if="visibleColumns.columns?.includes('owner_id')" filterField="owner_id" :showFilterMatchModes="false"
        :filterMenuStyle="{ width: '14rem' }" header="Chủ sở hữu" exportFooter=" " key="owner_id"
        style="min-width: 13rem">
        <template #body="{ data }">
          <span>{{ data.owner.name }}</span>
        </template>
        <template #filter="{ filterModel }">
          <Dropdown v-model="filterModel.value" :options="form.sortAndFilterInfos.owner_id" optionLabel="value"
            optionValue="id" placeholder="Chọn chủ sở hữu" class="p-column-filter">
            <template #option="slotProps">
              <div class="p-multiselect-representative-option">
                <img :alt="slotProps.option.name" :src="slotProps.option.avatar"
                  style="width: 32px; border-radius: 50%; vertical-align: middle" />
                <span style="margin-left: 0.5em; vertical-align: middle" class="image-text">{{ slotProps.option.value
                  }}</span>
              </div>
            </template>
          </Dropdown>
        </template>
      </Column> -->
      <Column v-if="visibleColumns.columns?.includes('tags')" field="tags" header="Thẻ gắn vào" exportFooter=" "
        key="tags" style="min-width: 14rem; max-width: 20rem; word-wrap: break-word; white-space: pre-line">
        <template #body="{ data }">
          <Tag v-for="(i, index) in data.tags" :key="index" :value="i"
            :class="`${index !== 0 ? 'ml-2' : ''} surface-500`" />
        </template>
      </Column>

      <Column v-if="visibleColumns.columns?.includes('created_at')" filterLocale="vi_VN" field="created_at"
        header="Ngày tạo" sortable exportFooter=" " key="created_at" dataType="date" style="min-width: 13rem">
        <template #body="{ data }">
          {{ data.created_at }}
        </template>
        <template #filter="{ filterModel }">
          <Calendar v-model="filterModel.value" dateFormat="dd/mm/yy" placeholder="Ngày/Tháng/Năm" mask="99/99/9999" />
        </template>
      </Column>
      <!-- <Column v-if="visibleColumns.columns?.includes('created_by')" field="created_by" header="Người tạo"
        exportFooter=" " key="created_by" filterField="created_by" :showFilterOperator="false"
        :showFilterMatchModes="false" style="min-width: 14rem">
        <template #filter="{ filterModel }">
          <Dropdown v-model="filterModel.value" :options="form.sortAndFilterInfos.created_by" optionLabel="value"
            optionValue="id" showClear filter placeholder="Chọn tất cả" class="p-column-filter">
            <template #option="slotProps">
              <div class="flex items-center">
                <img :alt="slotProps.option.name" :src="slotProps.option.avatar"
                  style="width: 32px; border-radius: 50%; vertical-align: middle" />
                <span style="margin-left: 0.5em; vertical-align: middle" class="image-text">{{ slotProps.option.value
                  }}</span>
              </div>
            </template>
          </Dropdown>
        </template>
      </Column> -->
      <Column v-if="visibleColumns.columns?.includes('updated_at')" field="updated_at" header="Ngày cập nhật"
        exportFooter=" " key="updated_at" dataType="date" sortable style="min-width: 13rem">
        <template #body="{ data }">
          {{ data.updated_at }}
        </template>
        <template #filter="{ filterModel }">
          <Calendar v-model="filterModel.value" dateFormat="dd/mm/yy" placeholder="Ngày/Tháng/Năm" mask="99/99/9999" />
        </template>
      </Column>
      <!-- <Column v-if="visibleColumns.columns?.includes('updated_by')" field="updated_by" header="Người cập nhật"
        exportFooter=" " key="updated_by" filterField="updated_by" :showAddButton="false" :showFilterOperator="false"
        :showFilterMatchModes="false" style="min-width: 14rem" sortable>
        <template #filter="{ filterModel }">
          <Dropdown v-model="filterModel.value" :options="form.sortAndFilterInfos.updated_by" optionLabel="value"
            optionValue="id" showClear filter placeholder="Chọn tất cả" class="p-column-filter">
            <template #option="slotProps">
              <div class="flex items-center">
                <img :alt="slotProps.option.name" :src="slotProps.option.avatar"
                  style="width: 32px; border-radius: 50%; vertical-align: middle" />
                <span style="margin-left: 0.5em; vertical-align: middle" class="image-text">{{ slotProps.option.value
                  }}</span>
              </div>
            </template>
          </Dropdown>
        </template>
      </Column> -->
      <Column v-if="visibleColumns.columns?.includes('follow')" field="follow" :exportable="false" dataType="boolean"
        :showFilterMatchModes="false" :showFilterOperator="false" :showAddButton="false" style="width: 9rem"
        header="Theo dõi">
        <template #body="{ data }">
          <div class="flex justify-center">
            <i :class="`pi ${data.follow ? ' pi-star-fill text-yellow-400' : 'pi-star hover:text-900'} `"
              class="cursor-pointer" style="font-size: 1.5rem" @click="(e) => toggleFollow(e, data)"></i>
          </div>
        </template>
        <template #filter="{ filterModel }">
          <Dropdown v-model="filterModel.value" :options="filterFollow" optionLabel="label" optionValue="value"
            showClear placeholder="Chọn..." class="p-column-filter">
          </Dropdown>
        </template>
      </Column>
      <Column field="action" header="Tác vụ" :exportable="false" headerClass="text-right" bodyClass="text-right"
        alignFrozen="right" frozen style="min-width: 50px">
        <template #body="{ data }">
          <Button icon="pi pi-ellipsis-v" outlined @click="(e) => toggle(e, data)" severity="secondary"
            variant="outlined" />
        </template>
      </Column>
    </DataTable>
    <Menu :model="menuItems" ref="menuRef" popup class="w-20rem" />
    <!-- <CreateForm v-model:visible="form.visible" @cancel-click="hideForm" @save-click="onSave" /> -->
  </div>
</template>

<style lang="scss" scoped>
.avatar_class_0 {
  background-color: #3f51b5;
  color: #fff;
}

.avatar_class_1 {
  background-color: #e91e63;
  color: #fff;
}

.avatar_class_2 {
  background-color: #9c27b0;
  color: #fff;
}

.avatar_class_3 {
  background-color: #673ab7;
  color: #fff;
}

.avatar_class_4 {
  background-color: #2196f3;
  color: #fff;
}

.avatar_class_5 {
  background-color: #03a9f4;
  color: #fff;
}

.avatar_class_6 {
  background-color: #00bcd4;
  color: #fff;
}

.avatar_class_7 {
  background-color: #009688;
  color: #fff;
}

.avatar_class_8 {
  background-color: #4caf50;
  color: #fff;
}

.avatar_class_9 {
  background-color: #8bc34a;
  color: #fff;
}

.avatar_class_10 {
  background-color: #cddc39;
  color: #fff;
}

.avatar_class_11 {
  background-color: #ffeb3b;
  color: #fff;
}

.avatar_class_12 {
  background-color: #ffc107;
  color: #fff;
}

.avatar_class_13 {
  background-color: #ff9800;
  color: #fff;
}

.avatar_class_14 {
  background-color: #ff5722;
  color: #fff;
}

.avatar_class_15 {
  background-color: #795548;
  color: #fff;
}

.avatar_class_16 {
  background-color: #607d8b;
  color: #fff;
}
</style>
