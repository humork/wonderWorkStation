package com.cc.util;

import java.security.MessageDigest;

/**
 * 
 * @Description:MD5加密处理
 * @author: wh
 * @date:2018年5月3日下午5:35:48
 */
public class MD5Util {

	/***
	 * MD5加密 生成32位md5码
	 * 
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		StringBuffer hexValue = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");

			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) {
		String str = new String("love");
		System.out.println("原始：" + str);
		System.out.println("MD5后：" + md5Encode(str));
	}
}
