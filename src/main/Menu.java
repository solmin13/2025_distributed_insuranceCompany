package main;

import java.util.*;

public class Menu {

	private CustomerListImpl customerList;
	private EmployeeListImpl employeeList;
	private String loginedEmployeeID;
	private Scanner scanner;

	public Menu(CustomerListImpl customerList, EmployeeListImpl employeeList, String loginedEmployeeID) {
		this.customerList = customerList;
		this.employeeList = employeeList;
		this.loginedEmployeeID = loginedEmployeeID;
		this.scanner = new Scanner(System.in);
	}

	public void printMainMenu() {
		System.out.println("Select Menu:");
		String[] menuName = { "add customer", "delete customer", "modify customer", "search customer" };
		for (int i = 0; i < menuName.length; i++) {
			System.out.printf("%d. %s \n", i + 1, menuName[i]);
		}
		System.out.println("\nEnter 0 for EXIT.");
	}

	public void excuteSelectedMenu(int selectedMenu) {

		switch (selectedMenu) {
		case 0: {
			System.out.println("Good Bye...");
			System.exit(0);
		}
		case 1: {
			createCustomer();
		}
		case 2: {
			deleteCustomer();
		}
		case 3: {
			updateCustomer();
		}
		case 4: {
			searchCustomer();
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

		Sales sales = (Sales) employeeList.search(loginedEmployeeID);
		if (sales.createCustomer(accountNumber, address, age, customerID, job, name, phoneNumber, rrn, sex)) {
			System.out.println("Customer added successfully.");
		} else
			System.out.printf("false");
	}

	private void deleteCustomer() {
		Sales sales = (Sales) employeeList.search(loginedEmployeeID);

		System.out.println("Customer List===");
		ArrayList<Customer> customers = sales.getAllCustomer();
		for (Customer customer : customers) {
			System.out.println(customer.getCustomerID() + " " + customer.getName());
		}
		System.out.println("Enter customer ID to delete.");
		String customerID = getUserInputStr("customerID");

		String log = "";
		if (sales.deleteCustomer(customerID)) {
			log = "Customer(" + customerID + ") deleted successfully.";
		} else {
			log = "Failed: cannot delete customer(" + customerID + ")";
		}
		System.out.println(log);

	}

	private void updateCustomer() {
		Sales sales = (Sales) employeeList.search(loginedEmployeeID);

		System.out.println("Customer List===");
		ArrayList<Customer> customers = sales.getAllCustomer();
		for (Customer customer : customers) {
			System.out.println(customer.getCustomerID() + " " + customer.getName());
		}
		System.out.println("Enter customer ID to update.");
		String customerID = getUserInputStr("customerID");

		System.out.println("Enter customer details:");
		String accountNumber = getUserInputStr("Account Number");
		String address = getUserInputStr("Address");
		int age = getUserInputInt("Age");
		String job = getUserInputStr("Job");
		String name = getUserInputStr("Name");
		String phoneNumber = getUserInputStr("Phone Number");
		String rrn = getUserInputStr("RRN");
		String sexStr = getUserInputStr("Sex (M/F)");
		Sex sex = sexStr.equalsIgnoreCase("M") ? Sex.MALE : Sex.FEMALE;
		String log = "";
		if (sales.updateCustomer(accountNumber, address, age, customerID, job, name, phoneNumber, rrn, sex)) {
			log = "Customer(" + customerID + ") updated successfully.";
		} else {
			log = "Failed: cannot update customer(" + customerID + ")";
		}
		System.out.println(log);

	}

	private void searchCustomer() {
		Sales sales = (Sales) employeeList.search(loginedEmployeeID);

		System.out.println("Customer List===");
		ArrayList<Customer> customers = sales.getAllCustomer();
		for (Customer customer : customers) {
			System.out.println(customer.getCustomerID() + " " + customer.getName());
		}
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
		
		String input ="";
		do {
			System.out.print(title + ": ");
			input = scanner.nextLine().trim();
			if(input.isEmpty()) {
				System.out.println("* this field cannot be null.");
			}
		}while(input.isEmpty());
		return input;
	}

	private int getUserInputInt(String title) {
		return Integer.parseInt(getUserInputStr(title));
	}

}
