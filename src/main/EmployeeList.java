package main;


public interface EmployeeList {
		/**
		 *
		 * @param employeeID
		 */
		public boolean delete(String employeeID);

		/**
		 *
		 * @param employee
		 */
		public boolean insert(Employee employee);

		/**
		 *
		 * @param employeeID
		 */
		public Employee search(String employeeID);

		/**
		 *
		 * @param employee
		 */
		public boolean update(Employee employee);

	}