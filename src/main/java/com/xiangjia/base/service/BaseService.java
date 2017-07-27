package com.xiangjia.base.service;

import java.util.List;
import java.util.Map;

import com.test.app.RpcService;
import com.xiangjia.base.dto.BasePageDtoModel;
import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.base.model.BaseModel;

/**
 * mybatis 基础 service 接口
 * <p>
 * 完成日期：2016年6月12日
 * </p>
 * 
 * @author hj
 * @version 1.0
 */
public interface BaseService<T extends BaseModel> extends RpcService {
	ResultDtoModel save(T model) throws Exception;// 保存

	ResultDtoModel delete(Long id) throws Exception;// 删除

	ResultDtoModel update(T model) throws Exception;// 修改

	T queryModelById(Long id) throws Exception;// 根据ID查询实体对象

	List<T> queryModelList(Map<String, Object> paramsMap) throws Exception;// 根据参数查询实体对象list

	T queryModelByParams(Map<String, Object> paramsMap) throws Exception;// 根据参数查询单个对象

	/***************************** 查询map的方法只作为查询展示信息时使用 **********************************/
	Map<String, Object> queryMapById(Long id) throws Exception;// 根据ID查询map对象

	Map<String, Object> queryMapByParams(Map<String, Object> paramsMap) throws Exception;// 根据参数查询一个map

	List<Map<String, Object>> queryMapList(Map<String, Object> paramsMap) throws Exception;// 根据参数查询List<map>

	/***************************** 查询map的方法只作为查询展示信息时使用 **********************************/
	BasePageDtoModel<T> queryPageModel(Map<String, Object> paramsMap) throws Exception; // 分页查询

	BasePageDtoModel<Map<String, Object>> queryPageMap(Map<String, Object> paramsMap) throws Exception;

	ResultDtoModel queryForCheckRepeat(Map<String, Object> paramsMap) throws Exception;// 校验是否重复
																						// 大于0时表示重复

	Integer queryModelCount(Map<String, Object> paramsMap) throws Exception;

	Integer queryMapCount(Map<String, Object> paramsMap) throws Exception;

	ResultDtoModel deleteByIds(String ids) throws Exception;

	ResultDtoModel disabledByIds(String ids) throws Exception;

	ResultDtoModel enabledByIds(String ids) throws Exception;

}
