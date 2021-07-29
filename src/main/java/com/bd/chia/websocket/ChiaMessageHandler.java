package com.bd.chia.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class ChiaMessageHandler { // extends StompSessionHandlerAdapter {

//	@Override
//	public Type getPayloadType(StompHeaders headers) {
//		// TODO Auto-generated method stub
//		return super.getPayloadType(headers);
//	}
//
//	@Override
//	public void handleFrame(StompHeaders headers, Object payload) {
//		// TODO Auto-generated method stub
//		super.handleFrame(headers, payload);
//	}
//
//	@Override
//	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//		// TODO Auto-generated method stub
//		System.out.println("afterConnected");
//		super.afterConnected(session, connectedHeaders);
//	}
//
//	@Override
//	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
//			Throwable exception) {
//		// TODO Auto-generated method stub
//		super.handleException(session, command, headers, payload, exception);
//	}
//
//	@Override
//	public void handleTransportError(StompSession session, Throwable exception) {
//		// TODO Auto-generated method stub
//		exception.printStackTrace();
//		super.handleTransportError(session, exception);
//	}

}
