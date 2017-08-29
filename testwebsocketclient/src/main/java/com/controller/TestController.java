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
	 * 显示页面
	 *@param model
	 *@param requeset
	 *@param response
	 *@return 
	 *@author 汪宏
	 *@date 2017年8月16日 下午2:28:26 
	 *@comment
	 */
	@RequestMapping(value="/addclient")
	public ModelAndView addview(HttpServletRequest requeset,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("addclient");
		return mv;
	}
	
	/**
	 * 添加
	 * 
	 *@author 汪宏
	 *@date 2017年8月16日 下午2:31:15 
	 *@comment
	 */
	@ResponseBody
	@RequestMapping(value="/doadd",method = RequestMethod.GET)
	public Object addArray() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8087/mall/queue";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			session.getBasicRemote().sendText("进入"); // 发送文本消息
			
			ReturnBody returnbody = new ReturnBody();
			returnbody.setState(1);
			returnbody.setMessage("成功！");
			
			return returnbody;
		} catch (Exception e) {
			
			e.printStackTrace();
			ReturnBody returnbody = new ReturnBody();
			returnbody.setState(0);
			returnbody.setMessage("失败！");
			return returnbody;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object test() {

		ReturnBody returnbody = new ReturnBody();
		returnbody.setState(0);
		returnbody.setMessage("失败！");
		return returnbody;

	}
	
	
	/**
	 *客户端链接服务器
	 *@param request
	 *@param response 
	 *@author 汪宏
	 *@date 2017年8月16日 上午11:18:36 
	 *@comment
	 */
	
	@RequestMapping(value = "/ws")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
			String uri = "ws://127.0.0.1:8099/testwebsocket/chat";
			Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
			session.getBasicRemote().sendText("回入排队"); // 发送文本消息
			System.out.println("发送成功");
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
