package com.xiangjia.common.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class UEQiNiuHelper {
	public String ak;
	public String sk;
	public String bucket;
	public UploadManager uploadManager;

	private static boolean inited = false;

	public static synchronized void init() {
		if (inited)
			return;
		inited = true;
		UEQiNiuHelper ueQiNiuHelper = new UEQiNiuHelper();
		ueQiNiuHelper.ak = QiNiuHelper.getAk();
		ueQiNiuHelper.sk = QiNiuHelper.getSk();
		ueQiNiuHelper.bucket = QiNiuHelper.getBucket();
		ueQiNiuHelper.uploadManager = new UploadManager();
		StorageManager.ueQiNiuHelper = ueQiNiuHelper;
	}

	public State saveTmpFile(File tmpFile, String path) {
		System.out.println("-----------》》》qiniu save temp file");
		File targetFile = new File(path);
		String fileName = targetFile.getName();

		try {
			uploadFileToQiNiu(fileName, tmpFile.getCanonicalPath());

		} catch (IOException e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.IO_ERROR);
		} finally {
			FileUtils.deleteQuietly(tmpFile);// 删除临时文件
		}

		State state = new BaseState(true);
		state.putInfo("size", tmpFile.length());
		state.putInfo("title", fileName);

		return state;
	}

	public String getUptoken() {
		String uptoken = Auth.create(ak, sk).uploadToken(bucket);
		return uptoken;
	}

	public void uploadFileToQiNiu(String fileKey, String file) {
		try {
			Response response = uploadManager.put(file, fileKey, getUptoken());
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		UEQiNiuHelper ueQiNiuUtil = new UEQiNiuHelper();
//		ueQiNiuUtil.init();
//		ueQiNiuUtil.uploadFileToQiNiu("aabbcc", "/home/conquer/Desktop/baidu.png");
//	}
}
