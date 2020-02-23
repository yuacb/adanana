package com.adanana.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 聊天室  webSocket
 */
@Component
@ServerEndpoint("/ChatRoom")
public class ChatRoom {
    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session) {

    }


}
