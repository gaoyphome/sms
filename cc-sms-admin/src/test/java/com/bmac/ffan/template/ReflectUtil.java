package com.bmac.ffan.template;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bmac.ffan.common.persistence.model.CcBusCompany;

/**
 * 
 * @author wangshengjun
 *
 */
public class ReflectUtil {
	public static <T> List<String> reflectFieldNames(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		Arrays.asList(fields).forEach(e -> {
			if(!e.getName().startsWith("serialVersionUID")) {				
				list.add(e.getName());
			}
		});
		return list;
	}

	public static void main(String[] args) {
		System.out.println(reflectFieldNames(CcBusCompany.class));
	}
}
