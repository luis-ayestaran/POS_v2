package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.CutOff;
import com.masterdev.student.exceptions.DaoException;

public interface ICutOffTable extends IGenericDao<CutOff, Long> {
	public List<CutOff> getAll() throws DaoException;
}

