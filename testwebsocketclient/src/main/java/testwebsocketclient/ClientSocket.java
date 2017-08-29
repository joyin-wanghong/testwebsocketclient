package testwebsocketclient;


import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


public class ClientSocket {

	public static void main(String[]str) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			//String uri = "ws://127.0.0.1:8082/testwebsocket/chat";
			String uri = "ws://192.168.1.74:8087/mall/queue";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			session.getBasicRemote().sendText("123132132131"); // 发送文本消息
			session.getBasicRemote().sendText("4564546");
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
