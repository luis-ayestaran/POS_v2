package com.masterdev.student.dao;

import java.util.List;

import org.hibernate.Session;

import com.masterdev.student.daointerfaces.IUserGroupTable;
import com.masterdev.student.entities.UserGroup;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.hbm.HibernateSessionFactory;

public class UserGroupTableDao extends GenericDao<UserGroup, Long> implements IUserGroupTable {
	public UserGroup find(UserGroup t) throws DaoException {
		// TODO Auto-generated method stub
		UserGroup valueReturn = null;
		Session session = getHibernateSession();
		String queryStr = "FROM UserGroup u WHERE u.group = :group";
		UserGroup result = (UserGroup) session.createQuery(queryStr)
		.setParameter("group", t.getGroup()).uniqueResult();
		session.close();
		if(result == null)
			throw new DaoException(System.getProperty("exception.1002"));
		else 
			valueReturn = result; 
		return valueReturn; 
	}
	
	@Override
	public List<UserGroup> getAll() throws DaoException {
		List<UserGroup> tableList = null;
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		String strQuery = "FROM UserGroup";
		tableList = session.createQuery(strQuery).getResultList();
		session.close();
		if(tableList.isEmpty())
			throw new DaoException("No register was found");
		else
			return tableList;
	}
}
