package com.xiangjia.common.mq;

public interface MqMessageHandlerInterface {
	
	public void handleMessage(MqMessageModel messageModel) throws Exception;
	
}
