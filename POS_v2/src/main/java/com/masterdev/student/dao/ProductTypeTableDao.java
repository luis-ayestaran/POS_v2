package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IProductTypeTable;
import com.masterdev.student.entities.ProductType;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class ProductTypeTableDao extends GenericDao<ProductType, Long> implements IProductTypeTable {
	public ProductType find(ProductType t) throws DaoException {
		// TODO Auto-generated method stub
		ProductType valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM ProductType t WHERE t.id = :id OR t.type= :type";
		ProductType result = (ProductType) session.createQuery(queryStr)
		.setParameter("id", t.getId())
		.setParameter("type", t.getType()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<ProductType> getAll() throws DaoException {
		List<ProductType> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM ProductType";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
