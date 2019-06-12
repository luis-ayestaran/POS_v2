package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.ProductType;
import com.masterdev.student.exceptions.DaoException;

public interface IProductTypeTable extends IGenericDao<ProductType, Long> {
	public List<ProductType> getAll() throws DaoException;
}
