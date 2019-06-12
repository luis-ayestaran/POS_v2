package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IProductBatchTable;
import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class ProductBatchTableDao extends GenericDao<ProductBatch, Long> implements IProductBatchTable {
	public ProductBatch find(ProductBatch t) throws DaoException {
		// TODO Auto-generated method stub
		ProductBatch valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM ProductBatch t WHERE t.id = :id OR t.entryDate= :entryDate";
		ProductBatch result = (ProductBatch) session.createQuery(queryStr)
				.setParameter("id", t.getId())
				.setParameter("entryDate", t.getEntryDate()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<ProductBatch> getAll() throws DaoException {
		List<ProductBatch> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM ProductBatch";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
