package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ISaleTable;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class SaleTableDao extends GenericDao<Sale, Long> implements ISaleTable {
	public Sale find(Sale t) throws DaoException {
		Sale valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM Sale s WHERE s.id = :id";
		Sale result = (Sale) session.createQuery(queryStr).setParameter("id", t.getFolio()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<Sale> getAll() throws DaoException {
		List<Sale> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM Sale";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}

