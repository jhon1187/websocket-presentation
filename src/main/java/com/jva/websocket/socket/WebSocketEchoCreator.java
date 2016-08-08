package com.jva.websocket.socket;

import java.util.List;
import java.util.Map;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class WebSocketEchoCreator implements WebSocketCreator {
	private WebSocketEchoSocket webSocketEchoSocket;

	@Override
	public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
		Map<String, List<String>> params = req.getParameterMap();
		
		webSocketEchoSocket = new WebSocketEchoSocket();
		webSocketEchoSocket.setIdUser(String.valueOf(params.get("idUser")));

		return webSocketEchoSocket;
	}
}
