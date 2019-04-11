// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueLazyload from 'vue-lazyload'
import store from './store/index'

// 加载过滤器
import './fiters'
import loading from './common/imgs/loading.gif'

import { Alert,ConfirmPlugin } from 'vux'

Vue.use(ConfirmPlugin)
Vue.component('alert', Alert)

Vue.use(VueLazyload, {
  // 内部自定义一个指令lazy
  loading
});

Vue.config.productionTip = false;

//跨域处理
axios.defaults.withCredentials = true;

//全局使用button

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h=>h(App)
});
