package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.exceptions.DaoException;

public interface ISaleDetailTable extends IGenericDao<SaleDetail, Long> {
	public List<SaleDetail> getAll() throws DaoException;
}
