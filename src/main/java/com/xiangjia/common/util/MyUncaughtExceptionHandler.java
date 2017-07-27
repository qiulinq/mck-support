package com.xiangjia.common.util;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		System.out.println("This is:" + thread.getName() + ",Message:"
		        + throwable.getMessage());
		throwable.printStackTrace();
	}

}
