package main;

import main.Employee.EmployeeType;

public class LoadData {
	private CustomerListImpl customerList;
	private EmployeeListImpl employeeList;
	public LoadData(CustomerListImpl customerList,EmployeeListImpl employeeList) {
		this.customerList = customerList;
		this.employeeList = employeeList;
	}
	public void loadCustomerData() {
		for (int i = 0; i < 3; i++) { // add temp three Customer
			int numOfEmployees = customerList.customers.size();
			Customer customer = new Customer.Builder().accountNumber("111").address("111").age(11)
					.customerID(Integer.toString(i + 1)).job("111").name("111").rrn("111").sex(Sex.FEMALE)
					.phoneNumber("111").build();
			customerList.insert(customer);
		}

	}

	public void loadEmployeeData() {
		for (int i = 0; i < 3; i++) { // add temp three Sales
			int numOfEmployees = employeeList.employees.size();
			Sales sales = new Sales(numOfEmployees, EmployeeType.Sales, customerList);
			employeeList.insert(sales);
		}

	}
}
