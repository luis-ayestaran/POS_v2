package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.UserGroup;
import com.masterdev.student.exceptions.DaoException;

public interface IUserGroupTable extends IGenericDao<UserGroup, Long>{
	public List<UserGroup> getAll() throws DaoException;
}
