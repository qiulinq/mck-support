package com.test.app;

import org.springframework.web.context.request.async.DeferredResult;

/**
 * 封装spring DeferredResult 类
 * <p>
 * 完成日期：2016年6月15日
 * </p>
 *
 * @author hj
 * @version 1.0
 */
public class AppResult {

	/**
	 * 构造方法
	 * 
	 * @param listener
	 *            回调
	 */
	public AppResult(AckListener listener) {

		this.setResult(new DeferredResult<Object>(BUSI_TIMEOUT));
		this.setListener(listener);
	}

	/**
	 * 构造方法
	 * 
	 * @param deferredResult
	 *            异步对象
	 * @param listener
	 *            回调
	 */
	public AppResult(DeferredResult<Object> deferredResult, AckListener listener) {

		this.setResult(deferredResult);
		this.setListener(listener);
	}

	/**
	 * 异步对象
	 */
	private DeferredResult<Object> result;
	/**
	 * 回调
	 */
	private AckListener listener;

	/**
	 * 构造方法
	 * 
	 * @return
	 */
	public AckListener getListener() {
		return listener;
	}

	public void setListener(AckListener listener) {
		this.listener = listener;
	}

	public DeferredResult<Object> getResult() {
		return result;
	}

	public void setResult(DeferredResult<Object> result) {
		this.result = result;
	}

	// 30秒
	private static final Long BUSI_TIMEOUT = 60 * 1000l;
}
