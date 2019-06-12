package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.Sale;
import com.masterdev.student.exceptions.DaoException;

public interface ISaleTable extends IGenericDao<Sale, Long> {
	public List<Sale> getAll() throws DaoException;
}
