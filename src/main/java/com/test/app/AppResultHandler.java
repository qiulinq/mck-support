package com.test.app;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import com.xiangjia.base.dto.JSonDtoModel;
import com.xiangjia.base.dto.SuccessDtoModel;
import com.xiangjia.common.ex.BaseException;
import com.xiangjia.common.helper.SpringContextHelper;
import com.xiangjia.common.helper.StringHelper;

/**
 * 异步调用实现
 * <p>完成日期：2016年6月15日</p>
 *
 * @author hj
 * @version 1.0
 */
public class AppResultHandler extends HashMap<String, AppResult> {
    /**
     * 日志工具
     */
    private final static Logger logger = LoggerFactory.getLogger(AppResultHandler.class);

    /**
     * <b>异步调用路由.</b><br/>
     * 异步调用路由
     * @param in 入参
     * @param service 目标接口
     * @param listener 回调
     * @return 异步对象
     * @throws Exception
     */
    public DeferredResult<Object> localToApp(JSonDtoModel in,String service, AckListener listener) throws Exception {
        String context = UUID.randomUUID().toString();
        AppResult result = this.saveResult(context,listener);
        callService(in,service,context);
        return result.getResult();
    }
    public DeferredResult<Object> localToApp(HttpServletRequest request,JSonDtoModel in,String service, AckListener listener) throws Exception {
        String context = UUID.randomUUID().toString();
        AppResult result = this.saveResult(context,listener);
        callService(request,in,service,context);
        return result.getResult();
    }
    /**
     * <b>异步调用路由.</b><br/>
     * 异步调用路由
     * @param in 入参
     * @param service 目标接口
     * @param context 异步对象名柄
     */
    private void callService(final JSonDtoModel in, final String service, final String context) {
        
//        Long userId = SessionUtils.getUserId();
//        String user = SessionUtils.getRealName();
//        List<String> roleList = SessionUtils.getRoleList();
//
//        Long deptId = SessionUtils.getDeptId();
//        String deptName = SessionUtils.getDeptName();
//        
//        in.put(Constant.USER_ID, userId);
//        in.put(Constant.USER, user);
//        in.put(Constant.ROLE_LIST, roleList);
//        in.put(Constant.DEPT_ID, deptId);
//        in.put(Constant.DEPT_NAME, deptName);
        
    	
        logger.info("调用接口["+service+"]开始");
        logger.info("输入参数["+in.toJSonString()+"]");

        /**
         * 启动线程,开始异步路由
         */
        class Call extends Thread{
            @Override
            public void run() {
                BaseRpc rpc =  SpringContextHelper.getBean(BaseRpc.class);

                AppResult result = AppResultHandler.getInstance().get(context);
                JSonDtoModel out = new JSonDtoModel();
                try {
                    out=rpc.execute(in,service);
                    logger.info("调用接口["+service+"]成功");
                    logger.info("返回参数["+out.toJSonString()+"]");

                    result.getListener().onAck(result,out);
                } catch (Exception e) {
                    logger.info("调用接口["+service+"]失败",e); 
                    if (e instanceof BaseException){
                        BaseException be = (BaseException)e;
                        out.setState(be.getState());
                        out.setInfo(be.getMessage());
                        out.setParams(be.getMsg());
                    }else {
                        out.setState(-1);
                        out.setInfo(e.getMessage());
                    }

                    try {
                    	result.getListener().onAck(result,out);
                    } catch (Exception e1) {
                        logger.info("调用接口["+service+"]失败");
                        result.getResult().setResult(new SuccessDtoModel("测试返回"));
                    }
                }
            }
        }

        new Call().start();
    }
    private void callService(final HttpServletRequest request,final JSonDtoModel in, final String service, final String context) {

//        Long userId = SessionUtils.getUserId();
//        String user = SessionUtils.getRealName();
//        List<String> roleList = SessionUtils.getRoleList();
//
//        Long deptId = SessionUtils.getDeptId();
//        String deptName = SessionUtils.getDeptName();
//
//        in.put(Constant.USER_ID, userId);
//        in.put(Constant.USER, user);
//        in.put(Constant.ROLE_LIST, roleList);
//        in.put(Constant.DEPT_ID, deptId);
//        in.put(Constant.DEPT_NAME, deptName);


        logger.info("调用接口["+service+"]开始");
        logger.info("输入参数["+in.toJSonString()+"]");

        /**
         * 启动线程,开始异步路由
         */
        class Call extends Thread{
            @Override
            public void run() {
                BaseRpc rpc =  SpringContextHelper.getBean(BaseRpc.class);

                AppResult result = AppResultHandler.getInstance().get(context);
                JSonDtoModel out = new JSonDtoModel();
                try {
                    out=rpc.execute(in,service);
                    logger.info("调用接口["+service+"]成功");
                    logger.info("返回参数["+out.toJSonString()+"]");

                    result.getListener().onAck(result,out);
                } catch (Exception e) {
                    logger.info("调用接口["+service+"]失败",e);
                    int state = 0;
                    String info = null;
                    if (e instanceof BaseException){
                        BaseException be = (BaseException)e;
                        state = be.getState();
                        info = be.getMessage();
                    }else {
                        state = -1;
                        info = e.getMessage();
                    }
                    String requestType = "";
                    if(request == null){
                        out.setState(state);
                        out.setInfo(info);
                        result.getResult().setResult(out);
                    }else{
                    	requestType = request.getHeader("X-Requested-With");
                    }
                    

                    if(StringHelper.isEmpty(requestType)){
                        ModelAndView mv = new ModelAndView();
                        mv.addObject("state",state);
                        mv.addObject("ex",info);
                        mv.setViewName("error");
                        result.getResult().setResult(mv);
                    }else {
                        out.setState(state);
                        out.setInfo(info);
                        result.getResult().setResult(out);
                    }
                }
            }
        }

        new Call().start();
    }
    /**
     * <b>构造异步对象.</b><br/>
     * 构造异步对象
     * @param context
     * @param listener
     * @return
     */
    private AppResult saveResult(String context,AckListener listener) {

        class OnResult implements Runnable {

            private boolean timeout;
            private String context;

            public OnResult(String context, boolean timeout) {
                this.context = context;
                this.timeout = timeout;
            }

            @Override
            public void run() {
                try {
                    if (this.timeout) {
                        AppResult result = AppResultHandler.getInstance().get(this.context);
                        result.getResult().setResult(new ModelAndView("timeout"));
                    }
                    // 从等待队列移除
                    AppResultHandler.getInstance().remove(this.context);
                } catch (Throwable e) {
                    logger.error("返回失败",e);
                }
            }
        }

        synchronized (getLocker()) {

            AppResult result = new AppResult(listener);
            result.getResult().onCompletion(new OnResult(context, false));
            result.getResult().onTimeout(new OnResult(context, true));

            // 加入等待回应
            this.put(context, result);

            return result;
        }
    }

    private Object locker = new Object();

    public Object getLocker() {
        return locker;
    }

    public void setLocker(Object locker) {
        this.locker = locker;
    }

    private static AppResultHandler instance = new AppResultHandler();

    public static AppResultHandler getInstance() {
        return instance;
    }

    public static void setInstance(AppResultHandler instance) {
        AppResultHandler.instance = instance;
    }

    private final static String BUSI_PRFIX = "WEB_";
}
