package rmit.assignment.view;

public class Menu {
	public static void displayMainMenu() {
		System.out.println("\t\tCourse Management System");
		System.out.println("=========================================================");
		System.out.println("=\t1. Add a student\t\t\t\t=");
		System.out.println("=\t2. Withdraw a student\t\t\t\t=");
		System.out.println("=\t3. Display a student list for a course\t\t=");
		System.out.println("=\t4. Display the course figures\t\t\t=");
		System.out.println("=\t5. Change course fee\t\t\t\t=");
		System.out.println("=\t6. Quit\t\t\t\t\t\t=");
		System.out.println("=========================================================");
		displayOptionMsg();
	}
	
	public static void displayCourseName() {
		System.out.println("=\t1. Italian Cooking\t\t\t\t=");
		System.out.println("=\t2. Seafood Cooking\t\t\t\t=");
		System.out.println("=\t3. Sewing\t\t\t\t\t=");
		System.out.println("=\t4. Creative Writing\t\t\t\t=");
		System.out.println("=\t5. Business Cooking\t\t\t\t=");
		System.out.println("=\t6. Back to main menu\t\t\t\t=");
		System.out.println("=========================================================");
		displayOptionMsg();
	}
	
	public static void displayOptionMsg() {
		System.out.println("Enter an option:");
	}
	
	public static void displayFeedbackMsg(int type) {
		System.out.println("=========================================================");
		switch(type) {
		case 0:
			System.out.println("Input wrong information"); break;
		case 1:
			System.out.println("Input name again"); break;
		case 2:
			System.out.println("Input age again"); break;
		case 4:
			System.out.println("Input course id again"); break;
		case 5:
			System.out.println("The student has enrolled, fail to enrol"); break;
		case 6:
			System.out.println("Wrong information, input again."); break;
		case 7:
			System.out.println("Continue to add£¿Y/N"); break;
		default: System.out.println("");
		}
		System.out.println("=========================================================");
		System.out.println();
	}
	
	public static void displayCourseFeedback(int type, String courseName) {
		switch(type) {
			case 0: 
				String msg = "No students in %s course";
				System.out.println(String.format(msg, courseName)); break;
			case 1: System.out.println("========================================================="); break;
			case 2: System.out.println(); break;
			case 3:	System.out.println("=\t1. Back to main menu\t\t\t\t="); break;
			case 4: System.out.println("Enter a student name: \t"); break;
			case 5: System.out.println("=================== No matched student =================="); break;
		}
		System.out.println();
	}
	
	public static void displayLogoutMsg() {
		System.out.println("Logout...");
	}
	
	public static void displaySubTitle(int menuNumber) {
		switch(menuNumber) {
		case 1:
			System.out.println("===================== Add a student ====================="); break;
		case 2:
			System.out.println("========= Withdraw a student from below list ============"); break;
		case 3:
			System.out.println("========== Display a student list for a course =========="); break;
		case 4:
			System.out.println("=============== Display the course figures =============="); break;
		case 5:
			System.out.println("======= Change course fee from below list================"); break;
		}
	}
	
	public static void displaySubitem(String msg) {
		System.out.println("= "+msg+": ");
	}
}
