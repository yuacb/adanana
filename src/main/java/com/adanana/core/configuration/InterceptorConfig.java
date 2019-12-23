package com.adanana.core.configuration;

import com.adanana.core.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        //。。。。登陆拦截 排除登陆 和注册 请求
        registry.addInterceptor(loginInterceptor).addPathPatterns("/app/**").excludePathPatterns("/app/login","/app/register");
    }
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        /* 是否通过请求Url的扩展名来决定media type */
//        configurer.favorPathExtension(true)
//                /* 不检查Accept请求头 */
//                .ignoreAcceptHeader(true)
//                .parameterName("mediaType")
//                /* 设置默认的media yype */
//                .defaultContentType(MediaType.TEXT_HTML)
//                /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
//                .mediaType("html", MediaType.TEXT_HTML)
//                /* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON*/
//                .mediaType("json", MediaType.APPLICATION_JSON);
//    }

    //    @Override
//    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/").setViewName("index");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }
//
//    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations( "classpath:/static/");
    }
////    @Override
////    protected void configureViewResolvers(ViewResolverRegistry registry) {
////            registry.
////    }
//    @Bean
//    public InternalResourceViewResolver setupViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        /** 设置视图路径的前缀 */
//        resolver.setPrefix("/static/");
//        /** 设置视图路径的后缀 */
//        resolver.setSuffix(".html");
//        return resolver;
//    }
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
    }
}
