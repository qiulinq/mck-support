package com.base.dictitem.model;

import com.base.dict.model.DictModel;
import com.xiangjia.base.model.BaseModel;
import com.xiangjia.common.cache.CacheUtils;
/**
* åºç¡CRUDåºæ¯  业务模型
* <p>完成日期：2016-11-16</p>
* <p>邮件：1210046812@qq.com</p>
*
* @author qiulinq
* @version 1.1
*/
public class DictItemModel extends BaseModel {

/*************属性***********/
    /**
    * 
    */
    private java.lang.Long dictId;/**=0l**/
    /**
    * 
    */
    private String itemName;/**=""**/
    /**
    * 
    */
    private Integer itemCode;/**=0**/
    /**
    * 
    */
    private Integer seq;/**=0**/
    /**
    * 状态
    */
    private Integer status;/**=0**/


/*************set get 方法***********/
    /**
    * 获取
    */
    public java.lang.Long getDictId(){
    return this.dictId;
    }
    /**
    * 设置
    */
    public void setDictId(java.lang.Long dictId){
    this.dictId = dictId;
    }
    /**
    * 获取
    */
    public String getItemName(){
    return this.itemName;
    }
    /**
    * 设置
    */
    public void setItemName(String itemName){
    this.itemName = itemName;
    }
    /**
    * 获取
    */
    public Integer getItemCode(){
    return this.itemCode;
    }
    /**
    * 设置
    */
    public void setItemCode(Integer itemCode){
    this.itemCode = itemCode;
    }
    /**
    * 获取
    */
    public Integer getSeq(){
    return this.seq;
    }
    /**
    * 设置
    */
    public void setSeq(Integer seq){
    this.seq = seq;
    }
    /**
    * 获取状态
    */
    public Integer getStatus(){
    return this.status;
    }
    /**
    * 设置状态
    */
    public void setStatus(Integer status){
    this.status = status;
    }
    
    public String getDictName(){
    	DictModel model = CacheUtils.getCache(dictId, DictModel.class);
    	if(model != null){
    		return model.getDictName();
    	}
		return "";
	}
}
