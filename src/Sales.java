

/**
 * @author 占쌘솔뱄옙
 * @version 1.0
 * @created 05-5-2025 占쏙옙占쏙옙 11:05:25
 */
public class Sales extends Employee {

	public CustomerList customerList;
//	public Contract m_Contract;

	public Sales(){
		this.customerList = new CustomerListImpl();
	}



	/**
	 * 
	 * @param 占쏙옙占쏙옙占�ID
	 * @param 占쏙옙품ID
	 */
//	public boolean createContract(String 占쏙옙占쏙옙占�ID, String 占쏙옙품ID){
//		return false;
//	}

	/**
	 * 
	 * 	@param accountNumber;
	 * 	@param address; // String �����쇰� 蹂�寃�
	 *  @param age;
	 * 	@param customerID;
	 * 	@param job;
	 * 	@param name;
	 * 	@param phoneNumber; // �대� 蹂�寃�
	 * 	@param rrn;
	 * 	@param sex; // Sex enum ���� �ъ��
	 */
	public boolean createCustomer(
		String accountNumber, String address, int age, String job, String name,
			String phoneNumber, String rrn, Sex sex) {
		Customer customer = new Customer.Builder()
				.accountNumber(accountNumber)
				.address(address)
				.age(age)
				.job(job)
				.name(name)
				.rrn(rrn)
				.sex(sex)
				.phoneNumber(phoneNumber)
				.build();
		System.out.println(customer);
		return customerList.insert(customer);
	}
	
	public Customer getCustomer(String customerId) {
		return customerList.search(customerId);
	}

	/**
	 *
	 * @param ID
	 * @param 품ID
	 */
//	public boolean createContract(String ID, String 품ID, String customerID){
//		// Contract.class�� ������ �댁�⑹�� ���쇰�명�곕� 諛�怨�, 鍮������댁�� �듯�� ���깊��, ContractList�� 異�媛���怨�, �깃났��硫� True, �ㅽ�⑦��硫� flase瑜� 由ы�댄���� 硫�����
//		Contract contract = new Contract.Builder(ID, 품ID, customerID).build();
//		return ContractList.add(contract);
//	}

	/**
	 *
	 * @param ID
	 */
//	public boolean deleteContract(String ID){
//		return ContractList.delete(ID);
//	}

	/**
	 *
	 * @param CustomerID
	 */
//	public boolean deleteCustomer(String CustomerID){
//		return CustomerList.delete(CustomerID);
//	}

	/**
	 *
	 * @param Contract
	 */
//	public boolean updateContract(Contract Contract){
//		return ContractList.update(Contract);
//	}

	/**
	 *
	 * @param Customer
	 */
//	public boolean updateCustomer(Customer Customer){
//		return CustomerList.update(Customer);
//	}
//
//	}

	/**
	 * 
	 * @param 占쏙옙占�ID
	 */
//	public boolean deleteContract(String 占쏙옙占�ID){
//		return false;
//	}

	/**
	 * 
	 * @param CustomerID
	 */
//	public boolean deleteCustomer(String CustomerID){
//		return false;
//	}

	/**
	 * 
	 * @param Contract
	 */
//	public boolean updateContract(Contract Contract){
//		return false;
//	}

	/**
	 * 
	 * @param Customer
	 */
//	public boolean updateCustomer(Customer Customer){
//		return false;
//	}

}