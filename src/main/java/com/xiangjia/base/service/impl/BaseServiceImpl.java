package com.xiangjia.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiangjia.base.dto.BasePageDtoModel;
import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.base.dto.SuccessDtoModel;
import com.xiangjia.base.mapper.BaseMapper;
import com.xiangjia.base.model.BaseModel;
import com.xiangjia.base.service.BaseService;
import com.xiangjia.common.cache.CacheUtils;
import com.xiangjia.common.helper.DateHelper;
import com.xiangjia.common.helper.StringHelper;
import com.xiangjia.common.util.SessionUtil;

/**
 * mybatis 基础 service 实现类
 * <p>
 * 完成日期：2016年6月12日
 * </p>
 * 
 * @author hj
 * @version 1.0
 */
public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

	protected Map<String, Object> paramsMap = new HashMap<String, Object>();

	protected BaseMapper<T> mapper;

	public void setMapper(BaseMapper<T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public ResultDtoModel save(T model) throws Exception {
		ResultDtoModel before = beforeSave(model);
		if (!before.getSuccess()) {
			return before;
		}
		mapper.save(model);
		model = saveOrUpdateExtend(model);
		afterSave(model);
		CacheUtils.addCache(model);
		return new ResultDtoModel(true, "新增成功");
	}

	@Override
	public ResultDtoModel delete(Long id) throws Exception {
		deleteExtend(id);
		mapper.delete(id);
		return new ResultDtoModel(true, "删除成功");
	}

	@Override
	public ResultDtoModel update(T model) throws Exception {

		ResultDtoModel before = beforeUpdate(model);
		if (!before.getSuccess()) {
			return before;
		}

		model.setUpdateTime(DateHelper.getTimestampNow());
		model.setUpdateUserId(SessionUtil.getUserId());
		mapper.update(model);
		model = saveOrUpdateExtend(model);
		CacheUtils.addCache(model);
		afterUpdate(model);
		return new ResultDtoModel(true, "修改成功");
	}

	@Override
	public T queryModelById(Long id) throws Exception {
		T model = mapper.queryModelById(id);
		if (model != null) {
			model = queryByIdExtend(model);
		} else {
			model = queryNullModelExtend();
		}
		return model;
	}

	protected T queryNullModelExtend() throws Exception {
		return null;
	}

	@Override
	public List<T> queryModelList(Map<String, Object> paramsMap) throws Exception {
		List<T> list = mapper.queryModelList(paramsMap);
		queryModelListExtend(list);
		return list;
	}

	@Override
	public T queryModelByParams(Map<String, Object> paramsMap) throws Exception {
		return mapper.queryModelByParams(paramsMap);
	}

	@Override
	public Map<String, Object> queryMapById(Long id) throws Exception {
		return mapper.queryMapById(id);
	}

	@Override
	public Map<String, Object> queryMapByParams(Map<String, Object> paramsMap) throws Exception {
		return mapper.queryMapByParams(paramsMap);
	}

	@Override
	public List<Map<String, Object>> queryMapList(Map<String, Object> paramsMap) throws Exception {
		return mapper.queryMapList(paramsMap);
	}

	@Override
	public BasePageDtoModel<T> queryPageModel(Map<String, Object> paramsMap) throws Exception {
		BasePageDtoModel<T> page = new BasePageDtoModel<T>();
		List<T> aaData = mapper.queryModelPageList(paramsMap);
		Integer count = mapper.queryModelCount(paramsMap);
		page.setAaData(aaData);
		page.setiTotalDisplayRecords(count);
		page.setiTotalRecords(count);
		return page;
	}

	@Override
	public ResultDtoModel queryForCheckRepeat(Map<String, Object> paramsMap) throws Exception {
		Integer count = mapper.queryForCheckRepeat(paramsMap);
		if (count != null && count > 0) {
			return new ResultDtoModel(false, "重复");
		} else {
			return new ResultDtoModel(true, "不重复");
		}
	}

	@Override
	public BasePageDtoModel<Map<String, Object>> queryPageMap(Map<String, Object> paramsMap) throws Exception {
		BasePageDtoModel<Map<String, Object>> page = new BasePageDtoModel<Map<String, Object>>();
		List<Map<String, Object>> aaData = mapper.queryMapPageList(paramsMap);
		Integer count = mapper.queryMapCount(paramsMap);
		page.setAaData(aaData);
		page.setiTotalDisplayRecords(count);
		page.setiTotalRecords(count);

		return page;
	}

	@Override
	public Integer queryModelCount(Map<String, Object> paramsMap) throws Exception {
		return mapper.queryModelCount(paramsMap);
	}

	@Override
	public Integer queryMapCount(Map<String, Object> paramsMap) throws Exception {
		return mapper.queryMapCount(paramsMap);
	}

	public void initCacheData() {
	}

	@Override
	public ResultDtoModel deleteByIds(String ids) throws Exception {
		if (StringHelper.isEmpty(ids)) {
			return new ResultDtoModel(false, "请选择需要删除的数据");
		}
		int count = 0;
		for (String id : ids.split(",")) {
			if (StringHelper.isNotEmpty(id)) {
				mapper.delete(Long.parseLong(id));
				count += 1;
				deleteExtend(Long.parseLong(id));
			}
		}
		return new ResultDtoModel(true, "成功删除[" + count + "]条数据");
	}

	@Override
	public ResultDtoModel disabledByIds(String ids) throws Exception {
		if (StringHelper.isEmpty(ids)) {
			return new ResultDtoModel(false, "请选择需要禁用的数据");
		}
		int count = 0;
		for (String id : ids.split(",")) {
			if (StringHelper.isNotEmpty(id)) {
				T model = mapper.queryModelById(Long.parseLong(id));
				model.setStatus(0);
				mapper.update(model);
				count += 1;
			}
		}
		return new ResultDtoModel(true, "成功禁用[" + count + "]条数据");
	}

	@Override
	public ResultDtoModel enabledByIds(String ids) throws Exception {
		if (StringHelper.isEmpty(ids)) {
			return new ResultDtoModel(false, "请选择需要启用的数据");
		}
		int count = 0;
		for (String id : ids.split(",")) {
			if (StringHelper.isNotEmpty(id)) {
				T model = mapper.queryModelById(Long.parseLong(id));
				model.setStatus(1);
				mapper.update(model);
				count += 1;
			}
		}
		return new ResultDtoModel(true, "成功启用[" + count + "]条数据");
	}

	/************************* 以下方法为可扩展方法 ****************************/

	/**
	 * 新增和保存扩展
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected T saveOrUpdateExtend(T model) throws Exception {
		return model;
	}

	/**
	 * 根据ID查询对象时扩展
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected T queryByIdExtend(T model) throws Exception {
		return model;
	}

	/**
	 * 删除扩展
	 * 
	 * @param id
	 * @throws Exception
	 */
	protected void deleteExtend(Long id) throws Exception {

	}

	/**
	 * 查询对象list扩展
	 * 
	 * @param list
	 * @return
	 */
	protected List<T> queryModelListExtend(List<T> list) {

		return list;
	}

	protected ResultDtoModel beforeUpdate(T model) throws Exception {

		return new ResultDtoModel(true, "");
	}

	protected ResultDtoModel beforeSave(T model) throws Exception {
		return new SuccessDtoModel();
	}

	protected ResultDtoModel afterSave(T model) throws Exception {
		return new SuccessDtoModel();
	}

	protected ResultDtoModel afterUpdate(T model) throws Exception {
		return new SuccessDtoModel();
	}
}
