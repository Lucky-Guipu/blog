import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'
import Login from '@/components/Login.vue'
import About from '@/components/About.vue'
import Moments from '@/components/Moments.vue'
import Category from '@/components/Category.vue'
import Tag from '@/components/Tag.vue'
import Blog from '@/components/Blog.vue'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/moments',
    name: 'Moments',
    component: Moments
  },
  {
    path: '/category',
    name: 'CategoryList',
    component: Category
  },
  {
    path: '/category/:name',
    name: 'CategoryDetail',
    component: Category
  },
  {
    path: '/tag/:name',
    name: 'Tag',
    component: Tag
  },
  {
    path: '/blog/:blogId',
    name: 'blog',
    component: Blog
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },

]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
