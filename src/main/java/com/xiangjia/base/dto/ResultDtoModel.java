package com.xiangjia.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDtoModel {

	private String code = "";

	private String message;

	private boolean success;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List dataList = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "ResultDtoModel [code=" + code + ", message=" + message + ", success=" + success + ", dataMap=" + dataMap + ", dataList=" + dataList + "]";
	}

	public ResultDtoModel(boolean success, String code, String message, Map<String, Object> dataMap) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
		this.dataMap = dataMap;
	}

	public ResultDtoModel(boolean success, String code, String message) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
	}

	public ResultDtoModel(boolean success, String message) {
		super();
		this.message = message;
		this.success = success;
	}

	public ResultDtoModel() {
		super();
	}

}
