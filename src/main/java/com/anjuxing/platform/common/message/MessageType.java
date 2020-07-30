package com.anjuxing.platform.common.message;

public enum MessageType {

	HAND_REQ(0), 
	HAND_RESP(1), 
	HEARTBEAT_REQ(2), 
	HEARTBEAT_RESP(3), 
	CLIENT_SERVICE_REQ(4), 
	CLIENT_SERVICE_RESP(5), 
	SERVER_SERVICE_REQ(6), 
	SERVER_SERVICE_RESP(7), 
	DATA_SERVICE_REQ(8), 
	DATA_SERVICE_RESP(9);

	private int value;

	private MessageType(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}
