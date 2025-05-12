public class Employee {

	public String employeeID;

	public Employee(){
		this.employeeID = RandomIdGenerater.Generate();
	}

	public String getEmployeeID() {
		return employeeID;
	}


}