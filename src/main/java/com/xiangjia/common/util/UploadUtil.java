package com.xiangjia.common.util;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 图片上传工具
 * 
 * @author Administrator
 *
 */
public class UploadUtil {

	private static Logger logger = Logger.getLogger(UploadUtil.class);
	private static String UPLOAD_URL = "upRes";
	

	public static String upload(HttpServletRequest request, CommonsMultipartFile file)
			throws Exception {
		String result = "";
		String proPath = request.getSession().getServletContext().getRealPath("/");
		String[] datePath = initPath();
		String dir = proPath + UPLOAD_URL + File.separator + datePath[0];
		File checkDir = new File(dir);
		if (!checkDir.exists()) {
			checkDir.mkdirs();
		}
		// 取得当前上传文件的文件名称
		String myFileName = file.getOriginalFilename();
		// 重命名上传后的文件名
		String fileName = getFileName() + RandomStringUtils.randomNumeric(4)
				+ myFileName.toLowerCase();
		// 定义上传路径
		String path = dir + fileName;
		BufferedOutputStream stream = null;
		try {
			byte[] bytes = file.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			stream.write(bytes);
			result = "/" + UPLOAD_URL + datePath[1] + fileName;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				logger.error("文件流关闭失败", e);
			}
		}
		if (!isImageFile(new File(path))) {
			result = "-1";
		}
		return result;
	}

	public static String getDir(HttpServletRequest request, String typePath) {
		String proPath = request.getSession().getServletContext().getRealPath("/");
		String dir = proPath + UPLOAD_URL + File.separator + typePath + File.separator;
		File mkdir = new File(dir);
		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}
		return dir;
	}

	public static String[] initPath() {
		String[] result = new String[2];
		Calendar calendar = Calendar.getInstance();
		String dateTime = DateUtils.formatDate1(calendar.getTime(), "yyyyMM");
		String date = File.separator + dateTime + File.separator;
		String path = "/" + dateTime + "/";
		int day = calendar.get(Calendar.DATE);
		if (day < 10) {
			date += 0;
			path += 0;
		}
		date += day + File.separator;
		path += day + "/";
		result[0] = date;
		result[1] = path;
		return result;
	}

	public static synchronized String getFileName() {
		return DateUtils.formatDate1(new Date(), "yyyyMMddHHmm");
	}

	public synchronized static String getSFileName() {
		return DateUtils.formatDate1(new Date(), "yyyyMMddHHmm")
				+ RandomStringUtils.randomNumeric(4);
	}

	/**
	 * 是否是图片
	 * 
	 * @param imageFile
	 * @return
	 */
	public static boolean isImageFile(File imageFile) {
		if (!imageFile.exists()) {
			return false;
		}
		Image img;
		try {
			img = ImageIO.read(imageFile);
			return !(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0);
		} catch (Exception e) {
			return false;
		}
	}
}
