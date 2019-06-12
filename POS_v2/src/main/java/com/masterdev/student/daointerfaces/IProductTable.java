package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.Product;
import com.masterdev.student.exceptions.DaoException;

public interface IProductTable extends IGenericDao<Product, Long> {
	public List<Product> getAll() throws DaoException;
}
