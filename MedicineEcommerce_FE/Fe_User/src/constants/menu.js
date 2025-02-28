export const adminMenu = [
  {
    label: 'Quản lý',
    icon: 'pi pi-fw pi-compass',
    items: [
      // {
      //   label: 'Khách hàng',
      //   icon: 'pi pi-fw pi-building',
      //   to: '/admin/tenants',
      //   permission: 'admin.tenants.index',
      // },

      {
        label: 'Người dùng ',
        icon: 'pi pi-fw pi-users',
        to: '/admin/users',
        permission: 'admin.users.index',
      },
      {
        label: 'Người dùng demo',
        icon: 'pi pi-fw pi-users',
        to: '/admin/nguoidung',
        permission: 'admin.users.index',
      },
      // {
      //   label: 'Vai trò người dùng',
      //   icon: 'pi pi-fw pi-users',
      //   to: '/admin/roles',
      //   permission: 'admin.roles.index',
      // },
    ],
  },
];

export const clientMenu = [
  {
    label: 'BHL App',
    items: [
      {
        label: 'Tổng quan',
        icon: 'pi pi-globe',
        to: '/',
      },
      {
        label: 'Công việc',
        icon: 'pi pi-check-square',
        to: '/',
      },
    ],
  },
];
