package com.cc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
	/**
	 * 响应回json对象
	 * 
	 * @param object
	 */
	public static String writeJson(Object object) {
		String json = JSON.toJSONString(object);
		System.out.println(json + "json");
		return json;
	}

	/***
	 * 响应json,并过滤不需要的属性
	 * 
	 * @param object
	 *            待转换对象
	 * @param propertyNames
	 *            字符串数组结构：不想转换的属性，例：new String[]{"pro_1","pro_2"}
	 */
	public static String writeJson(Object object, String... propertyNames) {
		String jsonRes = null;
		try {
			// 过滤属性
			PropertyFilter pf = filterProperty(propertyNames);
			// 套用过滤规则.加上这个可以把$ref过滤掉！
			jsonRes = JSON.toJSONString(object, pf, SerializerFeature.DisableCircularReferenceDetect);
			System.out.println(jsonRes + "json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonRes;
	}

	/***
	 * 属性过滤器
	 * 
	 * @param propertyNames
	 *            不需要转换的属性
	 * @return
	 */
	public static PropertyFilter filterProperty(final String... propertyNames) {
		// 过滤不显示的属性及关联属性
		PropertyFilter propertyFilter = new PropertyFilter() {

			public boolean apply(Object arg0, String propertyName, Object arg2) {
				if (propertyNames != null && propertyNames.length > 0) {
					for (String pname : propertyNames) {
						if (propertyName.equals(pname)) {
							return false;
						}
					}
				}
				return true;
			}
		};
		return propertyFilter;
	}
}
