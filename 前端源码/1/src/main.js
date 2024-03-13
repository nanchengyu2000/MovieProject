import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
// axios.defaults.baseURL="https://vyke0kcd.beesnat.com/Movie-System/"
axios.defaults.baseURL="http://localhost:8084/System/"
Vue.prototype.$axios=axios
// Vue.prototype.$resourcesPath='https://vyke0kcd.beesnat.com/Movie/'
Vue.prototype.$resourcesPath='http://localhost:8084/Movie/'
Vue.prototype.$SystemPath='http://localhost:8084/System/'
import "./plugins/video.js"; // 引入刚刚定义的video.js文件
import router from './Router/index'
import store from './store/index' 
import VueCoreVideoPlayer from "vue-core-video-player";
Vue.use(VueCoreVideoPlayer);

//  引入Echarts
import * as echarts from 'echarts'
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
import {Header,Aside,Form,FormItem,Input,Button,Link,Main,Card,Rate,Avatar,Backtop
  ,Tabs,TabPane,Carousel,CarouselItem,Dialog,Menu,MenuItem,MenuItemGroup,Row,Col
  ,Breadcrumb,BreadcrumbItem,Dropdown,DropdownItem,DropdownMenu,Container,Upload
  ,Step,Steps,DatePicker,Select,Option,Tag,TimePicker,Popover,Progress,Empty,Tooltip} from 'element-ui'
  Vue.use(Header).use(Aside).use(Form).use(FormItem).use(Input).use(Button).use(Link)
.use(Main).use(Card).use(Rate).use(Avatar).use(Backtop).use(Tabs).use(TabPane)
.use(Carousel).use(CarouselItem).use(Dialog).use(Menu).use(MenuItem).use(MenuItemGroup)
.use(Row).use(Col).use(Breadcrumb).use(BreadcrumbItem).use(Dropdown).use(DropdownItem)
.use(DropdownMenu).use(Container).use(Upload).use(Step).use(Steps).use(DatePicker)
.use(Select).use(Option).use(Tag).use(TimePicker).use(Popover).use(Progress).use(Empty)
.use(Tooltip)
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
