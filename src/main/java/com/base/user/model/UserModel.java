package com.base.user.model;

import com.xiangjia.base.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * åºç¡CRUDåºæ¯ 业务模型
 * <p>
 * 完成日期：2016-10-08
 * </p>
 * <p>
 * 邮件：1210046812@qq.com
 * </p>
 * 
 * @author qiulinq
 * @version 1.1
 */
public class UserModel extends BaseModel {

	/************* 属性 ***********/
	/**
	 * 登录帐号
	 */
	private String account;
	/** ="" **/
	/**
	 * 密码
	 */
	private String password;
	/** ="" **/
	/**
	 * 姓名
	 */
	private String realname;
	/** ="" **/
	/**
	 * 手机
	 */
	private String phone;
	/** ="" **/
	/**
	 * 状态
	 */
	private Integer status;

	/** =0 **/

	/************* set get 方法 ***********/
	/**
	 * 获取登录帐号
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * 设置登录帐号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取姓名
	 */
	public String getRealname() {
		return this.realname;
	}

	/**
	 * 设置姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 获取手机
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置手机
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取状态
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	private String roles;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
