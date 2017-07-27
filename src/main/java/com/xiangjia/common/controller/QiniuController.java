package com.xiangjia.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.util.Auth;
import com.xiangjia.common.helper.QiNiuHelper;
import com.xiangjia.common.util.PropertiesUtil;

@RequestMapping("qiniu")
@Controller
public class QiniuController {
	

	private final Logger log = LoggerFactory.getLogger(QiniuController.class);

	@RequestMapping("getuploadToken")
	@ResponseBody
	public Map<String, String> getUpToken() {
		
		Auth auth = Auth.create(QiNiuHelper.getAk(), QiNiuHelper.getSk());
		String token = auth.uploadToken(QiNiuHelper.getBucket());
		log.debug(token);
		Map<String, String> map = new HashMap<>();
		map.put("uptoken", token);
		return map;
	}
}
