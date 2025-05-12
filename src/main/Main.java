package main;

import java.util.*;

import main.Employee.EmployeeType;

public class Main {
	private static CustomerListImpl customerList;
	private static EmployeeListImpl employeeList;
	private static String loginedEmployeeID;

	public static void main(String[] args) {
		customerList = new CustomerListImpl();
		employeeList = new EmployeeListImpl();
		LoadData loadData = new LoadData(customerList, employeeList);

		loadData.loadCustomerData();

		loadData.loadEmployeeData();

		loginedEmployeeID = "1";
		Menu menu = new Menu(customerList, employeeList,loginedEmployeeID);
		while (true) {
			menu.printMainMenu();
			int selectedMenu = menu.getUserSelectInt();
			menu.excuteMenu(selectedMenu);
		}

	}

}
