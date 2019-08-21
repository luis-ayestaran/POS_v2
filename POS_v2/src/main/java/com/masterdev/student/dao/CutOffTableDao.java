package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ICutOffTable;
import com.masterdev.student.entities.CutOff;
import com.masterdev.student.exceptions.DaoException;

public class CutOffTableDao extends GenericDao<CutOff, Long> implements ICutOffTable {
	public CutOff find(CutOff t) throws DaoException {
		// TODO Auto-generated method stub
		CutOff valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM CheckIn e WHERE e.date = :date";
		CutOff result = (CutOff) session.createQuery(queryStr)
		.setParameter("date", t.getDate()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<CutOff> getAll() throws DaoException {
		
		List<CutOff> tableList = null;
		Session session = getHibernateSession();
		String strQuery = "FROM CutOff";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
