<script setup>
import { useLayout } from '@/layout/composables/layout';
import { getUser } from '@/services/localStorage';
import { computed } from 'vue';

const { layoutState, toggleConfigSidebar } = useLayout();
const user = computed(()=>getUser());
const userMenuItems = [
  {
    label: 'Hồ sơ người dùng',
    icon: 'pi pi-user',
    command: () => {
      layoutState.profileSidebarVisible = !layoutState.profileSidebarVisible
    }
  },
  {
    label: 'Lịch sử giao dịch',
    icon: 'pi pi-history',
    command: () => {
      layoutState.profileSidebarVisible = !layoutState.profileSidebarVisible
    }
  },
  {
    label: 'Đổi giao diện',
    icon: 'pi pi-palette',
    command: () => {
      layoutState.profileSidebarVisible = !layoutState.profileSidebarVisible
      toggleConfigSidebar()
    }
  },
  {
    label: 'Cấu hình người dùng',
    icon: 'pi pi-cog',
    command: () => {
      layoutState.profileSidebarVisible = !layoutState.profileSidebarVisible
    }
  },
  {
    label: 'Đăng xuất',
    icon: 'pi pi-power-off',
    command: () => {}
  },
]

</script>

<template>
    <Drawer v-model:visible="layoutState.profileSidebarVisible" position="right" class="layout-profile-sidebar w-full sm:w-[25rem]">
        <div class="flex flex-col mx-auto md:mx-0">
            <span class="mb-2 font-semibold">Xin chào!</span>
            <span class="text-surface-500 dark:text-surface-400 font-medium mb-8"></span>
            <PanelMenu :model="userMenuItems" />
        </div>
    </Drawer>
</template>
