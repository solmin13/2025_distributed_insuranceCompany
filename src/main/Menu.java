package main;

import java.util.*;

public class Menu {

	private CustomerListImpl customerList;
	private EmployeeListImpl employeeList;
	private String loginedEmployeeID;
	private Scanner scanner;

	public Menu(CustomerListImpl customerList, EmployeeListImpl employeeList, String loginedEmployeeID) {
		this.scanner = new Scanner(System.in);
		this.customerList = customerList;
		this.employeeList = employeeList;
		this.loginedEmployeeID = loginedEmployeeID;
		
	}

	public void printMainMenu() {
		System.out.println("Select Menu:");
		String[] menuName = { "add customer", "delete customer", "search customer", "modify customer" };
		for (int i = 0; i < menuName.length; i++) {
			System.out.printf("%d. %s \n", i + 1, menuName[i]);
		}
		System.out.println("\nEnter 0 for EXIT.");
	}

	public void excuteMenu(int selectedMenu) {
		
		switch (selectedMenu) {
		case 0: {
			System.out.println("Good Bye...");
			System.exit(0);
		}
		case 1: {
			createCustomer();
		}

		}
	}

	private void createCustomer() {
		System.out.println("Enter customer details:");

		String accountNumber = getUserInputStr("Account Number");
		String address = getUserInputStr("Address");
		int age = getUserInputInt("Age");
		String customerID = Integer.toString(customerList.customers.size());
		String job = getUserInputStr("Job");
		String name = getUserInputStr("Name");
		String phoneNumber = getUserInputStr("Phone Number");
		String rrn = getUserInputStr("RRN");
		String sexStr = getUserInputStr("Sex (M/F)");
		Sex sex = sexStr.equalsIgnoreCase("M") ? Sex.MALE : Sex.FEMALE;

		
		Sales sales = (Sales)employeeList.search(loginedEmployeeID);
		if (sales.createCustomer(accountNumber, address, age, customerID, job, name, phoneNumber, rrn, sex)) {
			System.out.println("Customer added successfully.");
		} else
			System.out.printf("false");
	}

	public int getUserSelectInt() {
		System.out.print(">> ");
		return Integer.parseInt(scanner.nextLine());
	}

	public String getUserSelectStr() {
		System.out.print(">> ");
		return scanner.nextLine();
	}

	private String getUserInputStr(String title) {
		System.out.print(title + ": ");
		return scanner.nextLine();
	}

	private int getUserInputInt(String title) {
		return Integer.parseInt(getUserInputStr(title));
	}

}
