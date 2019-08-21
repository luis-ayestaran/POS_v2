package com.masterdev.student.services;

import java.util.List;

import com.masterdev.student.dao.CashRegisterTableDao;
import com.masterdev.student.dao.CutOffTableDao;
import com.masterdev.student.entities.CashRegister;
import com.masterdev.student.entities.CutOff;
import com.masterdev.student.exceptions.DaoException;

public class CashRegisterService {
	private CashRegisterTableDao cashRegisterTableDao;
	private CutOffTableDao cutOffTableDao;
	
	public CashRegisterService() {
		this.cashRegisterTableDao = new CashRegisterTableDao();
		this.cutOffTableDao = new CutOffTableDao();
	}
	
	public CashRegister searchCashRegister(CashRegister cashRegister) {
		CashRegister valueReturn = null;
		CashRegister param = null;
		try {
			param = cashRegisterTableDao.find(cashRegister);
			if(param != null)
				valueReturn = param;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return valueReturn;
	}
	
	public void addCashRegister(CashRegister cashRegister) {
		try {
			cashRegisterTableDao.save(cashRegister);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCashRegister(CashRegister cashRegister) {
		try {
			cashRegisterTableDao.update(cashRegister);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public List<CashRegister> showCashRegisters() {
		List<CashRegister> list = null;
		try {
			list = cashRegisterTableDao.getAll();
		} catch(DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public CashRegister defaultCashRegisterExists() {
		List<CashRegister> cashRegisters = showCashRegisters();
		if(cashRegisters != null) {
			return cashRegisters.get(0);
		} else {
			return null;
		}
	}
	
	public CashRegister getNextUnusedCashRegister() {
		CashRegister nextCashRegister = null;
		List<CashRegister> cashRegisters = showCashRegisters();
		if(cashRegisters != null) {
			for(CashRegister cr : cashRegisters) {
				if(!cr.isUsed()) {
					nextCashRegister = cr;
					break;
				}
			}
		}
		return nextCashRegister;
	}
	
	public CutOff searchCutOff(CutOff cutOff) {
		CutOff valueReturn = null;
		CutOff param = null;
		try {
			param = cutOffTableDao.find(cutOff);
			if(param != null)
				valueReturn = param;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return valueReturn;
	}
	
	public void addCutOff(CutOff cutOff) {
		CashRegister cr = cutOff.getCashRegister();
		cr.setRemaining(cr.getRemaining() - cutOff.getAmount());
		try {
			cutOffTableDao.save(cutOff);
			cashRegisterTableDao.update(cr);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public List<CutOff> showCutOffs() {
		List<CutOff> list = null;
		try {
			list = cutOffTableDao.getAll();
		} catch(DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
