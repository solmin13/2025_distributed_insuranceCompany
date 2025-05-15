package main.Data;

import java.time.LocalDate;
import java.util.*;

public class Contract {

	private Date contractDate;
	private String contractID;
	private String customerID;
	private LocalDate expirationDate;
	private String productID;
	private String salesID;
	private Enum state;
	private InsuranceProduct m_InsuranceProduct;

	public Contract(){

	}
	
	public class Builder{
		private Date contractDate;
		private String contractID; 
		private String customerID;	//userInput
		private LocalDate expirationDate;	//userInput
		private String productID;	//userInput
		private String salesID;		//userInput
		private Enum state;
		private InsuranceProduct m_InsuranceProduct;
	}
	
	

}