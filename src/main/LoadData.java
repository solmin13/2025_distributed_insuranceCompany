package main;

import main.Data.Customer;
import java.util.HashMap;

import main.Data.InsuranceProduct;
import main.Employee.Employee.EmployeeType;
import main.Employee.LossAdjuster;
import main.Employee.ProductManagement;
import main.Employee.Sales;
import main.Enum.Sex;
import main.List.CustomerListImpl;
import main.List.EmployeeListImpl;
import main.List.InsuranceProductList;
import main.List.InsuranceProductListImpl;
import java.sql.*;

public class LoadData {
	private CustomerListImpl customerList;
	private EmployeeListImpl employeeList;
	private InsuranceProductList insuranceProductList;
	DBConnection dbConnector;
	
	public LoadData(DBConnection dbConnector, CustomerListImpl customerList, EmployeeListImpl employeeList, InsuranceProductListImpl insuranceProductList) {
		this.dbConnector = dbConnector;
		this.customerList = customerList;
		this.employeeList = employeeList;
		this.insuranceProductList = insuranceProductList;
	}
	public void loadCustomerData() {
		Customer customer1 = new Customer.Builder().accountNumber("333377645328").address("서울특별시 서대문구 북가좌동").age(23)
				.customerID(Integer.toString(0)).job("대학생").name("장소윤").rrn("60221340").sex(Sex.FEMALE)
				.phoneNumber("01077645328").build();
		Customer customer2 = new Customer.Builder().accountNumber("333346965328").address("서울특별시 서대문구 남가좌동").age(23)
				.customerID(Integer.toString(1)).job("대학생").name("박솔민").rrn("60221333").sex(Sex.FEMALE)
				.phoneNumber("01010001000").build();
		Customer customer3 = new Customer.Builder().accountNumber("333324555328").address("서울특별시 서대문구 북가좌동").age(25)
				.customerID(Integer.toString(2)).job("대학생").name("이종민").rrn("60211111").sex(Sex.FEMALE)
				.phoneNumber("01020002000").build();
		
		customerList.insert(customer1);
		customerList.insert(customer2);
		customerList.insert(customer3);
	}

	public void loadEmployeeData() {
		for (int i = 0; i < 3; i++) { // add temp three Sales
			int numOfEmployees = employeeList.employees.size();
			Sales sales = new Sales(numOfEmployees, EmployeeType.Sales, customerList);
			employeeList.insert(sales);
		}
		for (int i = 0; i < 3; i++) { // add temp three Sales
			int numOfEmployees = employeeList.employees.size();
			ProductManagement productManagement = new ProductManagement(numOfEmployees, EmployeeType.ProductManagement, customerList);
			employeeList.insert(productManagement);
		}
		for (int i = 0; i < 3; i++) { // add temp three lossAdjuster
			int numOfEmployees = employeeList.employees.size();
			LossAdjuster lossAdjuster = new LossAdjuster(numOfEmployees, EmployeeType.LossAdjuster);
			lossAdjuster.genrateDummy(10);
			employeeList.insert(lossAdjuster);
		}

	}
	public void loadInsuranceProductData() {
		String sql = "SELECT * FROM insurance_product";

		try(Statement statement = dbConnector.con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		){
			while(rs.next()){
				String productID = rs.getString("product_id");
				String productName = rs.getString("product_name");
				String productManagementID = rs.getString("product_management_id");
				int exemptionPeriod = rs.getInt("exemption_period");
				int maxAge = rs.getInt("max_age");
				int maxNumberEvent = rs.getInt("max_number_event");
				int premium = rs.getInt("premium");
				int reductionPeriod = rs.getInt("reduction_period");
				int reductionRatio = rs.getInt("reduction_ratio");
				Sex sex = null;
				String sexStr = rs.getString("sex");
				if ("MALE".equalsIgnoreCase(sexStr)) {
					sex = Sex.MALE;
				} else if ("FEMALE".equalsIgnoreCase(sexStr)) {
					sex = Sex.FEMALE;
				}

				String coverageJson = rs.getString("coverage_by_age");
				HashMap<String, String> coverageByAge = parseCoverageJson(coverageJson);

				InsuranceProduct.InsuranceBuilder builder = new InsuranceProduct.InsuranceBuilder()
						.productID(productID)
						.productName(productName)
						.productManagementID(productManagementID)
						.exemptionPeriod(exemptionPeriod)
						.maxAge(maxAge)
						.maxNumberEvent(maxNumberEvent)
						.premium(premium)
						.reductionPeriod(reductionPeriod)
						.reductionRatio(reductionRatio)
						.sex(sex)
						.coverageByAge(coverageByAge);

				InsuranceProduct product = new InsuranceProduct(builder);
				insuranceProductList.insert(product);

			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	private HashMap<String, String> parseCoverageJson(String json) {
		HashMap<String, String> map = new HashMap<>();
		json = json.trim();
		if(json.startsWith("{"))
			json = json.substring(1);
		if(json.endsWith("}"))
			json = json.substring(0,json.length()-1);
		String[] pairs = json.split(",");
		for(String pair : pairs){
			String[] keyValue = pair.split(":");
			if(keyValue.length==2){
				String key = keyValue[0].trim().replace("^\"|\"$", "");
				String value = keyValue[1].trim().replaceAll("^\"|\"$", "");
				map.put(key,value);
			}
		}
		return map;
	}
	public void loadContractData() {
		
	}
}
