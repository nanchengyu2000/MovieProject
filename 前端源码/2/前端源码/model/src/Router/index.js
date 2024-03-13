import Vue from 'vue'
import VueRouter from "vue-router"

import Login from '../Pages/Login.vue'
import Index from '../Pages/Index.vue'
import IndexLine from '../Pages/IndexLine.vue'
import TopLine from '../Pages/TopLine.vue'
import SeachResult from '../Pages/SeachResult.vue'
import MoveInfo from '../Pages/MoveInfo.vue'
import IndexAdmi from '../AdmiPages/IndexAdmi.vue'
import userHome from '../AdmiPages/Home.vue'
import ShowBook from '../AdmiPages/ShowBook.vue'
import UploadMovie from '../AdmiPages/UploadMovie.vue'  
import FolderList from '../AdmiPages/FolderList.vue'  
import FileList from '../AdmiPages/FileList.vue'
import StagePhoto from '../AdmiPages/StagePhoto.vue'
import Television from '../AdmiPages/Television.vue'
import Preview from '../AdmiPages/Preview.vue'
import BasicInfo from '../AdmiPages/BasicInfo.vue'
import Director from '../AdmiPages/Director.vue'
import Player from '../Pages/Player.vue'
Vue.use(VueRouter)
export default new VueRouter({
  routes:[
    {
      path:'/',
      component:Login
    },
    {
      path:'/Index',
      component:Index,
      children:[{
        path:'IndexLine',
        component:IndexLine
      },{
        path:'MoveInfo',
        component:MoveInfo
      },{
        path:'Player',
        component:Player
      },{
        path:'TopLine',
        component:TopLine
      },{
        path:'SeachResult',
        component:SeachResult
      }]
    },
    {
      path:'/IndexAdmi',
      component:IndexAdmi,
      children:[{
        path:'userHome',
        name:'userHome',
        component:userHome
      },{
        path:'showbook',
        component:ShowBook
      },{
        path:'UploadMovie',
        component:UploadMovie,
        children:[{
          path:'FolderList',
          component:FolderList,
        },{
          path:'FileList',
          component:FileList
        },{
          path:'StagePhoto',
          component:StagePhoto
        },{
          path:'Television',
          component:Television
        },{
          path:'Preview',
          component:Preview
        },{
          path:'BasicInfo',
          component:BasicInfo
        },{
          path:'Director',
          component:Director
        }]
      }
    ]
    }
  ]
})