package main;

import java.util.*;

import main.Employee.EmployeeType;

public class Main {

	private static Employee loginedEmployee;
	private static Menu menu;
	private static LoadData loadData;
	private static CustomerListImpl customerList;
	private static EmployeeListImpl employeeList;
	private static InsuranceProductList insuranceProductList;

	public static void main(String[] args) {
		customerList = new CustomerListImpl();
		employeeList = new EmployeeListImpl();
		insuranceProductList = new InsuranceProductListImpl();
		loadData = new LoadData(customerList, employeeList);

		loadData.loadCustomerData();
		loadData.loadEmployeeData();
		loadData.loadInsuranceProductData();
		
		loginedEmployee = login("6");

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
