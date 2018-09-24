package rmit.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String id;
	private String teacherName;
	private int enrolledQuantity;
	private int maximumQuantity;
	private double charge;
	private double income;
	private double cost;
	private List students;
	
	public Course() {
		
	}

	public Course(String id, String teacherName, int enrolledQuantity, int maximumQuantity, double charge,
			double income, double cost) {
		super();
		this.id = id;
		this.teacherName = teacherName;
		this.enrolledQuantity = enrolledQuantity;
		this.maximumQuantity = maximumQuantity;
		this.charge = charge;
		this.income = income;
		this.cost = cost;
		this.students = new ArrayList();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getEnrolledQuantity() {
		return enrolledQuantity;
	}

	public void setEnrolledQuantity(int enrolledQuantity) {
		this.enrolledQuantity = enrolledQuantity;
	}

	public int getMaximumQuantity() {
		return maximumQuantity;
	}

	public void setMaximumQuantity(int maximumQuantity) {
		this.maximumQuantity = maximumQuantity;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List getStudents() {
		return students;
	}

	public void setStudents(List students) {
		this.students = students;
	}

	public String toString() {
		return String.format("%s, %s, %d,%d, %.2f, %.2f, %.2f", this.id,this.teacherName, this.enrolledQuantity,this.maximumQuantity, this.charge,this.income, this.cost);
	}
}
