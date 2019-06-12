package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.exceptions.DaoException;

public interface IProductBatchTable extends IGenericDao<ProductBatch, Long> {
	public List<ProductBatch> getAll() throws DaoException;
}
