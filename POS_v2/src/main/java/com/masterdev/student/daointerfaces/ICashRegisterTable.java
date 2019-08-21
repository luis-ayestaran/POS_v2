package com.masterdev.student.daointerfaces;

import java.util.List;

import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.exceptions.DaoException;

public interface ICashRegisterTable extends IGenericDao<CashRegister, Long> {
	public List<CashRegister> getAll() throws DaoException;
}
