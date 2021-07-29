package com.bd.chia.dto;

public class DaemonRequest {
	private String command = "get_blockchain_state";
	private Boolean ack = false;
	private Object data = new Object();
	private String requestId = "1234";
	private String destination = "wallet";
	private String origin = "ui";
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public Boolean getAck() {
		return ack;
	}
	public void setAck(Boolean ack) {
		this.ack = ack;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
