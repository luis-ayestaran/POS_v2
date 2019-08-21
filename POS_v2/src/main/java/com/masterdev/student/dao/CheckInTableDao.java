package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ICheckInTable;
import com.masterdev.student.entities.CheckIn;
import com.masterdev.student.exceptions.DaoException;

public class CheckInTableDao extends GenericDao<CheckIn, Long> implements ICheckInTable {
	public CheckIn find(CheckIn t) throws DaoException {
		// TODO Auto-generated method stub
		CheckIn valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM CheckIn e WHERE e.date = :date";
		CheckIn result = (CheckIn) session.createQuery(queryStr)
		.setParameter("date", t.getDate()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<CheckIn> getAll() throws DaoException {
		
		List<CheckIn> tableList = null;
		Session session = getHibernateSession();
		String strQuery = "FROM CheckIn";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}

