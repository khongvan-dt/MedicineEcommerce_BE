
const routes = {
  path: '/auth',
  redirect: '/auth/login',
  component: () => import('@/layout/AuthLayout.vue'),
  children: [
    {
      path: '/auth/login',
      name: 'Login',
      component: () => import('@/views/auth/Login.vue')
    },
  ]
}

export default routes