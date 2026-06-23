import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue({
      template: {
      // 解决 meting-js 组件警告
      compilerOptions: {
        isCustomElement: (tag) => tag === 'meting-js'
      }
    }
    }),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
      'assets': fileURLToPath(new URL('./src/assets', import.meta.url)),
      'common': fileURLToPath(new URL('./src/common', import.meta.url)),
      'components': fileURLToPath(new URL('./src/components', import.meta.url)),
      'api': fileURLToPath(new URL('./src/api', import.meta.url)),
      'views': fileURLToPath(new URL('./src/views', import.meta.url)),
      'plugins': fileURLToPath(new URL('./src/plugins', import.meta.url))
    },
  },
  server : {
    port : 8080, 
    proxy: {
      '/api': {
        target: 'http://localhost:8443',
        changeOrigin: true,
        rewrite: (pathStr) => pathStr.replace(/^\/api/, '')
      },
    },
  },
})