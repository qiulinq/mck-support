package com.test.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * JSON DTO 基类
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public abstract class BaseJSonDtoModel {
    /**
     * 无参构造函数.</br>
     */
    public BaseJSonDtoModel(){}
    /**
     * DTO格式化为字符串.</br>
     * 将dto对象格式化为JSON字符串输出
     * @return JSON字符串
     */
    public String toJSonString() {
        return this.toJSonString(true);
    }

    /**
     * DTO格式化为字符串.</br>
     * 将dto对象格式化为JSON字符串输出</br>
     * @param flag 值为null的属性 是否显示输出,true 是,false 否
     * @return  JSON字符串
     */
    public String toJSonString(boolean flag) {
        if(flag){
            //格式化值为null的属性
            return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else{
            //不要格式化值为null的属性
            return JSON.toJSONString(this);
        }
    }


    /**
     * JSON字符串反序列化为java对象.</br>
     * @param  json JSON格式字符串
     * @param  clz 对象类类型
     * @return 对象类型实例
     */
    public static <T> T restore(String json, Class<T> clz) {
        return (T) JSON.parseObject(json, clz);
    }
}