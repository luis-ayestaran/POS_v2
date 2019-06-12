package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IProductTable;
import com.masterdev.student.entities.Product;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class ProductTableDao extends GenericDao<Product, Long> implements IProductTable {
	public Product find(Product t) throws DaoException {
		// TODO Auto-generated method stub
		Product valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "SELECT p FROM Product p WHERE p.product = :product OR p.barCode = :barCode OR p.internalCode = :internalCode";
		Product result = (Product) session.createQuery(queryStr)
		.setParameter("product", t.getProduct())
		.setParameter("barCode", t.getBarCode())
		.setParameter("internalCode", t.getInternalCode()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<Product> getAll() throws DaoException {
		List<Product> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM Product";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
