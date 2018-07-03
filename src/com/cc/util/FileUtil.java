package com.cc.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @Description:get properties
 * @author: wh
 * @date:2018年5月3日下午2:33:40
 */

public class FileUtil {

	public String path = "";
	@Autowired
	private HttpServletRequest request;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// 获得新的文件名称
	public static String createFileName(String fileName) {
		// 取后缀名
		String p_n = fileName.substring(fileName.indexOf("."));

		// 构建新文件名 =pic_当前时间毫秒值.后缀
		String newName = "f_" + System.currentTimeMillis() + p_n;

		return newName;
	}

	// 上传
	public boolean uploadFile(MultipartFile file, String fileName) {
		boolean res = true;
		if (file != null) {
			try {
				// 上传路径对象
				File fileDir = new File(request.getSession().getServletContext().getRealPath("\\") + this.path);
				// 判断目标文件夹是否存在
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				// 上传目标文件对象
				File f = new File(fileDir, fileName);
				// 转存文件
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public boolean delimg(String fileName) {
		boolean result = true;
		try {
			File fileDir = new File(request.getSession().getServletContext().getRealPath("\\") + this.path + fileName);
			if (!fileDir.exists()) {
				fileDir.delete();
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

}
