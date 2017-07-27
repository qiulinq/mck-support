package com.xiangjia.common.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import com.xiangjia.common.helper.DateHelper;
import com.xiangjia.common.helper.FileHelper;

/**
 * 头像上传
 * 
 * @author Administrator
 *
 */
public class PhotoUp {
	private static final String FILE_UPLOAD_FILENAME = "com/xiangjia/config/properties/fileupload";

	public static Map<String, String> getPath() {
		String basePath = PropertiesUtil.readProperties("basePath", FILE_UPLOAD_FILENAME);
		basePath = basePath.replace("\\", "/");
		if (!basePath.endsWith("/")) {
			basePath = basePath.substring(0, basePath.length() - 1);
		}
		String[] dates = DateHelper.dateToStr(new Date(), "yyyy-MM-dd").split("-");
		String reqPath = "";
		for (String str : dates) {
			reqPath += "/" + str;
		}
		reqPath += "/";
		FileHelper.mkdirsNotExists(basePath + reqPath);
		Map<String, String> map = new HashMap<String, String>();
		map.put("savePath", basePath + reqPath);
		map.put("reqPath", reqPath);
		return map;
	}

	public static String uploadPhoto(String imgStr) {
		Map<String, String>  map = getPath();
		String fileName=UUID.randomUUID().toString().replace("-", "") + ".jpg";;
		String filePath =map.get("savePath")+fileName;
		Base64 decoder = new Base64();
		byte[] b;
		try {
			b = decoder.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map.get("reqPath")+fileName;

	}
}
