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
		if(type == 0)
			System.out.println("Input wrong information");
		else if(type == 1)
			System.out.println("Input name again");
		else if(type == 2)
			System.out.println("Input age again");
		else if(type == 3)
			System.out.println("");
		else if(type == 4)
			System.out.println("Input course id again");
		else if(type == 5)
			System.out.println("The student has enrolled, fail to enrol");
		else if(type == 6)
			System.out.println("Wrong information, input again.");
		else if(type == 7)
			System.out.println("Continue to add£¿Y/N");
		System.out.println("=========================================================");
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
			System.out.println("===================== Withdraw a student ================"); break;
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
