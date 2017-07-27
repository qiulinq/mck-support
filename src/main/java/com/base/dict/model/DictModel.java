package com.base.dict.model;

import com.xiangjia.base.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
/**
* åºç¡CRUDåºæ¯  业务模型
* <p>完成日期：2016-11-16</p>
* <p>邮件：1210046812@qq.com</p>
*
* @author qiulinq
* @version 1.1
*/
public class DictModel extends BaseModel {

/*************属性***********/
    /**
    * 
    */
    private String dictName;/**=""**/
    /**
    * 
    */
    private String dictCode;/**=""**/
    /**
    * 状态
    */
    private Integer status;/**=0**/


/*************set get 方法***********/
    /**
    * 获取
    */
    public String getDictName(){
    return this.dictName;
    }
    /**
    * 设置
    */
    public void setDictName(String dictName){
    this.dictName = dictName;
    }
    /**
    * 获取
    */
    public String getDictCode(){
    return this.dictCode;
    }
    /**
    * 设置
    */
    public void setDictCode(String dictCode){
    this.dictCode = dictCode;
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
}
