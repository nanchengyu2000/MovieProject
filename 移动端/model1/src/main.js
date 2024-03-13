import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router/index'
import axios from 'axios'
import VueCoreVideoPlayer from 'vue-core-video-player'
Vue.use(VueCoreVideoPlayer)
import "./plugs/video"; // 引入刚刚定义的video.js文件
axios.defaults.baseURL="https://vyke0kcd.beesnat.com/Movie-System/"
Vue.prototype.$resource="https://vyke0kcd.beesnat.com/Movie/"
Vue.prototype.$axios=axios
Vue.use(VueRouter)
import { Button,Tabbar,TabbarItem,Search, Tab, Tabs,Swipe, SwipeItem,Icon} from 'vant';
Vue.use(Button).use(Tabbar).use(TabbarItem).use(Search).use(Tab).use(Tabs).use(Swipe).use(SwipeItem)
.use(Icon)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
