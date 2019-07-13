package com.masterdev.student.services;

import java.util.List;

import com.masterdev.student.dao.EmployeeTableDao;
import com.masterdev.student.entities.Employee;
import com.masterdev.student.entities.Product;
import com.masterdev.student.exceptions.DaoException;

public class PersonnelService {
	private EmployeeTableDao employeeTableDao;
	
	public PersonnelService() {
		this.employeeTableDao = new EmployeeTableDao();
	}
	
	public Employee searchEmployee(Employee employee) {
		Employee valueReturn = null;
		Employee employeeParam = null;
		try {
			employeeParam = employeeTableDao.find(employee);
			if(employeeParam != null)
				valueReturn = employeeParam;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valueReturn;
	}
	
	public void registrateEmployee(Employee employee) {
		try {
			employeeTableDao.save(employee);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee employee) {
		try {
			employeeTableDao.update(employee);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public List<Employee> showEmployees() {
		List<Employee> list = null;
		try {
			list = employeeTableDao.getAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
