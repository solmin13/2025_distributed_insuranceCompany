package main;

import java.util.*;

import main.Employee.EmployeeType;

public class Main {

	private static String loginedEmployeeID;
	private static Menu menu;
	private static LoadData loadData;
	private static CustomerListImpl customerList;
	private static EmployeeListImpl employeeList;

	public static void main(String[] args) {
		customerList = new CustomerListImpl();
		employeeList = new EmployeeListImpl();
		loadData = new LoadData(customerList, employeeList);

		loadData.loadCustomerData();
		loadData.loadEmployeeData();

		loginedEmployeeID = "1"; // TODO: implement login
		
		Menu menu = new Menu(customerList, employeeList, loginedEmployeeID);
		while (true) {
			menu.printMainMenu();
			int selectedMenu = menu.getUserSelectInt();
			menu.excuteSelectedMenu(selectedMenu);
		}

	}

}
