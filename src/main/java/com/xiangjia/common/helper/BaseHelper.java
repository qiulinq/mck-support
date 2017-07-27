package com.xiangjia.common.helper;

import java.beans.PropertyDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;


/**
 *  Base 工具类
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public class BaseHelper {

    /**
     * <b>查询条件模式化处理.</b><br/>
     *  由业务类对象属性转换为数据库字段
     * @param props
     * @return
     */
    public static Map<String, Object> convertMap(Map<String, Object> props) {
        Map<String, Object> res = new HashMap<>();
        Set<String> keys=props.keySet();

        for(Iterator<String> it=keys.iterator();it.hasNext();){
            String key = it.next();
            Object value = props.get(key);

            res.put(StringHelper.parseCol(key),value);
        }
        return res;
    }
    
    
    public static <T> T map2Model(Map<String, Object> props,Class<T> clazz){
    	T obj = null;
    	try {
		    obj = clazz.newInstance();
		} catch (Exception e) {
			return null;
		} 
    	
    	 Set<String> keys=props.keySet();

         for(Iterator<String> it=keys.iterator();it.hasNext();){
             String key = it.next();
             Object value = props.get(key);
              
             Field f = ReflectionHelper.getDeclaredField(clazz, key);
             if(StringHelper.isNotEmpty(f)){
            	 PropertyDescriptor pd;
				try {
					pd = new PropertyDescriptor(f.getName(), clazz);
				} catch (Exception e) {
					continue;
				}
				if(pd != null){
					 Method set = pd.getWriteMethod();//获得写方法
					 try {
						set.invoke(obj, value);
					} catch (Exception e) {
						continue;
					} 
				}
            	
             }
             
         }
    	
    	return obj;
    }
    
    
    public static String zerofill(Number num,Integer len){
    	String str = String.valueOf(num);
    	if(str.length()>=len){
    		return str;
    	}
    	//1 2
    	for (int i = str.length(); i < len; i++) {
    		str = "0" + str;
		}
    	
    	return str;
    }
    
    public static void main(String[] args) throws IllegalAccessException, Exception {
    	 System.out.println(zerofill(1, 2));
	}
    
    public static String list2String(List<Long> ids){
    	
    	if(ids ==null || ids.size()==0){
    		return "";
    	}
    	String str = "";
    	
    	for (int i = 0; i < ids.size(); i++) {
			Long id = ids.get(i);
			str += "," + id;
		}
    	
    	return str.substring(1);
    }


	public static <T> Map<String, Object> model2Map(T model) throws Exception {
		 Map<String, Object> props = BeanUtils.describe(model);
		 props.remove("class");
		return props;
	}


	public static String list2Str(Collection<String> con) {
		if(con != null && con.size() >0){
			 String str = "";
			 for (Iterator<String> it=con.iterator();it.hasNext();) {
				String item = it.next();
				str += "," + item;
			 }
			 return str.substring(1);
		}else{
			return "";
		}
	}
    
    
}
