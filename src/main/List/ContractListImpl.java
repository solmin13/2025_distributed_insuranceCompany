package main.List;
import main.Data.Contract;

public class ContractListImpl implements ContractList {

	public Contract m_Contract;

	public ContractListImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param contractID
	 */
	public boolean delete(String contractID){
		return false;
	}

	/**
	 * 
	 * @param contract
	 */
	public boolean insert(Contract contract){
		return false;
	}

	/**
	 * 
	 * @param contractID
	 */
	public Contract search(int contractID){
		return null;
	}

	/**
	 * 
	 * @param contract
	 */
	public boolean update(Contract contract){
		return false;
	}

}