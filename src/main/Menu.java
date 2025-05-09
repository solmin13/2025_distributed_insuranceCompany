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

		System.out.print("Account Number: ");
		String accountNumber = scanner.nextLine();

		System.out.print("Address: ");
		String address = scanner.nextLine();

		System.out.print("Age: ");
		int age = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Job: ");
		String job = scanner.nextLine();

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Phone Number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("RRN: ");
		String rrn = scanner.nextLine();

		System.out.print("Sex (M/F): ");
		String sexStr = scanner.nextLine();
		Sex sex = sexStr.equalsIgnoreCase("M") ? Sex.MALE : Sex.FEMALE;

		Sales sales = new Sales(this.customerList);
		if (sales.createCustomer(accountNumber, address, age, job, name, phoneNumber, rrn, sex)) {
			System.out.println("Customer added successfully.");
		} else
			System.out.printf("false");
	}

}
