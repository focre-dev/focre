package com.focre.base.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil extends BeanUtils {

	/**
	 * 从org.springframework.beans.BeanUtils类中直接复制过来
	 * 
	 * @param source
	 * @param target
	 * @throws BeansException
	 */
	public static void copyProperties(Object source, Object target) throws BeansException {
		copyProperties(source, target, null, (String[]) null);
	}
	
	public static <T> T copyProperties(Object source, Class<T> targetClass) throws BeansException, InstantiationException, IllegalAccessException {
		
		// 实例化对象
		T target = targetClass.newInstance();
		
		// 复制属性
		copyProperties(source, target, null, (String[]) null);
		
		return target;
	}

	/**
	 * @param sourceList
	 * @param destinationClass
	 * @throws BeansException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> copyProperties(Collection sourceList, Class<T> destinationClass) throws BeansException {
		List<T> destinationList = new ArrayList<>();
		if (CollectionUtils.isEmpty(sourceList)) {
			return destinationList;
		}

		T destinationObject = null;
		for (Object source : sourceList) {
			try {
				destinationObject = destinationClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				continue;
			}
			copyProperties(source, destinationObject);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 从org.springframework.beans.BeanUtils类中直接复制过来,修改部分代码
	 * 
	 * @param source
	 * @param target
	 * @param editable
	 * @param ignoreProperties
	 * @throws BeansException
	 */
	private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)
	        throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName()
				        + "] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0],
					        readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							// 判断被复制的属性是否为null, 如果不为null才复制
							if (value != null) {
								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
								}
								writeMethod.invoke(target, value);
							}
						} catch (Throwable ex) {
							throw new FatalBeanException(
							        "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) {
			return null;
		}

		Object obj = beanClass.newInstance();

		org.apache.commons.beanutils.BeanUtils.populate(obj, map);

		return (T) obj;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}

		return new org.apache.commons.beanutils.BeanMap(obj);
	}

	/**
	 * 实体对象转成Map
	 * 
	 * @param obj
	 *        实体对象
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
		Map<String, Object> map = new HashMap<>();
		if (obj == null) {
			return map;
		}
		@SuppressWarnings("rawtypes")
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	@SuppressWarnings("rawtypes")
	public <T> ArrayList convert(List<Object> a) {
		return (ArrayList) a;
	}
}