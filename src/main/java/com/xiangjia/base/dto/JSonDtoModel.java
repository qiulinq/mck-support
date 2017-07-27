package com.xiangjia.base.dto;

import static com.alibaba.fastjson.JSON.parseObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiangjia.common.helper.StringHelper;

/**
  * JSON DTO 实现类
  * <p>完成日期：2016年6月12日</p>
  *
  * @author hj
  * @version 1.0
  */
public class JSonDtoModel extends BaseJSonDtoModel implements Cloneable, Serializable {

    /**
     * 业务数据存取空间
     */
    private Map<String, Object> params = new HashMap();
    /**
     * 状态
     */
    private int state = 0;
    /**
     * 消息类型
     */
    private String info = "成功";
    /**
     * 无参构造函数
     */
    public JSonDtoModel(){
    }
    /**
     * DTO还原
     */
    public void clear() {
        this.state = 0;
        this.info = "成功";
        this.params.clear();
    }

    /**
     * 按key查询对象.</br>
     * @param  key 键
     * @return  对象类型值
     */
    public Object getByKey(String key) {
        return getParams().get(key);
    }

    /**
     * 按key返回字符串.</br>
     * @param key 键
     * @return 字符串
     */
    public String getStringByKey(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            Object res = getByKey(key);

            if (StringHelper.isEmpty(res)) {
                return null;
            }
            if(res instanceof JSONObject){
               return JSON.toJSONString(res, SerializerFeature.WriteMapNullValue);
            }

            if(res instanceof JSONArray){
                return JSON.toJSONString(res, SerializerFeature.WriteMapNullValue);
            }

            if(res instanceof Integer
                || res instanceof Byte
                || res instanceof Short
                || res instanceof Long
                || res instanceof Double
                || res instanceof String
                || res instanceof Float
                || res instanceof Boolean
                    ){
                return String.valueOf(res);
            }

            return JSON.toJSONString(res,SerializerFeature.WriteMapNullValue);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按key返回字符串.</br>
     * @param key 键
     * @param def 默认值
     * @return 字符串
     */
    public String getStringByKey(String key, String def) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        String str = getStringByKey(key);
        return str == null ? def : str;
    }

    /**
     * 按key返回JSONObject.</br>
     * @param key 键
     * @return JSONObject
     */
    public JSONObject getJSONObject(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        String str = getStringByKey(key);
        return StringHelper.isEmpty(str) ? parseObject("{}") : parseObject(str);
    }

    /**
     * 按key返回Long.</br>
     * @param key 键
     * @return Long
     */
    public Long getLongByKey(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            String str = getStringByKey(key);
            return  Long.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 按key返回Long.</br>
     * 带默认值,如果结果为空则返回默认值
     * @param key 键
     * @param def 默认值
     * @return Long
     */
    public Long getLongByKey(String key,Long def) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            Long l = getLongByKey(key);
            return  l == null ? def:l;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按key返回Integer.</br>
     * @param key 键
     * @return Integer
     */
    public Integer getIntByKey(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            String str = getStringByKey(key);
            return Integer.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 按key返回Integer.</br>
     * 带默认值,如果结果为空则返回默认值
     * @param key 键
     * @param def 默认值
     * @return Integer
     */
    public Integer getIntByKey(String key, int def) {
        try {
            Integer i = getIntByKey(key);
            return i == null ? def : i;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 按key返回Float.</br>
     * @param key 键
     * @return Float
     */
    public Float getFloatByKey(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            String str = getStringByKey(key);
            return Float.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 按key返回Float.</br>
     * 带默认值,如果结果为空则返回默认值
     * @param key 键
     * @return value 值
     */
    public Float getFloatParamByKey(String key,Float def) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        try {
            Float f = getFloatByKey(key);
            return f == null?def:f;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean getBooleanByKey(String key){
    	//参数校验
        if(StringHelper.isEmpty(key)) {
            return false;
        }
        
        try {
			return Boolean.valueOf(getStringByKey(key));
		} catch (Exception e) {
			return false;
		}
    }
    /**
     * 业务数据存入.</br>
     * @param key key 键
     * @param value 值
     */
    public void put(String key, Object value) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return;
        }
        getParams().put(key, value);
    }

    /**
     * 按key,获取类类型实例.</br>
     * @param key 键
     * @param clz 类类型
     * @param <T> 类类型
     * @return 类实例
     */
    public <T> T getObjectByKey(String key, Class<T> clz) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        if(StringHelper.isEmpty(clz)) {
            return null;
        }
        Object value = getParams().get(key);
        if (StringHelper.isEmpty(value)) {
            return null;
        }
        if (value instanceof String) {
            T o = parseObject(String.valueOf(value), clz);
            return o;
        } else if (value instanceof JSONObject) {
            T o = parseObject(JSON.toJSONString(value, SerializerFeature.WriteMapNullValue), clz);
            return o;
        } else {
            T o = BaseJSonDtoModel.restore(JSON.toJSONString(value, SerializerFeature.WriteMapNullValue), clz);
            return o;
        }
    }

    /**
     * 按key,类类型返回类实例Map列表.</br>
     * @param key 键
     * @param clz 类类型
     * @param <T> 类类型
     * @return 实例Map列表
     */
    public <T> List<Map<String, T>> getArrayMapByKey(String key, Class<T> clz) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        if(StringHelper.isEmpty(clz)) {
            return null;
        }
        List<Map> list = getArrayByKey(key, Map.class);
        List<Map<String, T>> result = new ArrayList<Map<String, T>>();

        if(StringHelper.isEmpty(list) || list.size() ==0) return result;
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Map<String, T> res = new HashMap<String, T>();
            Set<Map.Entry<String,Object>> entrySet = map.entrySet();

            for(Iterator<Map.Entry<String,Object>> it = entrySet.iterator();it.hasNext();){
                Map.Entry<String,Object> entry = it.next();
                String keyName= entry.getKey();
                Object value = entry.getValue();
                if(value instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) value;
                    T t = JSON.parseObject(JSON.toJSONString(jsonObject,SerializerFeature.WriteMapNullValue),clz);
                    res.put(keyName,t);
                }else{
                    res.put(keyName,(T)value);
                }
            }
            result.add(res);
        }
        return result;
    }
    
    
    /**
     * 按key,类类型返回类实例Map列表.</br>
     * @param key 键
     * @param clz 类类型
     * @param <T> 类类型
     * @return 实例Map列表
     */
    public static <T> List<Map<String, T>> str2ArrayMap(String str, Class<T> clz) {
        //参数校验
        if(StringHelper.isEmpty(str)) {
            return null;
        }
        if(StringHelper.isEmpty(clz)) {
            return null;
        }
        List<Map> list = JSONArray.parseArray(str,Map.class);
        List<Map<String, T>> result = new ArrayList<Map<String, T>>();

        if(StringHelper.isEmpty(list) || list.size() ==0) return result;
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Map<String, T> res = new HashMap<String, T>();
            Set<Map.Entry<String,Object>> entrySet = map.entrySet();

            for(Iterator<Map.Entry<String,Object>> it = entrySet.iterator();it.hasNext();){
                Map.Entry<String,Object> entry = it.next();
                String keyName= entry.getKey();
                Object value = entry.getValue();
                if(value instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) value;
                    T t = JSON.parseObject(JSON.toJSONString(jsonObject,SerializerFeature.WriteMapNullValue),clz);
                    res.put(keyName,t);
                }else{
                    res.put(keyName,(T)value);
                }
            }
            result.add(res);
        }
        return result;
    }
    

    /**
     * 按key返回Map列表.</br>
     * @param key 键
     * @return Map列表
     */
    public  List<Map<String, Object>> getArrayMapByKey(String key) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        return getArrayMapByKey(key,Object.class);
    }
    /**
     * 按key,返回类类型实例列表.</br>
     * @param key 键
     * @param clz 类类型
     * @param <T> 类类型
     * @return 实例列表
     */
    public <T> List<T> getArrayByKey(String key, Class<T> clz) {
        //参数校验
        if(StringHelper.isEmpty(key)) {
            return null;
        }
        if(StringHelper.isEmpty(clz)) {
            return null;
        }
        Object value = getParams().get(key);
        if (StringHelper.isEmpty(value)) {
            return null;
        }
        if (value instanceof String) {
            List<T> list = JSON.parseArray(String.valueOf(value), clz);
            return list;
        } else if (value instanceof JSONArray) {
            List<T> list = JSON.parseArray(JSON.toJSONString(value,SerializerFeature.WriteMapNullValue), clz);
            return list;
        } else {
            List<T> list = JSON.parseArray(JSON.toJSONString(value,SerializerFeature.WriteMapNullValue), clz);
            return list;
        }
    }

    /**
     * json格式化为数据传输对象.</br>
     * @param json json字符串
     * @return 数据传输对象
     */
    public static JSonDtoModel restore(String json) {

        JSonDtoModel model = (JSonDtoModel) parseObject(json, JSonDtoModel.class);
        return model;
    }

    /**
     * 格式化业务参数.</br>
     * @param json json字符串
     */
    public void restoreParams(String json) {
        this.params = ((Map) parseObject(json, Map.class));
    }

    /**
     * 数据传输对象转换为JSON字符串.</br>
     * @return JSON
     */
    public String paramsToJSonString() {
        return JSON.toJSONString(this.params);
    }




    /**
     * 克隆.</br>
     * @return 对象实例
     */
    public JSonDtoModel clone() {
        JSonDtoModel o = null;
        try {
            o = (JSonDtoModel) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return o;
    }

    /**
     * 克隆.</br>
     */
    public void clone(JSonDtoModel in) {
        for (String key : in.getParams().keySet()) {
            put(key, in.getParams().get(key));
        }
    }
    /**
     * toString 方法.</br>
     * @return 字符串
     */
    public String toString() {
        return JSONObject.toJSONString(this);
    }


    /*******************************************get set*********************************************************/

    /**
     * 业务数据存取空间.</br>
     */
    public Map<String, Object> getParams() {
        return this.params;
    }

    /**
     * 业务数据存取空间.</br>
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * 状态
     */
    public int getState() {
        return this.state;
    }

    /**
     * 状态
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 消息类型
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * 消息类型
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
     
}