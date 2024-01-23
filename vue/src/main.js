import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createStore } from './store'
import axios from 'axios'

axios.defaults.headers = {
    'Access-Control-Allow-Origin': '*'
}

const app = createApp(App)
const store = createStore();

app.use(router)
app.use(store);

app.mount('#app')