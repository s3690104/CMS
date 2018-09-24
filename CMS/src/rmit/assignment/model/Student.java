package rmit.assignment.model;

public class Student {
	private String studentName;
	private String address = "Address Unknown";
	private int age;
	private double courseFee;
	
	public Student() {
		super();
	}

	public Student(String studentName, String address, int age) {
		super();
		this.studentName = studentName;
		this.address = address;
		this.age = age;
		this.courseFee = 0;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}
	
	public String toString() {
//		return String.format("Name: %s, Address: %s, Age: %d, Fee: %.2f", this.studentName,this.address,this.age,this.courseFee);
		return String.format("%s, %s, %d", this.studentName,this.address,this.age);
	}

}
