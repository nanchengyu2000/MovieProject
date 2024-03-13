import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const page={
  state: {
    changepage:function changepage(){},
    test:"jjkk"
  },
  mutations: {
    ChangePages(state,value){
      state.changepage=value
    }
  },
}
const path={
  mutations:{
    Change(state) {
      state.isCollapse = !state.isCollapse
    },
    Changenames(state, value) {
      if (value.label !== '首页') {
        state.currentMenu = value;
        const i = state.listnames.findIndex(item => item.label === value.label)
        if (i === -1) {
          state.listnames.push(value);
          const obj = JSON.stringify(state.listnames)
          sessionStorage.setItem("listnames", obj)
        }
      } else {
        state.currentMenu = null
      }
    },
    Changeusernames(state, value) {
      if (value.label !== '首页') {
        state.currentuserMenu = value;
        const i = state.listusernames.findIndex(item => item.label === value.label)
        if (i === -1) {
          state.listusernames.push(value);
          const obj = JSON.stringify(state.listusernames)
          sessionStorage.setItem("listusernames", obj)
        }
      } else {
        state.currentuserMenu = null
      }
    },
  },
  state:{
    isCollapse: false,
    listnames: JSON.parse(sessionStorage.getItem('listnames')) || [{
      path: "/HomeLine/",
      name: "首页",
      label: "首页",
    }, ],
    currentMenu: null, //不太知道用途
    listusernames: JSON.parse(sessionStorage.getItem('listusernames')) || [{
      path: "/HomeUser/",
      name: "home",
      label: "home page",
    }, ],
    currentuserMenu: null, //不太知道用途
  }
}
const dialog={
  mutations:{
    show(state){
      // console.log("show dialog");
      state.dialogFormVisible=true;
    },
    disappear(state){
      // console.log("disappear dialog");
      state.dialogFormVisible=false;
    },
    changeheader(state,value){
      state.changeheader=value
    }
  },
  state:{
    dialogFormVisible:false,
    changeheader:function submitUpload(){}
  }
}
const Item={
  mutations:{
    changNum(state,value){
      state.changNum=value;
    },
    changUserNum(state,value){
      state.changNum=value;
    },
  },
  state:{
    changNum:function changNum(){},
    changUserNum:function changNum(){},
  }
}
const Drawer={
  state:{
    drawer:false
  },
  mutations:{
    changeDrawer(state,value){
      state.drawer=value
    }
  }
}
const movieinfo={
  state:{
    movie:{
      moviename:"",
      moviearea:"",
      sort:"",
      intro:"",
      duration:"",
      dateissued:"",
    },
    parentname:"",
    relativepath:"",
  },
  mutations:{
    setparentname(state,value){
      state.parentname=value
    },
    setrelativepath(state,value){
      state.relativepath=value
    }
  }
}
export default new Vuex.Store({
  modules:{
    page,
    path,
    dialog,
    Item,
    Drawer,
    movieinfo
  }
})