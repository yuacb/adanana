(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0321a722"],{"2dc9":function(e,r,t){"use strict";t.r(r);var a=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("CRow",[t("CCol",{attrs:{lg:"6"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData()},scopedSlots:e._u([{key:"header",fn:function(){return[t("CIcon",{attrs:{name:"cil-grid"}}),e._v(" Simple Table "),t("div",{staticClass:"card-header-actions"},[t("a",{staticClass:"card-header-action",attrs:{href:"https://coreui.io/vue/docs/components/nav",rel:"noreferrer noopener",target:"_blank"}},[t("small",{staticClass:"text-muted"},[e._v("docs")])])])]},proxy:!0}])})],1),t("CCol",{attrs:{lg:"6"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData(),striped:"",caption:"Striped Table"}})],1)],1),t("CRow",[t("CCol",{attrs:{lg:"6"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData(),small:"",caption:"Condensed Table"}})],1),t("CCol",{attrs:{lg:"6"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData(),fixed:"",bordered:"",caption:"Bordered Table"}})],1)],1),t("CRow",[t("CCol",{attrs:{sm:"12"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData(),hover:"",striped:"",bordered:"",small:"",fixed:"",caption:"Combined All Table"}})],1)],1),t("CRow",[t("CCol",{attrs:{sm:"12"}},[t("CTableWrapper",{attrs:{items:e.getShuffledUsersData(),hover:"",striped:"",bordered:"",small:"",fixed:"",dark:"",caption:"Combined All dark Table"}})],1)],1)],1)},s=[],n=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("CCard",[t("CCardHeader",[e._t("header",[t("CIcon",{attrs:{name:"cil-grid"}}),e._v(" "+e._s(e.caption)+" ")])],2),t("CCardBody",[t("CDataTable",{attrs:{hover:e.hover,striped:e.striped,bordered:e.bordered,small:e.small,fixed:e.fixed,items:e.items,fields:e.fields,"items-per-page":e.small?10:5,dark:e.dark,pagination:""},scopedSlots:e._u([{key:"status",fn:function(r){var a=r.item;return[t("td",[t("CBadge",{attrs:{color:e.getBadge(a.status)}},[e._v(e._s(a.status))])],1)]}}])})],1)],1)},i=[],l={name:"Table",props:{items:Array,fields:{type:Array,default:function(){return["username","registered","role","status"]}},caption:{type:String,default:"Table"},hover:Boolean,striped:Boolean,bordered:Boolean,small:Boolean,fixed:Boolean,dark:Boolean},methods:{getBadge:function(e){return"Active"===e?"success":"Inactive"===e?"secondary":"Pending"===e?"warning":"Banned"===e?"danger":"primary"}}},d=l,o=t("2877"),u=Object(o["a"])(d,n,i,!1,null,null,null),m=u.exports,c=t("bd76"),f={name:"Tables",components:{CTableWrapper:m},methods:{shuffleArray:function(e){for(var r=e.length-1;r>0;r--){var t=Math.floor(Math.random()*(r+1)),a=e[r];e[r]=e[t],e[t]=a}return e},getShuffledUsersData:function(){return this.shuffleArray(c["a"].slice(0))}}},g=f,p=Object(o["a"])(g,a,s,!1,null,null,null);r["default"]=p.exports},bd76:function(e,r,t){"use strict";var a=[{username:"Samppa Nori",registered:"2012/01/01",role:"Member",status:"Active"},{username:"Estavan Lykos",registered:"2012/02/01",role:"Staff",status:"Banned"},{username:"Chetan Mohamed",registered:"2012/02/01",role:"Admin",status:"Inactive"},{username:"Derick Maximinus",registered:"2012/03/01",role:"Member",status:"Pending"},{username:"Friderik Dávid",registered:"2012/01/21",role:"Staff",status:"Active"},{username:"Yiorgos Avraamu",registered:"2012/01/01",role:"Member",status:"Active"},{username:"Avram Tarasios",registered:"2012/02/01",role:"Staff",status:"Banned",_classes:"table-success"},{username:"Quintin Ed",registered:"2012/02/01",role:"Admin",status:"Inactive"},{username:"Enéas Kwadwo",registered:"2012/03/01",role:"Member",status:"Pending"},{username:"Agapetus Tadeáš",registered:"2012/01/21",role:"Staff",status:"Active"},{username:"Carwyn Fachtna",registered:"2012/01/01",role:"Member",status:"Active",_classes:"table-success"},{username:"Nehemiah Tatius",registered:"2012/02/01",role:"Staff",status:"Banned"},{username:"Ebbe Gemariah",registered:"2012/02/01",role:"Admin",status:"Inactive"},{username:"Eustorgios Amulius",registered:"2012/03/01",role:"Member",status:"Pending"},{username:"Leopold Gáspár",registered:"2012/01/21",role:"Staff",status:"Active"},{username:"Pompeius René",registered:"2012/01/01",role:"Member",status:"Active"},{username:"Paĉjo Jadon",registered:"2012/02/01",role:"Staff",status:"Banned"},{username:"Micheal Mercurius",registered:"2012/02/01",role:"Admin",status:"Inactive"},{username:"Ganesha Dubhghall",registered:"2012/03/01",role:"Member",status:"Pending"},{username:"Hiroto Šimun",registered:"2012/01/21",role:"Staff",status:"Active"},{username:"Vishnu Serghei",registered:"2012/01/01",role:"Member",status:"Active"},{username:"Zbyněk Phoibos",registered:"2012/02/01",role:"Staff",status:"Banned"},{username:"Einar Randall",registered:"2012/02/01",role:"Admin",status:"Inactive",_classes:"table-danger"},{username:"Félix Troels",registered:"2012/03/21",role:"Staff",status:"Active"},{username:"Aulus Agmundr",registered:"2012/01/01",role:"Member",status:"Pending"}];r["a"]=a}}]);
//# sourceMappingURL=chunk-0321a722.763b5dff.js.map