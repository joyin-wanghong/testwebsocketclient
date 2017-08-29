package com.controller;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.ReturnBody;
import testwebsocketclient.Client;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	/**
	 * ��ʾҳ��
	 *@param model
	 *@param requeset
	 *@param response
	 *@return 
	 *@author ����
	 *@date 2017��8��16�� ����2:28:26 
	 *@comment
	 */
	@RequestMapping(value="/addclient")
	public ModelAndView addview(HttpServletRequest requeset,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("addclient");
		return mv;
	}
	
	/**
	 * ���
	 * 
	 *@author ����
	 *@date 2017��8��16�� ����2:31:15 
	 *@comment
	 */
	@ResponseBody
	@RequestMapping(value="/doadd",method = RequestMethod.GET)
	public Object addArray() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // ��ȡWebSocket�����������о���ʵ�ֿ��Բ���websocket-api.jar��Դ��,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8087/mall/queue";
			Session session = container.connectToServer(Client.class, new URI(uri)); // ���ӻỰ
			session.getBasicRemote().sendText("����"); // �����ı���Ϣ
			
			ReturnBody returnbody = new ReturnBody();
			returnbody.setState(1);
			returnbody.setMessage("�ɹ���");
			
			return returnbody;
		} catch (Exception e) {
			
			e.printStackTrace();
			ReturnBody returnbody = new ReturnBody();
			returnbody.setState(0);
			returnbody.setMessage("ʧ�ܣ�");
			return returnbody;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object test() {

		ReturnBody returnbody = new ReturnBody();
		returnbody.setState(0);
		returnbody.setMessage("ʧ�ܣ�");
		return returnbody;

	}
	
	
	/**
	 *�ͻ������ӷ�����
	 *@param request
	 *@param response 
	 *@author ����
	 *@date 2017��8��16�� ����11:18:36 
	 *@comment
	 */
	
	@RequestMapping(value = "/ws")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // ��ȡWebSocket�����������о���ʵ�ֿ��Բ���websocket-api.jar��Դ��,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8099/testwebsocket/chat";
			Session session = container.connectToServer(Client.class, new URI(uri)); // ���ӻỰ
			session.getBasicRemote().sendText("�����Ŷ�"); // �����ı���Ϣ
			System.out.println("���ͳɹ�");
			//session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Queue<String> queue = new ArrayDeque<String>(); 
		queue.add("wanghong");
		queue.add("lihui");
		String name = queue.poll();
		System.out.println(name);
		
		String name2 = queue.poll();
		System.out.println(name2);
	}

}
