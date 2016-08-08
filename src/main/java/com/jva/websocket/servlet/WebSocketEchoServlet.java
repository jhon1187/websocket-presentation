package com.jva.websocket.servlet;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.jva.websocket.socket.WebSocketEchoCreator;

@WebServlet("webSocketEcho")
public class WebSocketEchoServlet extends WebSocketServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 475543342549620882L;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(0);

		//SimpleEcho
		// factory.register(WebSocketEchoSocket.class);

		//AdvancedEcho
		factory.setCreator(new WebSocketEchoCreator());
	}
}
