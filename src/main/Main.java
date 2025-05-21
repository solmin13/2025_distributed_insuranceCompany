package main;

import main.Employee.Employee;
import main.List.CustomerListImpl;
import main.List.EmployeeListImpl;
import main.List.InsuranceProductList;
import main.List.InsuranceProductListImpl;

public class Main {

	private static Employee loginedEmployee;
	private static Menu menu;
	private static LoadData loadData;
	private static CustomerListImpl customerList;
	private static EmployeeListImpl employeeList;
	private static InsuranceProductListImpl insuranceProductList;

	public static void main(String[] args) {
		DBConnection dbConnection = new DBConnection();
		customerList = new CustomerListImpl();
		employeeList = new EmployeeListImpl();
		insuranceProductList = new InsuranceProductListImpl();
		loadData = new LoadData(dbConnection, customerList, employeeList, insuranceProductList);

		loadData.loadCustomerData();
		loadData.loadEmployeeData();
		loadData.loadInsuranceProductData();
		
		loginedEmployee = login("4");


		Menu menu = new Menu(customerList, employeeList,insuranceProductList, loginedEmployee);
		while (true) {
			menu.printMainMenu();
			int selectedMenu = menu.getUserSelectInt();
			menu.excuteSelectedMenu(selectedMenu);
		}

	}

	public static Employee login(String loginID) {
		return employeeList.search(loginID);
	}

}
