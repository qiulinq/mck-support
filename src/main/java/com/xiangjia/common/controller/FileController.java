package com.xiangjia.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.common.util.FileUploadUtils;

@Controller
@RequestMapping("file")
public class FileController {
	
	
	@RequestMapping(value = "ajaxFileupload")
	@ResponseBody
	public ResultDtoModel ajaxFileupload(@RequestParam("ajaxFile") CommonsMultipartFile[] files,String validTypes,Long fileSize,HttpServletRequest request) {
		try {
			ResultDtoModel result = FileUploadUtils.validateFiles(files, validTypes, fileSize);
			if(!result.getSuccess()){
				return result;
			}
			List<Map<String, Object>> list = FileUploadUtils.fileUpload(request,files);
			result.setDataList(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultDtoModel(false,"上传文件出现异常:" + e.getMessage());
		}
	}
}
