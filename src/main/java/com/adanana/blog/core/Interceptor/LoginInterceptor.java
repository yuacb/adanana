package com.adanana.blog.core.Interceptor;

import com.adanana.blog.core.GlobalBean;
import com.adanana.blog.core.ResponseObject;
import com.adanana.blog.model.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(true){
            return true;
        }

        //由于跨域的问题...放行OPTIONS
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }

        //System.out.println(">>>MyInterceptor1>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        //验证session
        String sessionIdKey ="sessionId@"+request.getSession().getId();
        //是否已经登陆 登陆的话会存进redis
        Boolean hasKey = redisTemplate.hasKey(sessionIdKey);

        //未登录
        if(!hasKey){
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(JSON.toJSONString(ResponseObject.noLogin()));
        }else{
            User user = (User)redisTemplate.opsForValue().get(sessionIdKey);
            GlobalBean.setGlobalUser(user);
        }
        return hasKey;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }
}
