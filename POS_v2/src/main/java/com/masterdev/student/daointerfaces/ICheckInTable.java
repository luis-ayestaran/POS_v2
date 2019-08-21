package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.CheckIn;
import com.masterdev.student.exceptions.DaoException;

public interface ICheckInTable extends IGenericDao<CheckIn, Long> {
	public List<CheckIn> getAll() throws DaoException;
}
