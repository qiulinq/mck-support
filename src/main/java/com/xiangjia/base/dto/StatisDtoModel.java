package com.xiangjia.base.dto;

import java.util.List;

public class StatisDtoModel {

	private String[] xAxis;// x轴
	private List<DataModel> dataModel;// 数据

	public String[] getxAxis() {
		return xAxis;
	}

	public void setxAxis(String[] xAxis) {
		this.xAxis = xAxis;
	}

	public List<DataModel> getDataModel() {
		return dataModel;
	}

	public void setDataModel(List<DataModel> dataModel) {
		this.dataModel = dataModel;
	}

}
