package com.xiangjia.common.helper;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * java反射工具类
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public class ReflectionHelper {

    /**
     * 获取泛型参数类型
     * @param clazz 类类型
     * @param <T> 类类型
     * @return 对象类型
     */
    public static <T> Class<T> getClassGenricType(final Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    /**
     * 获取泛型参数类型
     * @param clazz 类类型
     * @param index 参数索引
     * @return 类类型
     */
    public static Class getClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
    
    /**
     *  获取方法对象
     * @param clazz 类类型
     * @param methodName 方法名
     * @param parameterTypes 类型
     * @return 方法
     */
    public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?> ... parameterTypes){
        Method method = null ;

        for( ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes) ;
                return method ;
            } catch (Exception e) {
            }
        }

        return null;
    }
    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * @param clazz : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName){
        Field field = null ;
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName) ;
                return field ;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }
        return null;
    }

}
