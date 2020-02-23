package com.adanana.blog.core;

import com.adanana.blog.model.User;

public class GlobalBean {
    //用户信息
   private static ThreadLocal<User> localUser = new ThreadLocal();
   public static void setGlobalUser(User user){
       localUser.set(user);
   }
   //get 方法
   public static User GLOBAL_USER(){
       return localUser.get();
   }
}
