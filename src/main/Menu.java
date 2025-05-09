package main;

import java.util.*;

public class Menu {

	private CustomerList customerList;
	private Scanner scanner;

	public Menu(Scanner scanner) {
		this.scanner = scanner;
		this.customerList = new CustomerListImpl();
	}

	public void printMainMenu() {
		System.out.println("Select Menu:");
		String[] menuName = { "add customer", "delete customer", "search customer", "modify customer" };
		for (int i = 0; i < menuName.length; i++) {
			System.out.printf("%d. %s \n", i + 1, menuName[i]);
		}
		System.out.println("\nEnter 0 for EXIT.");
	}

	public int getUserSelect() {
		System.out.print(">> ");
		return Integer.parseInt(scanner.nextLine());
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
		String job = getUserInputStr("Job");
		String name = getUserInputStr("Name");
		String phoneNumber = getUserInputStr("Phone Number");
		String rrn = getUserInputStr("RRN");
		String sexStr = getUserInputStr("Sex (M/F)");
		Sex sex = sexStr.equalsIgnoreCase("M") ? Sex.MALE : Sex.FEMALE;

		Sales sales = new Sales(this.customerList);
		if (sales.createCustomer(accountNumber, address, age, job, name, phoneNumber, rrn, sex)) {
			System.out.println("Customer added successfully.");
		} else
			System.out.printf("false");
	}

	private String getUserInputStr(String title) {
		System.out.print(title + ": ");
		return scanner.nextLine();
	}

	private int getUserInputInt(String title) {
		return Integer.parseInt(getUserInputStr(title));
	}

}
