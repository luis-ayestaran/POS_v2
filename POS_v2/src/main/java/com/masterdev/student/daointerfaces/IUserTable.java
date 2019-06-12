package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.User;
import com.masterdev.student.exceptions.DaoException;

public interface IUserTable extends IGenericDao<User, Long> {
	public List<User> getAll() throws DaoException;
}
