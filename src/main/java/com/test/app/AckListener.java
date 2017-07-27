package com.test.app;

import com.xiangjia.base.dto.JSonDtoModel;

/**
 * controller异步调用回调接口
 * <p>完成日期：2016年6月15日</p>
 *
 * @author hj
 * @version 1.0
 */
public interface AckListener {
    /**
     * <b>回调接口默认方法.</b><br/>
     * controller调用
     * @param appResult 封装spring DeferredResult 类
     * @param content 异步返回结果
     * @throws Exception
     */
    void onAck(AppResult appResult, JSonDtoModel content) throws Exception;

}
