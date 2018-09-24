package rmit.assignment.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rmit.assignment.model.Course;
import rmit.assignment.model.Student;

public class CourseUtil {
	private static final String ITALIAN_COOKING = "Italian Cooking";
	private static final String SEAFOOD_COOKING = "Seafood Cooking";
	private static final String SEWING = "Sewing";
	private static final String CREATIVE_WRITING = "Creative Writing";
	private static final String BUSINESS_WRITING = "Business Writing";
	
	private static final double ITALIAN_COOKING_CHARGE = 500.00;
	private static final double SEAFOOD_COOKING_CHARGE = 700.00;
	private static final double SEWING_CHARGE = 300.00;
	private static final double CREATIVE_WRITING_CHARGE = 200.00;
	private static final double BUSINESS_WRITING_CHARGE = 200.00;
	
	private static final double ITALIAN_COOKING_COST = 1000.00;
	private static final double SEAFOOD_COOKING_COST = 1000.00;
	private static final double SEWING_COST = 100.00;	//per person
	private static final double CREATIVE_WRITING_COST = 800.00;	
	private static final double BUSINESS_WRITING_COST = 600.00;
	
	private static final int MAXIMUM_QUANTITY_1 = 15;
	private static final int MAXIMUM_QUANTITY_2 = 10;
	
	private Map<String, String> courseNameMap;
	private Map<String, Course> courseFigureMap;

	public CourseUtil() {
		courseNameMap = new HashMap<>();
		courseNameMap.put("001",ITALIAN_COOKING);
		courseNameMap.put("002",SEAFOOD_COOKING);
		courseNameMap.put("003",SEWING);
		courseNameMap.put("004",CREATIVE_WRITING);
		courseNameMap.put("005",BUSINESS_WRITING);

		courseFigureMap = new HashMap<>();
		int tempTeacher = 1;
		for (Map.Entry<String, String> entry : courseNameMap.entrySet()) {
			Course course = new Course();
			course.setId(entry.getKey());
			course.setTeacherName("Teacher-"+tempTeacher++);
			course.setEnrolledQuantity(0);
			switch(entry.getValue()) {
				case ITALIAN_COOKING: 
					course.setMaximumQuantity(MAXIMUM_QUANTITY_2); 
					course.setCharge(ITALIAN_COOKING_CHARGE);
					course.setCost(ITALIAN_COOKING_COST);
					break;
				case SEAFOOD_COOKING: 
					course.setMaximumQuantity(MAXIMUM_QUANTITY_2);
					course.setCharge(SEAFOOD_COOKING_CHARGE);
					course.setCost(SEAFOOD_COOKING_COST);
					break;
				case SEWING: 
					course.setMaximumQuantity(MAXIMUM_QUANTITY_2); 
					course.setCharge(SEWING_CHARGE);
					course.setCost(SEWING_COST);
					break;
				case CREATIVE_WRITING:
					course.setMaximumQuantity(MAXIMUM_QUANTITY_2); 
					course.setCharge(CREATIVE_WRITING_CHARGE);
					course.setCost(CREATIVE_WRITING_COST);
					break;
				case BUSINESS_WRITING: 
					course.setMaximumQuantity(MAXIMUM_QUANTITY_1); 
					course.setCharge(BUSINESS_WRITING_CHARGE);
					course.setCost(BUSINESS_WRITING_COST);
					break;
				default: course.setEnrolledQuantity(0);
						
			}
			course.setStudents(new ArrayList<Student>());
			courseFigureMap.put(entry.getKey(), course);
		}
	}

	public Map<String, String> getCourseNameMap() {
		return courseNameMap;
	}

	public void setCourseNameMap(Map<String, String> courseNameMap) {
		this.courseNameMap = courseNameMap;
	}

	public Map<String, Course> getCourseFigureMap() {
		return courseFigureMap;
	}

	public void setCourseFigureMap(Map<String, Course> courseFigureMap) {
		this.courseFigureMap = courseFigureMap;
	}
	
	public static void main(String[] args) {
		CourseUtil cu = new CourseUtil();
		for (Map.Entry<String, Course> entry : cu.getCourseFigureMap().entrySet()) {
			Course c = entry.getValue();
			System.out.println(entry.getKey() + ", " + c.toString());
		}
	}

}
