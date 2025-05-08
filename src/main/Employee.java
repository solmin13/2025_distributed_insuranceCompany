package main;
/**
 * @author �ڼֹ�
 * @version 1.0
 * @created 05-5-2025 ���� 12:24:25
 */
public class Employee {

	public String employeeID;

	public Employee(){
		this.employeeID = RandomIdGenerater.Generate();
	}

	public String getEmployeeID() {
		return employeeID;
	}


}