package com.cc.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @Description:get properties
 * @author: wh
 * @date:2018��5��3������2:33:40
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

	// ����µ��ļ�����
	public static String createFileName(String fileName) {
		// ȡ��׺��
		String p_n = fileName.substring(fileName.indexOf("."));

		// �������ļ��� =pic_��ǰʱ�����ֵ.��׺
		String newName = "f_" + System.currentTimeMillis() + p_n;

		return newName;
	}

	// �ϴ�
	public boolean uploadFile(MultipartFile file, String fileName) {
		boolean res = true;
		if (file != null) {
			try {
				// �ϴ�·������
				File fileDir = new File(request.getSession().getServletContext().getRealPath("\\") + this.path);
				// �ж�Ŀ���ļ����Ƿ����
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				// �ϴ�Ŀ���ļ�����
				File f = new File(fileDir, fileName);
				// ת���ļ�
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
