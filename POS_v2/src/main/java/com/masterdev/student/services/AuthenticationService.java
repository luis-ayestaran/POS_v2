package com.masterdev.student.services;

import java.util.List;

import com.masterdev.student.dao.UserDao;
import com.masterdev.student.dao.UserGroupDao;
import com.masterdev.student.dao.UserGroupTableDao;
import com.masterdev.student.dao.UserTableDao;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.entities.User;
import com.masterdev.student.entities.UserGroup;

public class AuthenticationService {
	//private UserDao userDao;
	//private UserGroupDao userGroupDao;
	//----------------------------------------------
	private UserTableDao userTableDao;
	private UserGroupTableDao userGroupTableDao;
	//----------------------------------------------
	
	public AuthenticationService() {
		//this.userDao = new UserDao();
		//this.userGroupDao = new UserGroupDao();
		this.userTableDao = new UserTableDao();
		this.userGroupTableDao = new UserGroupTableDao();
	}
	
	public User searchUser(User user) {
		User valueReturn = null;
		User userParam = null;
		try {
			//userParam = userDao.find(user);
			userParam = userTableDao.find(user);
			if(userParam != null)
				valueReturn = userParam;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valueReturn;
	}
	
	public void registrateUser(User user, UserGroup userGroup) {
		UserGroup ugParam = searchUserGroup(userGroup);
		try {
			if(ugParam != null) {
				user.setUserGroup(ugParam);
				userTableDao.save(user);
			} else {
				userGroupTableDao.save(userGroup);
				userTableDao.save(user);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Boolean adminExists() {
		Boolean exists = false;
		List<User> list = showUsers();
		if(list != null && !list.isEmpty()) {
			for(User u : list) {
				if(u.getUserGroup().getGroup().equals("admin"))
				{
					exists = true;
					break;
				}
			}
		}
		return exists;
	}
	
	public List<User> showUsers() {
		List<User> list = null;
		try {
			list = userTableDao.getAll();
		} catch(DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public UserGroup searchUserGroup(UserGroup userGroup) {
		UserGroup ugParam = null;
		try {
			ugParam = userGroupTableDao.find(userGroup);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return ugParam;
	}
}
