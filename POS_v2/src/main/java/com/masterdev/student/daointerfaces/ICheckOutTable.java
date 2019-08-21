package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.CheckOut;
import com.masterdev.student.exceptions.DaoException;

public interface ICheckOutTable extends IGenericDao<CheckOut, Long> {
	public List<CheckOut> getAll() throws DaoException;
}
