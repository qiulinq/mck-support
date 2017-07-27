package com.base.dict.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dict.mapper.DictMapper;
import com.base.dict.model.DictModel;
import com.base.dict.service.DictService;
import com.xiangjia.base.service.impl.BaseServiceImpl;
/**
* åºç¡CRUDåºæ¯  service实现类
* <p>完成日期：2016-11-16</p>
* <p>邮件：1210046812@qq.com</p>
*
* @author qiulinq
* @version 1.1
*/
@Service
public class DictServiceImpl extends BaseServiceImpl<DictModel> implements DictService {

    @Resource
    public void setMapper(DictMapper mapper) {
        super.setMapper(mapper);
    }
}
