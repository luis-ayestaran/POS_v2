package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IUserTable;
import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class UserTableDao extends GenericDao<User, Long> implements IUserTable {
	public User find(User t) throws DaoException {
		// TODO Auto-generated method stub
		User valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM User u WHERE u.username = :username AND u.password = :password";
		User result = (User) session.createQuery(queryStr)
		.setParameter("username", t.getUsername())
		.setParameter("password", t.getPassword()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result;
		return valueReturn;
	}
	
	@Override
	public List<User> getAll() throws DaoException {
		
		List<User> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM User";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		return tableList;
	}
}
