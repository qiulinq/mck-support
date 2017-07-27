package com.xiangjia.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.base.model.BaseModel;

/**
 * mybatis 基础映射接口
 * <p>
 * 完成日期：2016年6月12日
 * </p>
 * 
 * @author hj
 * @version 1.0
 */
public interface BaseMapper<T extends BaseModel> {
	void save(T model) throws Exception;// 保存

	void delete(@Param("id") Long id) throws Exception;// 删除

	void update(T model) throws Exception;// 修改

	T queryModelById(@Param("id") Long id) throws Exception;// 根据ID查询实体对象

	List<T> queryModelList(Map<String, Object> paramsMap) throws Exception;// 根据参数查询实体对象list

	List<T> queryModelPageList(Map<String, Object> paramsMap) throws Exception;

	T queryModelByParams(Map<String, Object> paramsMap) throws Exception;// 根据参数查询单个对象

	/***************************** 查询map的方法只作为查询展示信息时使用 **********************************/
	Map<String, Object> queryMapById(@Param("id") Long id) throws Exception;// 根据ID查询map对象

	Map<String, Object> queryMapByParams(Map<String, Object> paramsMap) throws Exception;// 根据参数查询一个map

	List<Map<String, Object>> queryMapList(Map<String, Object> paramsMap) throws Exception;// 根据参数查询List<map>

	List<Map<String, Object>> queryMapPageList(Map<String, Object> paramsMap) throws Exception;

	/***************************** 查询map的方法只作为查询展示信息时使用 **********************************/

	Integer queryForCheckRepeat(Map<String, Object> paramsMap) throws Exception;// 校验是否重复
																				// 大于0时表示重复

	Integer queryModelCount(Map<String, Object> paramsMap) throws Exception;

	Integer queryMapCount(Map<String, Object> paramsMap) throws Exception;

	void deleteByKey(@Param("props") Map<String, Object> paramsMap) throws Exception;
}
