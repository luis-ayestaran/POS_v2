package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ICashRegisterTable;
import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.exceptions.DaoException;

public class CashRegisterTableDao extends GenericDao<CashRegister, Long> implements ICashRegisterTable{
	public CashRegister find(CashRegister t) throws DaoException {
		// TODO Auto-generated method stub
		CashRegister valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM CashRegister e WHERE e.name = :name";
		CashRegister result = (CashRegister) session.createQuery(queryStr)
		.setParameter("name", t.getName()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<CashRegister> getAll() throws DaoException {
		
		List<CashRegister> tableList = null;
		Session session = getHibernateSession();
		String strQuery = "FROM CashRegister";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
