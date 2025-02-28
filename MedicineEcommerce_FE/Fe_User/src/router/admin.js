import AppLayout from '@/layout/AppLayout.vue';

const admin = {
  path: '/admin',
  component: AppLayout,
  children: [
    {
      path: 'customers',
      name: 'admin-customer',
      meta: {
        breadcrumb: ['Users'],
        permission: 'admin.users.index'
      },
      component: () => import('@/views/admin/user/Index.vue')
    },
    {
      path: 'roles',
      name: 'admin-roles',
      meta: {
        breadcrumb: ['roles'],
        permission: 'admin.roles.index'
      },
      component: () => import('@/views/admin/user-management/roles/Index.vue')
    },
    {
      path: 'users',
      name: 'admin-users',
      meta: {
        breadcrumb: ['Quản lý người dùng']
      },
      component: () => import('@/views/admin/user-management/users/Index.vue')
    },
    {
      path: 'ticketprices',
      name: 'admin-ticketprices',
      meta: {
        breadcrumb: ['Quản lý giá vé']
      },
      component: () => import('@/views/admin/giave-management/giaves/Index.vue')
    },
  ]
};

export default admin;