package com.xiangjia.common.helper;

import java.math.BigDecimal;

/**
 *  类型转换 工具类
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public class ConvertHelper {
    /**
     * 无参构造方法
     */
    public ConvertHelper(){
    }
    /**
     * 字符串转换为int
     * @param str 字符串
     * @return 整数
     */
    public static Integer str2Int(String str) {
        try {
            return Integer.valueOf(str.trim());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static BigDecimal str2BigDecimal(String str){
    	
    	if(StringHelper.isEmpty(str.trim())){
    		return new BigDecimal(0.00);
    	}
    	Double d = Double.valueOf(str);
    	return new BigDecimal(d);
    }

    /**
     * 字符串转换为int
     * @param str 字符串
     * @param d 默认值
     * @return 整数
     */
    public static Integer str2Int(String str, Integer d) {
        try {
            return Integer.valueOf(str.trim());
        } catch (Exception e) {
            return d;
        }
    }

    /**
     * 字符串转换为Long
     * @param str 字符串
     * @return Long
     */
    public static Long str2Long(String str) {
        try {
            return Long.valueOf(str.trim());
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 字符串转换为Long
     * @param str 字符串
     * @param d 默认值
     * @return Long
     */
    public static Long str2Long(String str, Long d) {
        try {
            return Long.valueOf(str.trim());
        } catch (Exception e) {
            return d;
        }
    }
}
