package com.xiangjia.common.ex;

import java.util.HashMap;
import java.util.Map;
/**
 * 基础异常实现类
 * <p>完成日期：2016年6月16日</p>
 *
 * @author hj
 * @version 1.0
 */
public class BaseException extends Exception {

    protected Integer state = 0;
    protected Map<String,Object> msg=new HashMap<>();

    public BaseException(){
        super(ErrorCode.ERROR.getInfo());
        this.state = ErrorCode.ERROR.getState();
    }
    public BaseException(String info){
        super(info);
        this.state = ErrorCode.ERROR.getState();
    }

    public BaseException(Integer state,String info){
        this(info);
        this.state = state;
    }
    public BaseException(String info,Map<String,Object> msg){
        this(info);
        this.msg=msg;
    }
    public BaseException(Integer state,String info,Map<String,Object> msg){
        this(state,info);
        this.msg=msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }
}
