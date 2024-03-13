import VueRouter from "vue-router";
import Index from '../pages/Index.vue'
import Member from '../pages/Member.vue'
import Movie from '../pages/Movie.vue'
import OneSelf from '../pages/OneSelf.vue'
import Player from '../pages/Player.vue'
export default new VueRouter({
  routes:[{
    path:"/",
    component:Index,
  },{
    path:"/Member",
    component:Member,
  },{
    path:"/Movie",
    component:Movie,
  },{
    path:"/OneSelf",
    component:OneSelf,
  },{
    path:"/Player",
    component:Player
  }]
})