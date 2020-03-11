package com.adanana.blog.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * web_socket
 */
@Component
@ServerEndpoint(value="chatroom")
public class ChatRoomController {
    @OnOpen
    public synchronized void onOpen( Session session) throws IOException {
            System.out.println("socket 连接");
    }


    /**
     *
     * @Description: 收到消息执行
     * @param @param userId
     * @param @param message
     * @param @param session
     * @param @throws IOException
     * @return void
     * @throws
     * @author 黑暗料理界扛把子
     * @date 2018年5月10日
     */
    @OnMessage
    public synchronized void onMessage(@PathParam("userId") String userId, String message, Session session) throws IOException {

        System.out.println("socket 收到客户端消息");
    }

    /**
     *
     * @Description: 链接错误执行
     * @param @param userId
     * @param @param session
     * @param @param error
     * @return void
     * @throws IOException
     * @throws
     * @author 黑暗料理界扛把子
     * @date 2018年5月10日
     */
    @OnError
    public synchronized void onError(@PathParam("userId") String userId, Session session, Throwable error) throws IOException {

    }

    @OnClose
    public synchronized void onClose(@PathParam("userId") String userId,Session session,CloseReason reason){
         System.out.println("退出 socket 连接");
    }
}


