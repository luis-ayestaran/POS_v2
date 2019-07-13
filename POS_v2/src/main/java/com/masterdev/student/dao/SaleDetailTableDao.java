package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.ISaleDetailTable;
import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class SaleDetailTableDao extends GenericDao<SaleDetail, Long> implements ISaleDetailTable {
	public SaleDetail find(SaleDetail t) throws DaoException {
		SaleDetail valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM Detail d WHERE d.id = :id AND d.id_sale = :id_sale AND d.id_product = :id_product";
		SaleDetail result = (SaleDetail) session.createQuery(queryStr)
		.setParameter("id", t.getId())
		.setParameter("id_sale", t.getSale().getFolio())
		.setParameter("id_product", t.getProduct().getId());
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<SaleDetail> getAll() throws DaoException {
		List<SaleDetail> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM SaleDetail";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
