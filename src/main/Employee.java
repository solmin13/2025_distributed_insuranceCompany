package main;

public class Employee {

	private String employeeID;
	private EmployeeType employeeType;

	public enum EmployeeType {
		Sales, UnderWriter, ProductManagement, LossAdjuster
	}

	public Employee(int numOfEmployees, EmployeeType employeeType) {
		this.employeeID = Integer.toString(numOfEmployees);
		this.employeeType = employeeType;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public String getEmployeeType() {
		return employeeID;
	}

}