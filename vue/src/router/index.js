import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import LoganSquareView from '../views/LoganSquareView.vue';
import StopSearchView from '../views/StopSearchView.vue';
import StopDetailView from '../views/StopDetailView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/LoganSquare',
      name: 'logan',
      component: LoganSquareView
    },
    {
      path: '/stop',
      name: 'stop',
      component: StopSearchView
    },
    {
      path: '/stop/:mapId',
      name: 'stop-detail',
      component: StopDetailView
    }
  ]
})

export default router
