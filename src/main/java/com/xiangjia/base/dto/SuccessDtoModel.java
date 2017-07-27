package com.xiangjia.base.dto;

public class SuccessDtoModel extends ResultDtoModel {

	public SuccessDtoModel() {
		super(true, "");
	}
	public SuccessDtoModel(String message) {
		super(true, message);
	}
}
