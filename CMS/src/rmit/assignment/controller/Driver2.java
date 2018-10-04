package rmit.assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import rmit.assignment.model.Course;
import rmit.assignment.model.Student;
import rmit.assignment.util.CourseUtil;
import rmit.assignment.util.StringUtil;
import rmit.assignment.view.Menu;

public class Driver2 implements CourseUtil {
	private static Map<String, Integer> enrolMap = new HashMap<>();
	private static Map<String, String> courseNameMap = new HashMap<>();
	private static Map<String, Course> courseFigureMap = new HashMap<>();

	public static void main(String[] args) {
		// Initialize the course information
		initialCourseData();
		boolean status = true;
		Scanner scanner = new Scanner(System.in);
		while (status) {
			// display main menu
			Menu.displayMainMenu();
			String menuNumber = scanner.nextLine();
			try {
				int menu = Integer.valueOf(menuNumber);
				// validate menu number
				if (menu <= 0 || menu > 6) {
					throw new NumberFormatException();
				} else {
					switch (menu) {
					case 1:
						boolean continueToAdd = false;
						do{
							// display sub-function
							displaySubTitle(menu);
							// enter into specific function
							addStudent(scanner);
							continueToAdd = continueToAdd(scanner);
						}while(continueToAdd);
						break;
					case 2:
						withdrawStudent();
						break;
					case 3:
						boolean continueToChose = false;
						do{
							displaySubTitle(menu);
							displayStudentInCourse();
							continueToChose = choseCourse(scanner);
						}while(continueToChose);
						break;
					case 4:
						boolean continueToDisplayCourseFigures = false;
						do{
							displaySubTitle(menu);
							displayCourseFigures();
							continueToDisplayCourseFigures = continueToDisplayCourseFigures(scanner);
						}while(continueToDisplayCourseFigures);
						break;
					case 5:
						changeCourseFee();
						break;
					case 6:
						status = logout();
					}

				}
			} catch (NumberFormatException e) {
				// display main menu when input the wrong information by user
				Menu.displayFeedbackMsg(6);
			}
		}
	}

	/**
	 * add student
	 * @param scanner
	 */
	private static void addStudent(Scanner scanner) {
		Student student = new Student();
		// get and set student's name
		String studentName = (String) inputStudentInfo("Name",scanner, true, 1);
		student.setStudentName(studentName);
		//set age
		int age = (int) inputStudentInfo("Age",scanner, true, 2);
		student.setAge(age);
		//set address
		String address = (String) inputStudentInfo("Address",scanner, true, 3);
		if(StringUtil.isNotEmpty(address))
			student.setAddress(address);
		String courseId = (String) inputStudentInfo("Course id",scanner, true, 4);
		enrollStudent(student, courseId);
	}

	private static void withdrawStudent() {

	}

	private static void displayStudentInCourse() {
		Menu.displayCourseName();
	}
	
	/**
	 * display students from course
	 * @param scanner
	 * @return
	 */
	private static boolean choseCourse(Scanner scanner) {
		String tInput = scanner.nextLine();
		if (StringUtil.isEmpty(tInput)) {
			Menu.displayFeedbackMsg(6);
		} else {
			try {
				int menu = Integer.valueOf(tInput);
				// validate menu number
				if (menu <= 0 || menu > 6) {
					throw new NumberFormatException();
				} else {
					if (menu == 6) {
						return false;
					} else {
						String courseId = String.valueOf("00" + menu);
						Course course = courseFigureMap.get(courseId);
						displayCourseInfo(course, 0);
					}
				}
			} catch (NumberFormatException e) {
				Menu.displayFeedbackMsg(6);
			}
		}
		return true;
	}
	
	/**
	 * menu of continue to display course figures
	 * @param scanner
	 * @return
	 */
	private static boolean continueToDisplayCourseFigures(Scanner scanner) {
		Menu.displayCourseFeedback(3,"");
		String tInput = scanner.nextLine();
		try {
			if (StringUtil.isEmpty(tInput)) {
				throw new NumberFormatException();
			} else {
				int menu = Integer.valueOf(tInput);
				// validate menu number
				if (menu <= 0 || menu > 1) {
					throw new NumberFormatException();
				}else
					return true;
			}
		} catch (NumberFormatException e) {
			Menu.displayFeedbackMsg(6);
			continueToDisplayCourseFigures(scanner);
		}
		return false;
	}
	
	/**
	 * display student in specific course and course figures 
	 * @param course
	 * @param flag 1-display student 2-set and display course figures
	 */
	private static void displayCourseInfo(Course course, int flag) {
		String courseName = courseNameMap.get(course.getId());
		List<Student> students = course.getStudents();
		//
		if(flag == 0) {
			//combine display menu
			String menuFormat = String.format("\tStudents in %s", courseName);
			System.out.println(menuFormat);
			Menu.displayCourseFeedback(1,"");
			//display students from specific course
			if (students.size() > 0) {
				for (Student tStudent : students) {
					System.out.println(tStudent.toString());
				}
				//combine display menu
				Menu.displayCourseFeedback(1,"");
			} else {
				//no students in the course and combine display menu
				noStudents(courseName);
			}
		} else {
			String displayFormat = "%s: Students %d, Income %.2f, Cost %.2f, Profit %.2f";
			double income = 0.0;
			double cost = 0.0;
			double profit = 0.0;
			if (students.size() > 0) {
				for (Student tStudent : students) {
					//set course figures
					income = income + tStudent.getCourseFee();
					if (course.getId().equals("003")) {
						cost = 100 * students.size();
					} else {
						cost = course.getCost();
					}
					profit = income - cost;
				}
				System.out.println(String.format(displayFormat, courseName, students.size(), income,cost, profit));
				Menu.displayCourseFeedback(-1, "");
			} else {
				//no students in the course and combine display menu
				Menu.displayCourseFeedback(0, courseName);
			}
		}
	}
	
	/**
	 * no students in current course
	 * @param courseName
	 */
	private static void noStudents(String courseName) {
		Menu.displayCourseFeedback(0, courseName);
		Menu.displayCourseFeedback(1,"");
	}

	/**
	 * display course figures
	 */
	private static void displayCourseFigures() {
		System.out.println();
		for (Course course : courseFigureMap.values()) {
			displayCourseInfo(course, 1);
		}
		Menu.displayCourseFeedback(1,"");
	}

	private static void changeCourseFee() {

	}

	private static void displaySubTitle(int menuNumber) {
		Menu.displaySubTitle(menuNumber);
	}

	private static boolean logout() {
		Menu.displayLogoutMsg();
		return false;
	}

	/**
	 * get input information from console
	 * 1-name 2-age 3-address 4-course id
	 * @param subitem
	 * @param scanner
	 * @param temp
	 * @param flag
	 * @return
	 */
	private static Object inputStudentInfo(String subitem, Scanner scanner, boolean temp, int flag) {
		do {
			Menu.displaySubitem(subitem);
			String tInput = scanner.nextLine();
			if (StringUtil.isEmpty(tInput) && flag !=3) {
				Menu.displayFeedbackMsg(flag);
			} else {
				switch(flag) {
				//1 student name
				case 1:
					if (StringUtil.isEmpty(tInput)) {
						Menu.displayFeedbackMsg(flag);
					} else {
						temp = false;
						return String.valueOf(tInput);
					}
					break;
				//2 age	
				case 2:
					try {
						if (Integer.valueOf(tInput) < 1) {
							Menu.displayFeedbackMsg(flag);
						} else {
							temp = false;
							return Integer.valueOf(tInput);
						}
					} catch (NumberFormatException e) {
						Menu.displayFeedbackMsg(flag);
					}
					break;
				//3 address	
				case 3:
					return String.valueOf(tInput);
				//4 course id	
				case 4:
					if (StringUtil.isEmpty(tInput)) {
						Menu.displayFeedbackMsg(flag);
					} else {
						if (!courseNameMap.containsKey(tInput))
							Menu.displayFeedbackMsg(flag);
						else {
							temp = false;
							return String.valueOf(tInput);
						}
					}
					break;
				}
			}
		} while (temp);
		
		return null;
	}
	
	/**
	 * check student enrolled or not enrolled
	 * @param student
	 * @param courseId
	 * @return
	 */
	private static void enrollStudent(Student student, String courseId) {
		Course course = courseFigureMap.get(courseId);
		List<Student> students = course.getStudents();
		int j = 0;
		for (int i = 0; i < students.size(); i++) {
			Student tStudent = students.get(i);
			if (tStudent.getStudentName().equals(student.getStudentName())) {
				Menu.displayFeedbackMsg(5);
				j++;
				break;
			}
		}
		if (j == 0) {
			students.add(student);
			course.setStudents(students);
			//student's course fee
			//the student get discount since he has enrolled before
			if (enrolMap.containsKey(student.getStudentName())) {
				student.setCourseFee(course.getCharge() * DISCOUNT);
				enrolMap.put(student.getStudentName(), enrolMap.get(student.getStudentName() + 1));
			} else {
				//the student enrolled first time
				student.setCourseFee(course.getCharge());
				enrolMap.put(student.getStudentName(), 1);
			}
		}
	}
	
	/**
	 * continue to add student
	 * @param scanner
	 * @param temp
	 */
	private static boolean continueToAdd(Scanner scanner) {
		Menu.displayFeedbackMsg(7);
		String isContinue = scanner.nextLine();
		if (StringUtil.isEmpty(isContinue)) {
			Menu.displayFeedbackMsg(6);
			continueToAdd(scanner);
		} else {
			if (isContinue.equalsIgnoreCase("Y") || isContinue.equalsIgnoreCase("yes")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize the course information
	 */
	private static void initialCourseData() {
		courseNameMap.put("001", ITALIAN_COOKING);
		courseNameMap.put("002", SEAFOOD_COOKING);
		courseNameMap.put("003", SEWING);
		courseNameMap.put("004", CREATIVE_WRITING);
		courseNameMap.put("005", BUSINESS_WRITING);

		int tempTeacher = 1;
		for (Map.Entry<String, String> entry : courseNameMap.entrySet()) {
			Course course = new Course();
			course.setId(entry.getKey());
			course.setTeacherName("Teacher-" + tempTeacher++);
			course.setEnrolledQuantity(0);
			switch (entry.getValue()) {
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
			default:
				course.setEnrolledQuantity(0);

			}
			course.setStudents(new ArrayList<Student>());
			courseFigureMap.put(entry.getKey(), course);
		}
	}
}
