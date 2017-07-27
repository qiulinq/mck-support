package com.base.dictitem.service;

import java.util.List;

import com.base.dictitem.model.DictItemModel;
import com.xiangjia.base.service.BaseService;
/**
* åºç¡CRUDåºæ¯  service接口
* <p>完成日期：2016-11-16</p>
* <p>邮件：1210046812@qq.com</p>
*
* @author qiulinq
* @version 1.1
*/
public interface DictItemService extends BaseService<DictItemModel> {
	
	List<DictItemModel> queryListByDictCode(String dictCode)throws Exception;
}
