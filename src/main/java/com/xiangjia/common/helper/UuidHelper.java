package com.xiangjia.common.helper;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * <p>文件名称: UuidHelper.java </p>
 * <p>文件描述:uuid工具类</p>
 * <p>版权所有: 版权所有(C)2015</p>
 * <p>公   司: 重庆翔龙科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2016-5-4</p>
 *
 * @author mike
 * @version 1.0
 */
public class UuidHelper {
    /**
     * 无参构造方法
     */
    public UuidHelper(){
    }
    /**
     * 随机变量
     */
    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     * @return UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     * @return GUID
     */
    public static String GUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     * @return long
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
}
