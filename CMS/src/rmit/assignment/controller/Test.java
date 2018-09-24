package rmit.assignment.controller;

import java.util.Iterator;
import java.util.Map;

import rmit.assignment.util.CourseUtil;

public class Test {

	public static void main(String[] args) {
		System.out.println("=====最常见并且大多数情况下最可取的遍历方式,在键值都需要时使用。=====");
		CourseUtil courseUtil = new CourseUtil();
		for (Map.Entry<String, String> entry : courseUtil.getCourseNameMap().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println("===================在for-each循环中遍历keys或values==============");
		for (String key : courseUtil.getCourseNameMap().keySet()) {
			System.out.println("Key = " + key);
		}
		for (String value : courseUtil.getCourseNameMap().values()) {
			System.out.println("Value = " + value);
		}
		System.out.println("===========================使用泛型=============================");
		Iterator<Map.Entry<String, String>> entries = courseUtil.getCourseNameMap().entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println("=====================通过键找值遍历（效率低）======================");
		for (String key : courseUtil.getCourseNameMap().keySet()) { 
			  String value = courseUtil.getCourseNameMap().get(key); 
			  System.out.println("Key = " + key + ", Value = " + value);
		}
	}

}
