package rmit.assignment.controller;

import java.util.Iterator;
import java.util.Map;

import rmit.assignment.util.CourseUtil;

public class Test {

	public static void main(String[] args) {
		System.out.println("=====������Ҵ������������ȡ�ı�����ʽ,�ڼ�ֵ����Ҫʱʹ�á�=====");
		CourseUtil courseUtil = new CourseUtil();
		for (Map.Entry<String, String> entry : courseUtil.getCourseNameMap().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println("===================��for-eachѭ���б���keys��values==============");
		for (String key : courseUtil.getCourseNameMap().keySet()) {
			System.out.println("Key = " + key);
		}
		for (String value : courseUtil.getCourseNameMap().values()) {
			System.out.println("Value = " + value);
		}
		System.out.println("===========================ʹ�÷���=============================");
		Iterator<Map.Entry<String, String>> entries = courseUtil.getCourseNameMap().entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println("=====================ͨ������ֵ������Ч�ʵͣ�======================");
		for (String key : courseUtil.getCourseNameMap().keySet()) { 
			  String value = courseUtil.getCourseNameMap().get(key); 
			  System.out.println("Key = " + key + ", Value = " + value);
		}
	}

}
