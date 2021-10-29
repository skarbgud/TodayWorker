package com.workerholic.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {

	// VO => Map
	// params (Map으로 바꾸려는 VO 객체)
	public static Map<String, Object> convertToMap(Object obj) throws Exception {

		// 빈 객체일 경우 빈 Map 리턴
		if (obj == null) {
			return Collections.emptyMap();
		}

		Map<String, Object> convertMap = new HashMap<String, Object>();

		Field[] fields = obj.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			convertMap.put(field.getName(), field.get(obj));
		}

		return convertMap;
	}

	// Map => VO 
	// params (바꾸려는 Map객체, 해당 Class 타입의 기본 생성자)
	public static <T> T convertToValueObject(Map<String, Object> map, Class<T> type) throws Exception {

		if (type == null) {
			throw new NullPointerException("Class NullPointerException");
		}

		T instance = type.getConstructor().newInstance();

		if (map == null || map.isEmpty()) {
			return instance;
		}

		// 키값들을 불러온다
		for (Map.Entry<String, Object> entrySet : map.entrySet()) {
			Field[] fields = type.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);

				String fieldName = field.getName();

				boolean isSameType = entrySet.getValue().getClass().equals(field.getType());
				boolean isSameName = entrySet.getKey().equals(fieldName);

				if (isSameName && isSameType) {
					field.set(instance, map.get(fieldName));
				}
			}
		}

		return instance;
	}
}
