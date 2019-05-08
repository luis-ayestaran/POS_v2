package com.masterdev.student.dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class ProductBatchDao extends HibernateSessionFactory implements IDao<ProductBatch> {
	public ProductBatch find(ProductBatch t) throws DaoException {
		// TODO Auto-generated method stub
		ProductBatch batchReturn = null;
		Session session = getSessionFactory().openSession();
		String queryStr = "FROM ProductBatch t WHERE t.id = :id OR t.entryDate= :entryDate";
		ProductBatch batch = (ProductBatch) session.createQuery(queryStr)
		.setParameter("id", t.getId())
		.setParameter("type", t.getEntryDate());
		if(batch == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			batchReturn = batch; 
		session.close();
		return batchReturn; 
	} 

	public List<ProductBatch> getAll() throws DaoException {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().openSession();
		String queryStr = "FROM ProductType";
		Query<ProductBatch> query = session.createQuery(queryStr, ProductBatch.class);
		List<ProductBatch> list = query.list();
		session.close();
		if(list == null)
			return null;
		else
			return list;
		//return null;
	}

	public boolean save(ProductBatch t) throws DaoException {
		// TODO Auto-generated method stub
		boolean result = false;
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Long id = (Long) session.save(t);
		t.setId(id);
		try {
			session.getTransaction().commit();
			session.close();
			result = true;
		} catch(IllegalStateException e1) {
			e1.getStackTrace();
			throw e1;
		}catch(RollbackException e2) {
			e2.getStackTrace();
			throw e2;
		}finally{
			session.close();
		}
		return result;
	}

	public void update(ProductBatch t) throws DaoException {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.update(t);
		try {
			session.getTransaction().commit();
			session.close();
		}catch(IllegalStateException e1) {
			e1.getStackTrace();
			throw e1;
		}catch(RollbackException e2) {
			e2.getStackTrace();
			throw e2;
		}finally{
			session.close();
		}
	}

	public void delete(ProductBatch t) throws DaoException {
		// TODO Auto-generated method stub
		Session session = getSessionFactory().openSession();
		session.delete(t);
		session.flush();
		session.close();
	}
}
