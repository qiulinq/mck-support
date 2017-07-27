package com.test.app;

import org.springframework.stereotype.Component;

import com.xiangjia.base.dto.JSonDtoModel;
import com.xiangjia.common.ex.BaseAssert;
import com.xiangjia.common.ex.ErrorCode;
import com.xiangjia.common.helper.SpringContextHelper;



/**
 * 公共调用App入口
 * <p>完成日期：2016年6月16日</p>
 *
 * @author hj
 * @version 1.0
 */
@Component
public class BaseRpcImpl implements BaseRpc{
    @Override
    public JSonDtoModel execute(JSonDtoModel in, String service) throws Exception {

        try {
			BaseAssert.isNotNull(service, ErrorCode.PARAM_ERROR,"service为空");
			String[] arr = service.split("\\.");
			String serviceName = arr[0];
			String methodName = arr[1];

			BaseAssert.isNotNull(serviceName,ErrorCode.ERROR,"serviceName为空");
			BaseAssert.isNotNull(methodName,ErrorCode.ERROR,"methodName为空");

			String appName = serviceName + "App";

			BaseApp app = SpringContextHelper.getBean(appName,BaseApp.class);
			BaseAssert.isNotNull(app,ErrorCode.ERROR,"app为空");
			JSonDtoModel out =app.execute(in,methodName);
			return out;
		} catch (Exception e) {
			BaseAssert.isTrue(false, ErrorCode.ERROR,e.getMessage());
		}
		return null;
    }
}
