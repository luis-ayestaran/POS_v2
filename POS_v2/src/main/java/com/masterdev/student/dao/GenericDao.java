package com.masterdev.student.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.RollbackException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IGenericDao;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class GenericDao<E, K extends Serializable > implements IGenericDao<E, K> {

	public Class<E> domainClass = getDomainClass();
	
	protected Class getDomainClass() {
		if( domainClass == null ) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
	protected Session getHibernateSession() {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}
	
	@Override
	public E find(K id) throws DaoException {
		// TODO Auto-generated method stub
		Session session = getHibernateSession();
		E returnValue = (E) session.get(domainClass, id);
		if(returnValue == null)
			throw new DaoException(System.getProperty("exception.1002"));
		session.getTransaction().commit();
		session.close();
		return returnValue;
	}


	@Override
	public void save(E entity) throws DaoException {
		// TODO Auto-generated method stub
		try {
			Session session = getHibernateSession();
			session.save(entity);
			try {
				session.getTransaction().commit();
				session.close();
			} catch(IllegalStateException e1) {
				throw e1;
			}catch(RollbackException e2) {
				throw e2;
			}finally{
				session.close();
			}
		}catch(HibernateException e) {
			throw new DaoException("It wasn't possible to save the entity");
		}
	}

	@Override
	public void update(E entity) throws DaoException {
		// TODO Auto-generated method stub
		try {
			Session session = getHibernateSession();
			session.update(entity);
			try {
				session.getTransaction().commit();
				session.close();
			} catch(IllegalStateException e1) {
				throw e1;
			}catch(RollbackException e2) {
				throw e2;
			}finally{
				session.close();
			}
		}catch(HibernateException e) {
			throw new DaoException("It wasn't possible to update the entity");
		}
	}

	@Override
	public void delete(E entity) throws DaoException {
		// TODO Auto-generated method stub
		try {
			Session session = getHibernateSession();
			session.delete(entity);
			session.flush();
			session.close();
		}catch(HibernateException e) {
			throw new DaoException("It wasn't possible to delete the register");
		}
	}

}