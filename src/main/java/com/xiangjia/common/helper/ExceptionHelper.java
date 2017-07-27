package com.xiangjia.common.helper;

import com.xiangjia.base.dto.ResultDtoModel;

public class ExceptionHelper {

	public static ResultDtoModel getExceptionResultDtoModel(Exception e) {

		return new ResultDtoModel(false, "系统错误");
	}
}
