package com.jva.websocket.socket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketEchoSocket {

	private Session session;

	private String idUser;

	private static Set<WebSocketEchoSocket> users;
	
	public WebSocketEchoSocket() {
		if(users == null){
			users = new HashSet<WebSocketEchoSocket>();
		}
	}

	@OnWebSocketConnect
	public void handleConnect(Session session) {
		this.session = session;
		users.add(this);
	}

	@OnWebSocketClose
	public void handleClose(int statusCode, String reason) {
		stop();
	}

	// called when a message received from the browser
	@OnWebSocketMessage
	public void handleMessage(String message) {
		send(message);
	}

	// called in case of an error
	@OnWebSocketError
	public void handleError(Throwable error) {
		error.printStackTrace();
	}

	// sends message to client
	public static void send(String message) {
		try {
			if (users != null) {
				for (WebSocketEchoSocket user : users) {

					user.session.getRemote().sendString(message);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// closes the socket
	private void stop() {
		try {
			session.disconnect();
			users.remove(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
}
