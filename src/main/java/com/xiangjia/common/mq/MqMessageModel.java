package com.xiangjia.common.mq;

import java.sql.Timestamp;

public class MqMessageModel {
	
	public static final Integer RECIVE_MICRO_MESSAGE = 1;
	
	public static final Integer SEND_ADV_MESSAGE = 2;
	
	private Integer messageType;//消息类型  1:表示接收到的微信发送的消息   2：表示向微信社群发送消息
	
	private String messageText;//消息文本
	
	private Timestamp createDate;//消息创建时间
	
	private String destination;//消息目的地
	

	public MqMessageModel(Integer messageType,String destination, String messageText, Timestamp createDate) {
		super();
		this.messageType = messageType;
		this.messageText = messageText;
		this.createDate = createDate;
		this.destination = destination;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "MqMessageModel [messageType=" + messageType + ", messageText=" + messageText + ", createDate=" + createDate + ", destination=" + destination + "]";
	}
}
