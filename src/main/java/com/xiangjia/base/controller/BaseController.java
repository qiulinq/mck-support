package com.xiangjia.base.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiangjia.base.dto.BasePageDtoModel;
import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.base.model.BaseModel;
import com.xiangjia.base.service.BaseService;
import com.xiangjia.common.helper.ExceptionHelper;
import com.xiangjia.common.helper.StringHelper;

/**
 * 基础场景-基础-前端Control控制类
 * <p>
 * 完成日期：2016-06-27
 * </p>
 * 
 * @author hj
 * @version 1.1
 */
public class BaseController<T extends BaseModel> {

	private static Logger log = LoggerFactory.getLogger(BaseController.class);

	protected Map<String, Object> paramsMap = new HashMap<String, Object>();

	protected String PAGE_PREFIX = "bussiness";

	protected String _BASE_ = "base";

	protected BaseService<T> service;

	protected String getPageBase() {
		return PAGE_PREFIX + "/" + _BASE_;
	}

	protected String getModelBase() {
		return _BASE_;
	}

	public void setService(BaseService<T> service) {
		this.service = service;
	}

	// list页面跳转
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listPost(HttpServletRequest request, ModelMap model) throws Exception {
		return getPageBase() + "/" + getModelBase() + "-list";
	}

	// ajax获取列表页面数据
	@RequestMapping(value = "pageList", method = RequestMethod.POST)
	@ResponseBody
	public BasePageDtoModel<T> pageList(HttpServletRequest request) {
		try {
			return service.queryPageModel(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return new BasePageDtoModel<T>();
		}
	}

	// ajax获取列表页面数据
	@RequestMapping(value = "allList", method = RequestMethod.POST)
	@ResponseBody
	public List<T> list(HttpServletRequest request) throws Exception {
		try {
			return service.queryModelList(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
		}
		return new ArrayList<T>();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		try {
			T bean = service.queryModelById(id);
			model.addAttribute("bean", bean);
			model = loadBaseData(model, bean);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
		}
		return getPageBase() + "/" + getModelBase() + "-edit";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(ModelMap model, HttpServletRequest request) {
		try {
			model = loadBaseData(model, null);
			model.put("bean", service.queryModelById(null));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
		}
		return getPageBase() + "/" + getModelBase() + "-edit";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.POST)
	public String show(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
		try {
			T bean = service.queryModelById(id);
			model.addAttribute("bean", bean);
			model = loadBaseData(model, bean);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
		}
		return getPageBase() + "/" + getModelBase() + "-show";
	}

	public ModelMap loadBaseData(ModelMap model, T bean) throws Exception {

		return model;
	}

	// 编辑跳转
	@RequestMapping(value = "/jump", method = RequestMethod.GET)
	public String jump(Long id, ModelMap model, HttpServletRequest request) throws Exception {
		model.addAttribute("bean", service.queryMapById(id));
		return getPageBase() + "/" + getModelBase() + "-edit";
	}

	// 保存
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel save(T model) {
		try {
			return service.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	// 根据ID查询
	@RequestMapping(value = "queryById/{id}", method = RequestMethod.POST)
	@ResponseBody
	public T queryById(@PathVariable("id") Long id) {
		try {
			return service.queryModelById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	// 根据根据参数查询
	@RequestMapping(value = "queryByParams", method = RequestMethod.POST)
	@ResponseBody
	public T queryByParams(HttpServletRequest request) {
		try {
			return service.queryModelByParams(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	// 修改数据
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel update(T model) {
		try {
			return service.update(model);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	// 根据ID删除
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel delete(@PathVariable("id") Long id) {
		try {
			return service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	// 根据ID查询map
	@RequestMapping(value = "/queryMapById/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMapById(@PathVariable("id") Long id) {
		try {
			return service.queryMapById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	// 根据参数查询一个map
	@RequestMapping(value = "/queryMapByParams", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMapByParams(HttpServletRequest request) {
		try {
			return service.queryMapByParams(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	// 根据参数查询一个list map
	@RequestMapping(value = "/queryListMap", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> queryListMap(HttpServletRequest request) {
		try {
			return service.queryMapList(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	@RequestMapping(value = "/queryPageMap", method = RequestMethod.POST)
	public BasePageDtoModel<Map<String, Object>> queryPageMap(HttpServletRequest request, ModelMap model) {
		try {
			return service.queryPageMap(getParamsMap(request));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return null;
		}
	}

	@RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel deleteByIds(String ids) {
		try {
			return service.deleteByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	@RequestMapping(value = "disabledByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel disabledByIds(String ids) {
		try {
			return service.disabledByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	@RequestMapping(value = "enabledByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel enabledByIds(String ids) {
		try {
			return service.enabledByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return ExceptionHelper.getExceptionResultDtoModel(e);
		}
	}

	// 校验是否重复
	@RequestMapping(value = "checkRepeated", method = RequestMethod.POST)
	@ResponseBody
	public ResultDtoModel checkRepeated(HttpServletRequest request) {
		try {
			Map<String, Object> map = getParamsMap(request);
			map.put("isChecked", 0);
			T model = service.queryModelByParams(map);
			// System.out.println(JSON.toJSON(model));
			ResultDtoModel res = new ResultDtoModel(model == null, "");
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("data", model);
			res.setDataMap(dataMap);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生如下异常：{}", e);
			return new ResultDtoModel(false, "系统错误");
		}

	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> getParamsMap(HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		while (enu.hasMoreElements()) {
			String paraName = enu.nextElement();
			String paraValue = request.getParameter(paraName);
			if (StringHelper.isNotEmpty(paraValue))
				paramsMap.put(paraName, paraValue);
		}
		paramsMap.put("start", paramsMap.get("iDisplayStart"));
		paramsMap.put("limit", paramsMap.get("iDisplayLength"));
		return paramsMap;
	}
}
