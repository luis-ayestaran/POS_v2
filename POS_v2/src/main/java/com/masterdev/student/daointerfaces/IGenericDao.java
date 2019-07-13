package com.masterdev.student.daointerfaces;

import java.io.Serializable;

import com.masterdev.student.exceptions.DaoException;

public interface IGenericDao <E, K extends Serializable>{
	E find(K id) throws DaoException;
	
	void save(E entity) throws DaoException;
	
	void update(E entity) throws DaoException;
	
	void delete(E entity) throws DaoException;
}


