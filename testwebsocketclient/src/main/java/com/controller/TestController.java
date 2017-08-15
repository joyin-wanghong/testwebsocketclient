package com.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import testwebsocketclient.Client;

@Controller
@RequestMapping(value = "/test")
public class TestController {


	@RequestMapping(value = "/ws")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8082/testwebsocket/chat";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			session.getBasicRemote().sendText("123132132131"); // 发送文本消息
			session.getBasicRemote().sendText("4564546");
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
