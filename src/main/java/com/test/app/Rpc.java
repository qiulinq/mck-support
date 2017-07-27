package com.test.app;

import com.xiangjia.base.dto.JSonDtoModel;

/**
 * 公共调用App入口
 * <p>完成日期：2016年6月16日</p>
 *
 * @author hj
 * @version 1.0
 */
public interface Rpc {

    /**
     * <b>公共入口.</b><br/>
     * 从controller调用过来的接口统一公用入口
     * @param in 入参
     * @param service 调用service方法入口     * @return out 出参
     * @throws Exception
     */
    JSonDtoModel execute(JSonDtoModel in, String service) throws Exception;
}
