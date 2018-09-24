package rmit.assignment.view;

public class Menu {
	public static void displayMainMenu() {
		System.out.println("\t\tCourse Management System");
		System.out.println("=========================================================");
		System.out.println("=\t1. Add a student\t\t\t\t=");
		System.out.println("=\t2. Withdraw a student\t\t\t\t=");
		System.out.println("=\t3. Display a student list for a course\t\t=");
		System.out.println("=\t4. Display the course figures\t\t\t=");
		System.out.println("=\t5. Quit\t\t\t\t\t\t=");
		System.out.println("=========================================================");
	}
	
	public static void displayOptionMsg() {
		System.out.println("Enter an option:");
	}
	
	public static void displayErrorMsg(int type) {
		if(type == 0)
			System.out.println("Input wrong information, choose again:");
		else if(type == 1)
			System.out.println("Input student's name again");
		else if(type == 2)
			System.out.println("Input age again");
		else if(type == 3)
			System.out.println("Input course id again");
	}
	
	public static void displayLogoutMsg() {
		System.out.println("Logout...");
	}
}
