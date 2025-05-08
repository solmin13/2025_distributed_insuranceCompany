package main;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author 占쌘솔뱄옙
 * @version 1.0
 * @created 05-5-2025 占쏙옙占쏙옙 11:05:24
 */
public class EmployeeListImpl implements EmployeeList {

	public ArrayList<Employee> employees;

	public EmployeeListImpl(){

	}

	/**
	 *
	 * @param employeeID
	 */
	public boolean delete(String employeeID){
// Iterator瑜� �ъ�⑺���� ConcurrentModificationException�� 諛⑹���硫댁�� ������寃� ����
		Iterator<Employee> iterator = employees.iterator();
		while (iterator.hasNext()) {
			Employee employee = iterator.next();
			if (employee.getEmployeeID().equals(employeeID)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param employee
	 */
	public boolean insert(Employee employee){
		return this.employees.add(employee);
	}

	/**
	 *
	 * @param employeeID
	 */
	public Employee search(String employeeID){
		for (Employee employee : employees) {
			if (employee.getEmployeeID().equals(employeeID)) {
				return employee;
			}
		}
		return null;
	}

	/**
	 *
	 * @param updatedEmployee
	 */
	public boolean update(Employee updatedEmployee){
		for (int i = 0; i < employees.size(); i++) {
			Employee existingCustomer = employees.get(i);
			if (existingCustomer.getEmployeeID().equals(updatedEmployee.getEmployeeID())) {
				employees.set(i, updatedEmployee);
				return true;
			}
		}
		return false;
	}

}