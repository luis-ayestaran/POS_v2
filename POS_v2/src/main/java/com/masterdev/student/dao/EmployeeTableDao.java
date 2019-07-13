package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IEmployeeTable;
import com.masterdev.student.entities.Employee;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class EmployeeTableDao extends GenericDao<Employee, Long> implements IEmployeeTable {
	public Employee find(Employee t) throws DaoException {
		// TODO Auto-generated method stub
		Employee valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM Employee e WHERE e.name = :name";
		Employee result = (Employee) session.createQuery(queryStr)
		.setParameter("name", t.getName()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<Employee> getAll() throws DaoException {
		
		List<Employee> tableList = null;
		Session session = getHibernateSession();
		String strQuery = "FROM Employee";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
