package com.xiangjia.common.helper;

public class CodeHelper {
	
	private static final Integer ICITEM_LEVEL1_CODE_LENGTH = 2;
	
	private static final Integer ICITEM_LEVEL2_CODE_LENGTH = 3;
	
	public static String getIcitemTypeCode(Long level1Id,Long id){
		String code = "";
		if(level1Id != null){
			code = BaseHelper.zerofill(level1Id, ICITEM_LEVEL1_CODE_LENGTH) + "." + BaseHelper.zerofill(id, ICITEM_LEVEL2_CODE_LENGTH);
		}else{
			code = BaseHelper.zerofill(id, ICITEM_LEVEL1_CODE_LENGTH);
		}
		return code;
	}
}
