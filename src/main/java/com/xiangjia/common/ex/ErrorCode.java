package com.xiangjia.common.ex;
/**
 * 异常代码
 * <p>完成日期：2016年6月16日</p>
 *
 * @author hj
 * @version 1.0
 */
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS(0,"成功"),
    /**
     * 系统异常
     */
    ERROR(-1,"错误([MESSAGE])"),
    PARAM_ERROR(1,"参数错误([MESSAGE])");

    /**
     * 异常状态
     */
    private Integer state;
    /**
     * 异常信息
     */
    private String info;

    /**
     * 构造方法
     * @param state 异常状态
     * @param info 异常信息
     */
    private ErrorCode(int state, String info) {
        this.info = info;
        this.state = state;
    }

    /********************************get set***********************************/
    /**
     * 异常状态
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 异常状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 异常信息
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * 异常信息
     */
    public void setInfo(String info) {
        this.info = info;
    }

}
