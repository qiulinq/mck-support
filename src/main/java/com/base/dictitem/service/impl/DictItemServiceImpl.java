package com.base.dictitem.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dict.mapper.DictMapper;
import com.base.dict.model.DictModel;
import com.base.dictitem.mapper.DictItemMapper;
import com.base.dictitem.model.DictItemModel;
import com.base.dictitem.service.DictItemService;
import com.xiangjia.base.service.impl.BaseServiceImpl;

/**
 * åºç¡CRUDåºæ¯ service实现类
 * <p>
 * 完成日期：2016-11-16
 * </p>
 * <p>
 * 邮件：1210046812@qq.com
 * </p>
 * 
 * @author qiulinq
 * @version 1.1
 */
@Service
public class DictItemServiceImpl extends BaseServiceImpl<DictItemModel> implements DictItemService {
	@Resource
	private DictMapper dictMapper;

	private DictItemMapper dictItemMapper;

	@Resource
	public void setMapper(DictItemMapper mapper) {
		super.setMapper(mapper);
		dictItemMapper = mapper;
	}

	@Override
	public List<DictItemModel> queryListByDictCode(String dictCode) throws Exception {
		paramsMap.clear();
		paramsMap.put("dictCode", dictCode);
		DictModel dictModel = dictMapper.queryModelByParams(paramsMap);
		if (dictModel != null) {
			paramsMap.clear();
			paramsMap.put("dictId", dictModel.getId());
			paramsMap.put("orderBy", " order by item_code");
			return dictItemMapper.queryModelList(paramsMap);
		}
		return null;
	}
}
