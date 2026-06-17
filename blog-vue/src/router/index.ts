import { createRouter, createWebHistory } from 'vue-router'
import AppIndex from '@/components/home/AppIndex.vue'
import Login from '@/components/Login.vue'

const routes = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/index',
    name: 'AppIndex',
    component: AppIndex
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
