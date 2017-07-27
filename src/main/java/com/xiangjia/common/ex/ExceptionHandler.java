package com.xiangjia.common.ex;

 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.xiangjia.base.dto.JSonDtoModel;
import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.common.helper.AjaxHeler;
import com.xiangjia.common.helper.StringHelper;

public class ExceptionHandler implements HandlerExceptionResolver {
	private static Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
    	ex.printStackTrace();

        String requestType = request.getHeader("X-Requested-With");

        if(StringHelper.isEmpty(requestType)){
            ModelAndView mv = new ModelAndView();
            mv.addObject("ex",ex.getMessage());
            mv.setViewName("error");
            return mv;
        }else {
        	log.error("ajax异常：{}",ex );
        	ModelAndView mv = new ModelAndView(); 
            FastJsonJsonView view = new FastJsonJsonView();  
            Map<String, Object> attributes = new HashMap<String, Object>();  
            attributes.put("success", false);  
            if(ex instanceof UnauthorizedException){
             attributes.put("message", "游客权限只能浏览");
            }else{
             attributes.put("message", "系统错误，请联系管理员");}
            view.setAttributesMap(attributes);  
            mv.setView(view);  
            return mv;
        }
    }

}