


/**
 * @author �ڼֹ�
 * @version 1.0
 * @created 05-5-2025 ���� 11:05:24
 */
public interface CustomerList {

	/**
	 * 
	 * @param customerID
	 */
	public boolean delete(String customerID);

	/**
	 * 
	 * @param customer
	 */
	public boolean insert(Customer customer);

	/**
	 * 
	 * @param customerID
	 */
	public Customer search(String customerID);

	/**
	 * 
	 * @param customer
	 */
	public boolean update(Customer customer);

}