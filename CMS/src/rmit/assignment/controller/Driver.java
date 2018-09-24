package rmit.assignment.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import rmit.assignment.model.Student;
import rmit.assignment.util.CourseUtil;
import rmit.assignment.util.StringUtil;
import rmit.assignment.view.Menu;

public class Driver {
	public static void main(String[] args) {
		Menu.displayMainMenu();
		Menu.displayOptionMsg();
		CourseUtil courseUtil = new CourseUtil();
		Scanner scanner = new Scanner(System.in);
		boolean status = true;
		while (status) {
			try {
				int menuNum = scanner.nextInt();
				switch(menuNum) {
					case 1:	
						System.out.println("===================== Add a student =====================");
						scanner.nextLine();
						Student student = new Student();
						boolean temp = true;
						String studentName = "";
						do {
							System.out.println("= Name:\t");
							studentName = scanner.nextLine();
							if(StringUtil.isEmpty(studentName)) {
								Menu.displayErrorMsg(1);
							}else {
								temp = false;
							}
						}while(temp);
						student.setStudentName(studentName);
						
						temp = true;
						int age = 1;
						do {
							System.out.println("= Age:\t");
							String tAge = scanner.nextLine();
							if(StringUtil.isEmpty(tAge)) {
								Menu.displayErrorMsg(2);
							}else {
								try {
									age = Integer.valueOf(tAge);
									if(age < 1) {
										Menu.displayErrorMsg(2);
									}else {
										temp = false;
									}
								} catch (NumberFormatException e) {
									Menu.displayErrorMsg(2);
								}
							}
						}while(temp);
						student.setAge(age);
						
						temp = true;
						do {
							System.out.println("= Address:\t");
							String address = scanner.nextLine();
							if(StringUtil.isNotEmpty(address)) {
								student.setAddress(address);
							}
							temp = false;
						}while(temp);
						
						temp = true;
						String courseId = "";
						do {
							System.out.println("= Course ID:\t");
							courseId = scanner.nextLine();
							if(StringUtil.isEmpty(courseId)) {
								Menu.displayErrorMsg(3);
							}else {
								if(!courseUtil.getCourseNameMap().containsKey(courseId))
									Menu.displayErrorMsg(3);
								else
									temp = false;
							}
						}while(temp);
						
						System.out.println("============== Students in "+courseUtil.getCourseNameMap().get(courseId)+" ==============");
						System.out.println(student.toString());
						break;
					case 2:	
						System.out.println("======= Withdraw a student =======");
						break;
					case 3:	
						System.out.println("======= Display a student list for a course =======");
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
				Menu.displayErrorMsg(0);
				scanner.nextLine();
			} catch (Exception e) {
//				e.printStackTrace();
				Menu.displayErrorMsg(0);
				scanner.nextLine();
			}
		}

	}

}
