package testwebsocketclient;


import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


public class ClientSocket {

	public static void main(String[]str) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // ��ȡWebSocket�����������о���ʵ�ֿ��Բ���websocket-api.jar��Դ��,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8082/testwebsocket/chat";
			Session session = container.connectToServer(Client.class, new URI(uri)); // ���ӻỰ
			session.getBasicRemote().sendText("123132132131"); // �����ı���Ϣ
			session.getBasicRemote().sendText("4564546");
			System.out.println("���ͳɹ�");
			Thread.sleep(223333333);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
