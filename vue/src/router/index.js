import { createRouter, createWebHistory } from 'vue-router'
import TrainLinesView from '../views/TrainLinesView.vue';
import AboutView from '../views/AboutView.vue'
import LoganSquareView from '../views/LoganSquareView.vue';
import StopSearchView from '../views/StopSearchView.vue';
import StopDetailView from '../views/StopDetailView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/line',
      name: 'train-lines',
      component: TrainLinesView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/',
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
