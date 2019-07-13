package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.Employee;
import com.masterdev.student.exceptions.DaoException;

public interface IEmployeeTable extends IGenericDao<Employee, Long> {
	public List<Employee> getAll() throws DaoException;
}
