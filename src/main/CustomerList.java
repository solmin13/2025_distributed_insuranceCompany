package main;

import java.util.ArrayList;

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
	
	public ArrayList<Customer> getAll();

}