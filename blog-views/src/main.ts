import App from './App.vue'
import router from './router'
import { createApp } from 'vue'
//pinia
import { createPinia } from 'pinia'
//element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//semantic-ui
import 'semantic-ui-css/semantic.min.css'

const pinia = createPinia()
const app = createApp(App)

app.use(router)
app.use(pinia)
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.mount('#app')