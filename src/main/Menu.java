package main;

import java.util.*;

import main.Employee.EmployeeType;

public class Menu {

	private Employee loginedEmployee;
	private CustomerListImpl customerList;
	private EmployeeListImpl employeeList;
	private InsuranceProductList insuranceProductList;
	private Scanner scanner;

	public Menu(CustomerListImpl customerList, EmployeeListImpl employeeList, InsuranceProductList insuranceProductList,
			Employee loginedEmployee) {
		this.loginedEmployee = loginedEmployee;
		this.customerList = customerList;
		this.employeeList = employeeList;
		this.insuranceProductList = insuranceProductList;
		this.scanner = new Scanner(System.in);
	}

	public void printMainMenu() {
		EmployeeType loginedEmployeeType = loginedEmployee.getEmployeeType();

		String[] menuList = {};
		if (loginedEmployeeType == EmployeeType.Sales) {
			String[] salesMenuList = { "add customer", "delete customer", "modify customer", "search customer" };
			menuList = salesMenuList;
		} else if (loginedEmployeeType == EmployeeType.ProductManagement) {
			String[] productManagementMenuList = { "상품 등록", "상품 수정", "상품 조회", "상품 삭제" };
			menuList = productManagementMenuList;
		}

		System.out.println("Select Menu:");
		for (int i = 0; i < menuList.length; i++) {
			System.out.printf("%d. %s \n", i + 1, menuList[i]);
		}
		System.out.println("\nEnter 0 for EXIT.");
	}

	public void excuteSelectedMenu(int selectedMenu) {
		EmployeeType loginedEmployeeType = loginedEmployee.getEmployeeType();
		if (loginedEmployeeType == EmployeeType.Sales) {
			switch (selectedMenu) {
			case 0:
				System.out.println("Good Bye...");
				System.exit(0);
			case 1:
				createCustomer();
			case 2:
				deleteCustomer();
			case 3:
				updateCustomer();
			case 4:
				searchCustomer();
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				break;
			}
		} else if (loginedEmployeeType == EmployeeType.ProductManagement) {
			switch (selectedMenu) {
			case 0:
				System.out.println("Good Bye...");
				System.exit(0);
			case 1:
				createInsuaranceProduct();
				break;
			case 2:
				updateInsuaranceProduct();
				break;
			case 3:
				searchInsuaranceProduct();
				break;
			case 4:
				deleteInsuaranceProduct();
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				break;
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
		Sex sexStr = checkSexInput();

		Sales sales = (Sales) loginedEmployee;
		if (sales.createCustomer(accountNumber, address, age, customerID, job, name, phoneNumber, rrn, sexStr)) {
			System.out.println("Customer added successfully.");
		} else
			System.out.printf("false");
	}

	private void searchCustomer() {
		Sales sales = (Sales) loginedEmployee;

		System.out.println("Customer List===");
		ArrayList<Customer> customers = sales.getAllCustomer();
		for (Customer customer : customers) {
			System.out.println(customer.getCustomerID() + " " + customer.getName());
		}
	}

	private void updateCustomer() {
		Sales sales = (Sales) loginedEmployee;

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

	private void deleteCustomer() {
		Sales sales = (Sales) loginedEmployee;

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

	public int getUserSelectInt() {
		System.out.print(">> ");
		return Integer.parseInt(scanner.nextLine());
	}

	public String getUserSelectStr() {
		System.out.print(">> ");
		return scanner.nextLine();
	}

	private String getUserInputStr(String title) {
		String input = "";
		do {
			System.out.print(title + ": ");
			input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("* this field cannot be null.");
			}
		} while (input.isEmpty());
		return input;
	}

	private int getUserInputInt(String title) {
		return Integer.parseInt(getUserInputStr(title));
	}
// ==============================

	public void createInsuaranceProduct() {

		String productName = getUserInputStr("product name");

		int maxAge = getUserInputInt("max age");
		int maxNumberEvent = getUserInputInt("max number event");
		int premium = getUserInputInt("premium");
		int reductionPeriod = getUserInputInt("reduction period");
		int reductionRatio = getUserInputInt("reduction ratio");
		Sex sex = checkSexInput();
		int exemptionPeriod = getUserInputInt("exemption period");
		HashMap<String, String> coverageByAge = checkHashMap();

		ProductManagement manager = (ProductManagement) loginedEmployee;
		if (manager.createProduct(insuranceProductList, coverageByAge, exemptionPeriod, reductionPeriod, reductionRatio,
				productName, sex, premium, maxAge, maxNumberEvent))
			System.out.println("상품이 정상적으로 등록되었습니다.");
		else // 같은 이름의 상품이 있는 경우 예외 처리
			System.out.println("같은 이름의 상품이 있어 등록이 실해했습니다.");
	}

	public void searchInsuaranceProduct() {
		ProductManagement manager = (ProductManagement) loginedEmployee;
		int index = 0;
		final int maxCount = 10;
		String input = "";

		if (insuranceProductList.size() == 0) {
			System.out.println("등록된 상품이 없습니다.");
			return;
		}

		while (index < insuranceProductList.size()) {
			int end = Math.min(index + maxCount, insuranceProductList.size());
			System.out.println("=== 보험 상품 ===");
			for (int i = index; i < end; i++) {
				InsuranceProduct product = insuranceProductList.getProduct(i);
				System.out.println(index + 1 + ". " + product.getProductID() + " " + product.getProductName() + " "
						+ product.getProductManagementID());
			}
			index = end;
			System.out.println("조회하고 싶은 상품을 선택해주세요.");
			if (index >= insuranceProductList.size())
				System.out.println("모든 상품을 다 출력했습니다.");
			else
				System.out.println("다음 페이지로 넘어가려면 'next', 키워드 검색을 원하시면 'search', 조회 종료를 원하시면 'end'를 입력해주세요.");
			input = scanner.nextLine();
			if (input.equals("search")) {
				searchKeyWord();
				break;
			} else if (input.equals("end")) {
				System.out.println("상품 조회를 종료합니다.");
				break;
			} else if (!input.equals("next")) {
				try {
					index = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					index = getUserInputInt("잘못된 입력입니다. 번호를 다시 입력해주세요 ");
				}
				System.out.println(manager.getProduct(insuranceProductList, index - 1).toString());
				break;
			}
		}
	}

	public void searchKeyWord() {
		ProductManagement manager = (ProductManagement) loginedEmployee;
		InsuranceProductList products = null;

		System.out.println("원하는 키워드를 선택해주세요.");
		System.out.println("1.product id \n2.product name \n3.product management id");
		int chooseMenu = getUserSelectInt();

		switch (chooseMenu) {
		case 1:
			String checkProductID = getUserInputStr("찾으려는 상품의 ID를 입력하세요");
			products = manager.searchProducts(insuranceProductList, "productID", checkProductID);
			break;
		case 2:
			String checkProductName = getUserInputStr("찾으려는 상품의 이름을 입력하세요");
			products = manager.searchProducts(insuranceProductList, "productName", checkProductName);
			break;
		case 3:
			String checkProductManagerID = getUserInputStr("찾으려는 상품의 상품관리자 id를 입력하세요");
			products = manager.searchProducts(insuranceProductList, "productManagementID", checkProductManagerID);
			break;
		}

		insuranceProductList.printAllProducts();
	}

	public void updateInsuaranceProduct() {
		String productID = getUserInputStr("업데이트하려는 상품의 ID를 적어주세요");

		ProductManagement manager = (ProductManagement) loginedEmployee;
		InsuranceProduct product = manager.searchProduct(insuranceProductList, productID);
		if (product == null) {
			System.out.println("일치하는 상품이 없습니다.");
			return;
		}

		System.out.println("수정하려는 정보를 선택해주세요.");
		String[] menuList = { "product name", "max age", "max number event", "premium", "reduction period",
				"reduction ratio", "sex", "exemption period", "coverage by age" };
		for (int i = 0; i < menuList.length; i++) {
			System.out.println((i + 1) + " " + menuList);
		}
		int chooseMenu = getUserSelectInt();
		System.out.println("수정된 값을 입력해주세요 : ");
		boolean result = false;

		switch (chooseMenu) {
		case 1:
			result = product.setProductName(scanner.nextLine());
			break;
		case 2:
			result = product.setMaxAge(getUserInputInt("product name"));
			break;
		case 3:
			result = product.setMaxNumberEvent(getUserInputInt("max number event"));
			break;
		case 4:
			result = product.setPremium(getUserInputInt("premium"));
			break;
		case 5:
			result = product.setReductionPeriod(getUserInputInt("reduction period"));
			break;
		case 6:
			result = product.setReductionRatio(getUserInputInt("reduction ratio"));
			break;
		case 7:
			result = product.setSex(checkSexInput());
			break;
		case 8:
			result = product.setExemptionPeriod(getUserInputInt("exemption period"));
			break;
		case 9:
			result = product.setCoverageByAge(checkHashMap());
			break;
		}

		if (result)
			System.out.println("성공적으로 수정되었습니다.");
		else
			System.out.println("문제가 발생하였습니다.");
	}

	public void deleteInsuaranceProduct() {
		ProductManagement manager = (ProductManagement) loginedEmployee;
		String input = null;
		boolean result = false;

		while (true) {
			input = getUserInputStr("삭제하려는 상품의 ID를 입력해주세요");
			if (input != null) {
				result = manager.deleteProduct(insuranceProductList, input);
				break;
			}
		}
		if (result)
			System.out.println("삭제가 완료되었습니다.");
		else
			System.out.println("문제가 발생했습니다.");
	}

	/**
	 * string입력 값을 HashMap<String,String>으로 변환
	 * 
	 * @param scanner
	 * @return coverageByAge값을 HashMap<String,String>으로 반환
	 */
	public HashMap<String, String> checkHashMap() {
		HashMap<String, String> hash = new HashMap<>();
		while (true) {
			System.out.println("coverage by age : ");
			String coverageByAgStrings = scanner.nextLine();
			String[] array = coverageByAgStrings.split(" ");
			if (array.length % 2 != 0) {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				continue;
			}
			for (int i = 0; i < array.length - 1; i += 2)
				hash.put(array[i], array[i + 1]);
			break;
		}
		return hash;
	}

	/**
	 * 입력값에 따라 Sex 결정
	 * 
	 * @param scanner
	 * @return 입력값에 따른 Sex 반환
	 */
	public Sex checkSexInput() {
		Sex sex = null;
		String value = "";
		while (true) {
			System.out.println("sex : ");
			value = scanner.nextLine();
			if (value.equals("m") || value.equals("M") || value.equals("Male") || value.equals("male")) {
				sex = Sex.MALE;
				break;
			} else if (value.equals("f") || value.equals("F") || value.equals("Female") || value.equals("female")) {
				sex = Sex.FEMALE;
				break;
			}
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
		return sex;
	}
}
