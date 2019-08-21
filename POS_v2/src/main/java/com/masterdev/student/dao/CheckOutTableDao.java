package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ICheckOutTable;
import com.masterdev.student.entities.CheckOut;
import com.masterdev.student.exceptions.DaoException;

public class CheckOutTableDao extends GenericDao<CheckOut, Long> implements ICheckOutTable {
	public CheckOut find(CheckOut t) throws DaoException {
		// TODO Auto-generated method stub
		CheckOut valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM CheckIn e WHERE e.date = :date";
		CheckOut result = (CheckOut) session.createQuery(queryStr)
		.setParameter("date", t.getDate()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<CheckOut> getAll() throws DaoException {
		
		List<CheckOut> tableList = null;
		Session session = getHibernateSession();
		String strQuery = "FROM CheckOut";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}