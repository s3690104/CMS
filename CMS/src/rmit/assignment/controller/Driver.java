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
		Scanner scanner = new Scanner(System.in);
		boolean status = true;
		boolean temp = true;
		while (status) {
			Menu.displayMainMenu();
			try {
				int menuNum = scanner.nextInt();
				scanner.nextLine();
				switch (menuNum) {
				case 1:
					addStudent();
					break;
				case 2:
					temp = true;
					do {
						System.out.println("======= Withdraw a student =======");
						Menu.displayCourseName();
						try {
							String inputNumber = scanner.nextLine();
							menuNum = Integer.valueOf(inputNumber);
							if (menuNum <= 0 || menuNum > 6) {
								Menu.displayFeedbackMsg(0);
							} else {
								
							}
						}catch(NumberFormatException e) {
							Menu.displayFeedbackMsg(0);
						}
					}while(temp);
					break;
				case 3:
					temp = true;
					do {
						System.out.println("========== Display a student list for a course ==========");
						Menu.displayCourseName();
						try {
							String inputNumber = scanner.nextLine();
							menuNum = Integer.valueOf(inputNumber);
							if (menuNum <= 0 || menuNum > 6) {
								Menu.displayFeedbackMsg(0);
							} else {
								if (menuNum <= 0 || menuNum > 6) {
									Menu.displayFeedbackMsg(0);
								} else {
									if (menuNum == 6) {
//										Menu.displayMainMenu();
										temp = false;
									} else {
										String courseId = String.valueOf("00" + menuNum);
										String menuFormat = String.format(
												"============== Students in %s ==============",
												courseNameMap.get(courseId));
										System.out.println(menuFormat);
										Course courses = courseFigureMap.get(courseId);
										List<Student> students = courses.getStudents();
										if (students.size() > 0) {
											for (Student tStudent : students) {
												System.out.println(tStudent.toString());
											}
										} else {
											System.out.println("No students in this course.");
										}
										System.out.println("=========================================================");
										System.out.println();
									}

								}
							}
						} catch (NumberFormatException e) {
							Menu.displayFeedbackMsg(0);
						}
					} while (temp);
					break;
				case 4:
					System.out.println("=============== Display the course figures ==============");
					temp = true;
					do {
						String displayFormat = "%s: Students %d, Income %.2f, Cost %.2f, Profit %.2f";
						// String displayFormat = "%s:, Students %d, Income %.2f, Cost %.2f, Profit
						// %.2f";
						for (Course course : courseFigureMap.values()) {
							String courseName = courseNameMap.get(course.getId());
							List<Student> students = course.getStudents();
							double income = 0.0;
							double cost = 0.0;
							double profit = 0.0;
							if (students.size() > 0) {
								for (Student tStudent : students) {
									income = income + tStudent.getCourseFee();
									if (course.getId().equals("003")) {
										cost = 100 * students.size();
									} else {
										cost = course.getCost();
									}
									profit = income - cost;
								}
								System.out.println(String.format(displayFormat, courseName, students.size(), income,
										cost, profit));
							} else {
								System.out.println(String.format("No students in %s course.", courseName));
							}
							temp = false;
						}
					} while (temp);

					temp = true;
					do {
						System.out.println("=========================================================");
						System.out.println("=\t1. Back to main menu\t\t\t\t=");
						System.out.println("=========================================================");
						Menu.displayOptionMsg();
						try {
							String inputNumber = scanner.nextLine();
							menuNum = Integer.valueOf(inputNumber);
							if (menuNum <= 0 || menuNum > 1) {
								Menu.displayFeedbackMsg(0);
							} else {
//								Menu.displayMainMenu();
								temp = false;
							}
						} catch (NumberFormatException e) {
							Menu.displayFeedbackMsg(0);
						}
					} while (temp);

					break;
				case 5:	
						System.out.println("======= Change course fee =======");
						break;
				case 6:
					Menu.displayLogoutMsg();
					status = false;
					break;
				default:
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				 System.out.println("InputMismatchException");
				// e.printStackTrace();
				Menu.displayFeedbackMsg(0);
//				Menu.displayOptionMsg();
				scanner.nextLine();
			} catch (Exception e) {
				 System.out.println("Exception");
				// e.printStackTrace();
				Menu.displayFeedbackMsg(0);
//				Menu.displayOptionMsg();
				scanner.nextLine();
			}
		}

	}

	private static void addStudent() {
		System.out.println("===================== Add a student =====================");
		Scanner scanner = new Scanner(System.in);
		Student student = new Student();
		String studentName = inputStudentName(scanner, true);
		student.setStudentName(studentName);
		int age = inputStudentAge(scanner, true);
		student.setAge(age);
		inputStudentAddress(scanner, student);
		registerCourse(scanner, student, true, studentName);
		continueToAdd(scanner, true);
	}

	private static void continueToAdd(Scanner scanner, boolean temp) {
		do {
			System.out.println("Continue to add£¿Y/N");
			String isContinue = scanner.nextLine();
			if (StringUtil.isEmpty(isContinue)) {
				Menu.displayFeedbackMsg(0);
			} else {
				if (isContinue.equalsIgnoreCase("Y") || isContinue.equalsIgnoreCase("yes")) {
					addStudent();
				} 
//				else {
//					Menu.displayMainMenu();
//				}
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
				Menu.displayFeedbackMsg(3);
			} else {
				if (!courseNameMap.containsKey(courseId))
					Menu.displayFeedbackMsg(3);
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
					int j = 0;
					for (int i = 0; i < students.size(); i++) {
						Student tStudent = students.get(i);
						if (tStudent.getStudentName().equals(studentName)) {
							Menu.displayFeedbackMsg(4);
							temp = false;
							j++;
						}
					}
					if (j == 0) {
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
				Menu.displayFeedbackMsg(2);
			} else {
				try {
					age = Integer.valueOf(tAge);
					if (age < 1) {
						Menu.displayFeedbackMsg(2);
					} else {
						temp = false;
					}
				} catch (NumberFormatException e) {
					Menu.displayFeedbackMsg(2);
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
				Menu.displayFeedbackMsg(1);
			} else {
				temp = false;
			}
		} while (temp);

		return studentName;
	}

}
