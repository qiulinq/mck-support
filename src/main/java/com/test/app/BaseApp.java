package com.test.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xiangjia.base.dto.JSonDtoModel;
import com.xiangjia.common.ex.BaseAssert;
import com.xiangjia.common.ex.ErrorCode;
import com.xiangjia.common.helper.ReflectionHelper;

/**
 * 基础调用接口规范
 * <p>完成日期：2016年6月12日</p>
 *
 * @author hj
 * @version 1.0
 */
public abstract  class BaseApp{
    /**
     * service 具体实现业务逻辑
     */
    protected RpcService service;

    /**
     * 设置 service
     * @param service
     */
    public void setService(RpcService service) {
        this.service = service;
    }

    /**
     * <b>通用入口.</b><br/>
     * 从controller调用过来的接口统一公用入口<br/>
     * 不可被重写
     * @param in 入参
     * @return out 出参
     * @throws Exception
     */
    public final JSonDtoModel execute(JSonDtoModel in,String invoke) throws Exception {
        JSonDtoModel out = new JSonDtoModel();

 
        //统一设置当前登陆用户
        /* 因为service里传入参数是JsonDtoModel 所以不再需要将 userId放在线程资源中
         Long userId = in.getLongByKey(Constant.USER_ID);
        String user = in.getStringByKey(Constant.USER);
        if(StringHelper.isNotEmpty(userId) && UserManager.get() == null){
            UserManager.put(userId,user);
        }*/
        
        
        //在调用业务逻辑之前校验逻辑
        //主要用于有些接口要求必须登陆来调用,验证是否登陆,
        validate(in);
        //调用具体业务
        execute(in, out,invoke);
        return out;
    }

    /**
     * <b>校验逻辑.</b><br/>
     * 在调用业务逻辑之前校验逻辑,可以被重写
     * @param in 入参
     * @throws Exception
     */
    public void validate(JSonDtoModel in) throws Exception{
    }

    protected void execute(JSonDtoModel in,JSonDtoModel out,String invoke) throws Exception{
        Method method = null;
        try {
            method = ReflectionHelper.getDeclaredMethod(service.getClass(), invoke, JSonDtoModel.class,JSonDtoModel.class);
        } catch (Exception e) {
            BaseAssert.isTrue(false, ErrorCode.ERROR, invoke + "不存在");
        }
        BaseAssert.isNotNull(method,ErrorCode.ERROR,invoke+"不存在");

        try {
           method.invoke(service, in,out);
        } catch (InvocationTargetException e) { 
           e.getTargetException().printStackTrace();;
           throw new Exception(e.getTargetException().getMessage());
        }
    }
}
