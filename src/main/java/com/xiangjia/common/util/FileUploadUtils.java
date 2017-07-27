package com.xiangjia.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xiangjia.base.dto.ResultDtoModel;
import com.xiangjia.common.helper.DateHelper;
import com.xiangjia.common.helper.FileHelper;

public class FileUploadUtils {
	private static Logger log = LoggerFactory.getLogger(FileUploadUtils.class);
	//文件上传配置文件名
	private static final String FILE_UPLOAD_FILENAME = "com/xiangjia/config/properties/fileupload";

//	private static String filePath = null;
	
	private static final String FILE_PATH_SUB = "";

	private static String picTypes = "BMP,JPG,TIFF,GIF,PCX,TGA,EXIF,FPX,SVG,PSD,CDR,PCD,DXF,UFO,EPS,AI,RAW,JPEG,PNG,PDF,";

	// 获取本地文件的保存路径
//	private static String getFilePath(HttpServletRequest request) {
//		if (filePath == null) {
//			filePath = request.getSession().getServletContext().getRealPath("/") + FILE_PATH_SUB;
//		}
//		File file = new File(filePath);
//        if(!file.exists()){
//            file.mkdirs();
//        }
//		return filePath;
//	}
	private static Map<String, String> getFilePath(){
		String basePath = PropertiesUtil.readProperties("basePath", FILE_UPLOAD_FILENAME);
		basePath = basePath.replace("\\", "/");
		if(!basePath.endsWith("/")){
			basePath = basePath.substring(0,basePath.length() - 1);
		}
		String[] dates = DateHelper.dateToStr(new Date(),"yyyy-MM-dd").split("-");
		String reqPath = "";
		for(String str : dates){
			reqPath += "/" + str ;
		}
		reqPath += "/";
		System.out.println(basePath + reqPath);
		FileHelper.mkdirsNotExists(basePath + reqPath);
		Map<String, String> map = new HashMap<String, String>();
		map.put("savePath", basePath + reqPath);
		map.put("reqPath", reqPath);
		return map;
	}

	public static List<Map<String, Object>> fileUpload(HttpServletRequest request,
			CommonsMultipartFile[] upFiles) throws IllegalStateException, IOException {

		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		Map<String, String> filePathMap = getFilePath();
		String filePath = filePathMap.get("savePath");
		for (CommonsMultipartFile upFile : upFiles) {
			if (!upFile.isEmpty()) {
				String oldName = upFile.getOriginalFilename();
				String fileName = UUID.randomUUID().toString().replace("-", "") + "." + getFileType(oldName);

				File f = new File(filePath + fileName);
				if (!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				upFile.transferTo(f);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileSize",
						(int) Math.ceil((upFile.getSize() / 1024.0)));// 文件大小
				map.put("oldName", oldName);// 原文件名称
				map.put("newName", fileName);
				boolean isImage = isImage(fileName);
				map.put("isImage", isImage);
				map.put("reqUrl", filePathMap.get("reqPath") + fileName);
				fileList.add(map);
			}
		}
		return fileList;
	
	}

	public static boolean isImage(String filename) {
		return picTypes.indexOf(getFileType(filename).toUpperCase() + ",") > -1;
	}

	private static String getFileType(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	public static ResultDtoModel validateFiles(CommonsMultipartFile[] files,
			String validTypes,Long fileSize) {
//		//判断文件最少个数
//		if(min != null && min > 0 && files.length < min){
//			
//		}
		for (CommonsMultipartFile file : files) {
			String oldName = file.getOriginalFilename();
			if (validTypes != null && validTypes.length() > 0) {
				if (validTypes.indexOf(getFileType(oldName)) < 0) {
					return new ResultDtoModel(false, oldName + "文件格式错误，当前支持一下格式："
							+ validTypes);
				}
			}
			if(fileSize != null && fileSize > 0 && file.getSize() > fileSize * 1024){
				return new ResultDtoModel(false, oldName + "文件大小超出限制,当前单个文件最大允许"
						+ fileSize + "kb");
			}
		}
		return new ResultDtoModel(true, "");
	}
}
