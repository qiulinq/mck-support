package com.xiangjia.common.ex;

import java.util.Map;

import com.xiangjia.common.helper.StringHelper;

/**
 * 基础断言处理类
 * <p>完成日期：2016年6月16日</p>
 *
 * @author hj
 * @version 1.0
 */
public class BaseAssert {

    /****************************************isTrue**************************************************/
    public static void isTrue(boolean flag, String message) throws BaseException {
        if(!flag) {
            throw new BaseException(message);
        }
    }
    public static void isTrue(boolean flag,Integer state, String message) throws BaseException {
        if(!flag) {
            throw new BaseException(state,message);
        }
    }
    public static void isTrue(boolean flag, String message,Map<String,Object> msg) throws BaseException {
        if(!flag) {
            throw new BaseException(message,msg);
        }
    }

    public static void isTrue(boolean flag, Integer state,String message,Map<String,Object> msg) throws BaseException {
        if(!flag) {
            throw new BaseException(state,message,msg);
        }
    }

    public static void isTrue(boolean flag,ErrorCode code) throws BaseException {
        if(!flag) {
            throw new BaseException(code.getState(),code.getInfo());
        }
    }

    public static void isTrue(boolean flag,ErrorCode code,Map<String,Object> msg) throws BaseException {
        if(!flag) {
            throw new BaseException(code.getState(),code.getInfo(),msg);
        }
    }
    public static void isTrue(boolean flag,ErrorCode code,String message) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(flag,state,info);
    }

    public static void isTrue(Boolean flag,ErrorCode code,String message,Map<String,Object> msg) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(flag,state,info,msg);
    }
    public static void isTrue(Object object,Integer state, String message) throws BaseException {
        isTrue(StringHelper.isNotEmpty(object),state,message);
    }
    
    /****************************************isNotNull**************************************************/
    public static void isNotNull(Object object, String message) throws BaseException {
       isTrue(StringHelper.isNotEmpty(object),message);
    }

    public static void isNotNull(Object object,String message,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isNotEmpty(object),message,msg);
    }

    public static void isNotNull(Object object, Integer state,String message,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isNotEmpty(object),state,message,msg);
    }

    public static void isNotNull(Object object,ErrorCode code) throws BaseException {
        isTrue(StringHelper.isNotEmpty(object),code);
    }

    public static void isNotNull(Object object,ErrorCode code,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isNotEmpty(object),code,msg);
    }

    public static void isNotNull(Object object,ErrorCode code,String message) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(StringHelper.isNotEmpty(object),state,info);
    }

    public static void isNotNull(Object object,ErrorCode code,String message,Map<String,Object> msg) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(StringHelper.isNotEmpty(object),state,info,msg);
    }
    
    
    /****************************************isNull**************************************************/
    public static void isNull(Object object, String message) throws BaseException {
       isTrue(StringHelper.isEmpty(object),message);
    }

    public static void isNull(Object object,String message,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isEmpty(object),message,msg);
    }

    public static void isNull(Object object, Integer state,String message,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isEmpty(object),state,message,msg);
    }

    public static void isNull(Object object,ErrorCode code) throws BaseException {
        isTrue(StringHelper.isEmpty(object),code);
    }

    public static void isNull(Object object,ErrorCode code,Map<String,Object> msg) throws BaseException {
        isTrue(StringHelper.isEmpty(object),code,msg);
    }

    public static void isNull(Object object,ErrorCode code,String message) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(StringHelper.isEmpty(object),state,info);
    }

    public static void isNull(Object object,ErrorCode code,String message,Map<String,Object> msg) throws BaseException {
        Integer state = code.getState();
        String info = code.getInfo();
        info = info.replace("[MESSAGE]",message);
        isTrue(StringHelper.isEmpty(object),state,info,msg);
    }
}
