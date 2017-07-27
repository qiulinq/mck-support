package com.xiangjia.base.model;

import java.sql.Timestamp;

import com.base.user.model.UserModel;
import com.xiangjia.common.cache.CacheUtils;
import com.xiangjia.common.helper.DateHelper;
import com.xiangjia.common.util.Constants;
import com.xiangjia.common.util.SessionUtil;

/**
 * mybatis 基础数据模型
 * <p>
 * 完成日期：2016年6月12日
 * </p>
 * 
 * @author hj
 * @version 1.0
 */
public class BaseModel {
	/**
	 * 注解
	 */
	private Long id;

	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	private Long createUserId;// 创建人ID

	private Timestamp createTime;// 创建人

	private Long updateUserId;// 修改人id

	private Timestamp updateTime;// 修改人

	private Integer active = Constants.COMMON_ACTIVE_TRUE;// 状态

	public Long getCreateUserId() {
		if (id == null) {
			return SessionUtil.getUserId();
		}
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getCreateTime() {
		if (id == null) {
			return DateHelper.getTimestampNow();
		}
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		if (updateUserId == null) {
			return SessionUtil.getUserId();
		}
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Timestamp getUpdateTime() {
		if (updateTime == null) {
			return DateHelper.getTimestampNow();
		}
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateUserName() {

		return getUsername(createUserId);
	}

	public String getUpdateUserName() {
		return getUsername(updateUserId);
	}

	private String getUsername(Long userId) {
		UserModel model = CacheUtils.getCache(userId, UserModel.class);
		if (model != null) {
			return model.getRealname();
		}
		return "";
	}

	public String getStatusStr() {
		if (this.status == null) {
			return "";
		}
		if (this.status - 1 == 0) {
			return getSuccessStatus("有效");
		}
		if (this.status - 0 == 0) {
			return getFailStatus("无效");
		}
		return "";
	}

	protected String getSuccessStatus(String message) {

		return "<span class='label label-success'>" + message + "</span>";
	}

	protected String getFailStatus(String message) {

		return "<span class='label label-default'>" + message + "</span>";
	}

}
