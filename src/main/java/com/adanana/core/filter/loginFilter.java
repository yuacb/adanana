package com.adanana.core.filter;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@WebFilter(filterName="loginFilter",urlPatterns="/*")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        HttpServletRequest request=(HttpServletRequest) servletRequest;
//        HttpServletResponse response=(HttpServletResponse) servletResponse;
//        String requestURL = request.getServletPath();
//        String[] rootURLs= requestURL.split("\\/");
//        String rootURL = "";
//        if(rootURLs.length>0)
//        {
//            rootURL = rootURLs[1];
//        }
//
//        //白名单
//        LinkedList whitelist  =  new LinkedList<String>();
//        whitelist.add("");
//        whitelist.add("js");
//        whitelist.add("error.html");
//        //验证session
//
//        if(whitelist.contains(rootURL)){
            filterChain.doFilter(servletRequest, servletResponse);
//        }else{
//            HttpSession session=request.getSession();
//            session.isNew();
//            JSONObject jsonObject  = new JSONObject();
//            PrintWriter writer =response.getWriter();
//            JSONObject o = new JSONObject();
//            try {
//                o.put("status", "success");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            writer.write(o.toString());
 //       }
    }

    @Override
    public void destroy() {

    }
}
