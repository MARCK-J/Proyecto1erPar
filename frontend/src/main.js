import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue';
import LoginComponent from './components/LoginComponent.vue';
import HomeComponent from './components/HomeComponent.vue';
import LabelComponent from './components/LabelComponent.vue';
import TaskComponent from './components/TaskComponent.vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import '@fortawesome/fontawesome-free/css/all.css'

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const routes = [
  {
    name: 'Login',
    path: '/login',
    component: LoginComponent
  },
  {
    name: 'Home',
    path: '/',
    component: HomeComponent
  },
  {
    name: 'Etiquetas',
    path: '/etiquetas',
    component: LabelComponent
  }
  ,
  {
    name: 'Tareas',
    path: '/tareas',
    component: TaskComponent
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


createApp(App).use(router).component('font-awesome-icon', FontAwesomeIcon).use(VueSweetalert2).mount('#app')
