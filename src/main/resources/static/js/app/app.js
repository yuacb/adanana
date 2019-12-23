// const URL_REQUEST = "http://127.0.0.1:8080/app/";
const URL_REQUEST = " http://www.adanana.com/app/";
var navigate = new Vue({
    el:"#navigate",
    data:{
        userNickname:""
    }
});

var register = new Vue({
    el:"#register",
    data:{
        userLoginName:"",
        userNickname:"",
        password :"",
        confirmpassword:"",
        email:""
    },
    methods :{
        //注册
        fx_register:function(){
        //发送 post 请求
        this.$http.post( URL_REQUEST+"register",register.$data,{emulateJSON:true})
            .then(function(res){
                debugger;
                $("#register").modal('hide');
                },function(res){
                    console.log(res.status);
                },function (res) {
            });
        }
    }
});

var login = new Vue({
    el:"#login",
    data:{
        loginUserName:"",
        loginPassword :"",
    },
    methods :{
        //注册
        fx_login:function(){
            debugger;
            //发送 post 请求
            this.$http.post( URL_REQUEST+"login",{userLoginName:this.loginUserName,password:this.loginPassword },{emulateJSON:true})
                .then(function(res){
                    banner.$data.userNickname = res.userNickname;
                    $("#login").modal('hide');
                },function(res){
                    console.log(res.status);
                },function (res) {
                });
        }
    }
});