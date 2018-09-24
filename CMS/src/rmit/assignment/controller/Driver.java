package rmit.assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import rmit.assignment.model.Course;
import rmit.assignment.model.Student;
import rmit.assignment.util.CourseUtil;
import rmit.assignment.util.StringUtil;
import rmit.assignment.view.Menu;

public class Driver implements CourseUtil {
	private static Map<String, Integer> enrolMap = new HashMap<>();
	private static Map<String, String> courseNameMap = new HashMap<>();
	private static Map<String, Course> courseFigureMap = new HashMap<>();

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

	public static void main(String[] args) {
		initialCourseData();
		Menu.displayMainMenu();
		Scanner scanner = new Scanner(System.in);
		boolean status = true;
		while (status) {
			try {
				int menuNum = scanner.nextInt();
				switch (menuNum) {
				case 1:
					addStudent();
					break;
				case 2:
					System.out.println("======= Withdraw a student =======");
					break;
				case 3:
					displayStudentInCourse(scanner);
					break;
				case 4:
					System.out.println("======= Display the course figures =======");
					break;
				case 5:
					Menu.displayLogoutMsg();
					status = false;
					break;
				default:
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
//				System.out.println("InputMismatchException");
//				e.printStackTrace();
				Menu.displayErrorMsg(0);
				scanner.nextLine();
			} catch (Exception e) {
//				System.out.println("Exception");
//				 e.printStackTrace();
				Menu.displayErrorMsg(0);
				scanner.nextLine();
			}
		}

	}

	private static void displayStudentInCourse(Scanner scanner) {
		int menuNum;
		boolean temp = true;
		do {
			System.out.println("========== Display a student list for a course ==========");
			Menu.displayCourseName();
			menuNum = scanner.nextInt();
			temp = displayStudentsInCourseDetail(menuNum, temp);
		}while(temp);
	}

	private static boolean displayStudentsInCourseDetail(int menuNum, boolean temp) {
		if(menuNum <=0 || menuNum >6) {
			Menu.displayErrorMsg(0);
		}else {
			if(menuNum == 6) {
				Menu.displayMainMenu();
				temp = false;
			}else {
				String courseId = String.valueOf("00"+menuNum);
				String menuFormat = String.format("============== Students in %s ==============", courseNameMap.get(courseId));
				System.out.println(menuFormat);
				Course courses = courseFigureMap.get(courseId);
				List<Student> students = courses.getStudents();
				if(students.size()>0) {
					for(Student tStudent : students) {
						System.out.println(tStudent.toString());
					}
				}else {
					System.out.println("No students in this course.");
				}
				System.out.println("=========================================================");
			}
			
		}
		return temp;
	}

	private static void addStudent() {
		System.out.println("===================== Add a student =====================");
		Scanner scanner = new Scanner(System.in);
		Student student = new Student();
		boolean temp = true;
		String studentName = inputStudentName(scanner, true);
		student.setStudentName(studentName);

//		temp = true;
		int age = inputStudentAge(scanner, true);
		student.setAge(age);

//		temp = true;
		inputStudentAddress(scanner, student);

//		temp = true;
		String courseId = registerCourse(scanner, student, true, studentName);
		
//		temp = true;
		continueToAdd(scanner, true);
		
		

		// System.out.println("============== Students in
		// "+courseUtil.getCourseNameMap().get(courseId)+" ==============");
		// System.out.println(student.toString());
	}
	
	private static void continueToAdd(Scanner scanner, boolean temp) {
		do {
			System.out.println("Continue to add£¿Yes/No");
			String isContinue = scanner.nextLine();
			if (StringUtil.isEmpty(isContinue)) {
				Menu.displayErrorMsg(0);
			} else {
				if (isContinue.equalsIgnoreCase("Yes")) {
					addStudent();
				} else {
					Menu.displayMainMenu();
				}
				temp = false;
			}
		} while (temp);
	}

	private static String registerCourse(Scanner scanner, Student student, boolean temp, String studentName) {
		String courseId;
		do {
			System.out.println("= Course ID:\t");
			courseId = scanner.nextLine();
			if (StringUtil.isEmpty(courseId)) {
				Menu.displayErrorMsg(3);
			} else {
				if (!courseNameMap.containsKey(courseId))
					Menu.displayErrorMsg(3);
				else {
					Course course = courseFigureMap.get(courseId);
					if (enrolMap.containsKey(student.getStudentName())) {
						student.setCourseFee(course.getCharge() * DISCOUNT);
						enrolMap.put(student.getStudentName(), enrolMap.get(student.getStudentName() + 1));
					} else {
						student.setCourseFee(course.getCharge());
						enrolMap.put(student.getStudentName(), 1);
					}

					List<Student> students = course.getStudents();
					int j=0;
					for(int i=0;i<students.size();i++) {
						Student tStudent = students.get(i);
						if(tStudent.getStudentName().equals(studentName)) {
							Menu.displayErrorMsg(4);
							temp = false;
							j++;
						}
					}
					if(j == 0) {
						students.add(student);
						course.setStudents(students);
						temp = false;
					}
				}
			}
		} while (temp);
		return courseId;
	}

	private static void inputStudentAddress(Scanner scanner, Student student) {
		boolean temp;
		do {
			System.out.println("= Address:\t");
			String address = scanner.nextLine();
			if (StringUtil.isNotEmpty(address)) {
				student.setAddress(address);
			}
			temp = false;
		} while (temp);
	}

	private static int inputStudentAge(Scanner scanner, boolean temp) {
		int age = 1;
		do {
			System.out.println("= Age:\t");
			String tAge = scanner.nextLine();
			if (StringUtil.isEmpty(tAge)) {
				Menu.displayErrorMsg(2);
			} else {
				try {
					age = Integer.valueOf(tAge);
					if (age < 1) {
						Menu.displayErrorMsg(2);
					} else {
						temp = false;
					}
				} catch (NumberFormatException e) {
					Menu.displayErrorMsg(2);
				}
			}
		} while (temp);
		return age;
	}

	private static String inputStudentName(Scanner scanner, boolean temp) {
		String studentName;
		do {
			System.out.println("= Name:\t");
			studentName = scanner.nextLine();
			if (StringUtil.isEmpty(studentName)) {
				Menu.displayErrorMsg(1);
			} else {
				temp = false;
			}
		} while (temp);
		
		return studentName;
	}

}
