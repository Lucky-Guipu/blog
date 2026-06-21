import App from './App.vue'
import router from './router'
import axios from 'axios'
import { createApp } from 'vue'
//element-ui
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//semantic-ui
import 'semantic-ui-css/semantic.min.css'


axios.defaults.baseURL = 'http://localhost:8443/api'

const app = createApp(App)
app.config.globalProperties.$axios = axios
app.use(router)
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.mount('#app')