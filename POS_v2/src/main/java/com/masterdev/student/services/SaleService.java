package com.masterdev.student.services;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.SaleDetail;
import com.masterdev.student.entities.Sale;
import com.masterdev.student.dao.SaleDetailTableDao;
import java.text.ParseException;
import com.masterdev.student.dao.ProductTableDao;
import com.masterdev.student.dao.SaleTableDao;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.middle.DatePickerMethods;

public class SaleService {
	private ProductTableDao productTableDao;
	private SaleDetailTableDao detailTableDao;
	private SaleTableDao saleTableDao;
	private List<SaleDetail> saleList;
	private Float total;
	private Sale sale;
	
	public SaleService() {
		this.productTableDao = new ProductTableDao();
		this.detailTableDao = new SaleDetailTableDao();
		this.saleTableDao = new SaleTableDao();
	}
	
	public Sale openSale() {
		DatePickerMethods dpm = new DatePickerMethods();
		Date date = dpm.getCurrentDateTime();
		sale = new Sale(date);
		try {
			saleTableDao.save(sale);
		} catch (DaoException e) {
			
		}
		saleList = new ArrayList<SaleDetail>();
		return sale;
	}
	
	public void saveSale(Sale sale) {
		DatePickerMethods dpm = new DatePickerMethods();
		Date date = dpm.getCurrentDateTime();
		sale.setDate(date);
		sale.setTotal(getTotal());
		sale.setDetail(new ArrayList<SaleDetail>(saleList));
		try {
			saleTableDao.update(sale);
			for(SaleDetail sd : saleList)
			{
				detailTableDao.save(sd);
			}
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelSale(Sale sale) {
		try {
			while(saleList.size() > 0)
			{
				SaleDetail sd = saleList.get(0);
				returnProduct(sd);
				saleList.remove(0);
			}
			saleTableDao.delete(sale);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void addDetail(SaleDetail saleDetail) {
		saleList.add(saleDetail);
		discardProduct(saleDetail);
	}
	
	public void discardProduct(SaleDetail saleDetail) {
		Product p = saleDetail.getProduct();
		p.setQuantity(p.getQuantity() - saleDetail.getQuantity());
		p.setUnitsSold(p.getUnitsSold() + saleDetail.getQuantity());
		try {
			productTableDao.update(p);
		} catch(DaoException e) {
			e.printStackTrace();
		}
		printVenta();
	}
	
	public void removeDetail(SaleDetail saleDetail) {
		saleList.remove(saleDetail);
		returnProduct(saleDetail);
		printVenta();
	}
	
	public void returnProduct(SaleDetail saleDetail) {
		Product p = saleDetail.getProduct();
		p.setQuantity(p.getQuantity() + saleDetail.getQuantity());
		p.setUnitsSold(p.getUnitsSold() - saleDetail.getQuantity());
		try {
			productTableDao.update(p);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public void printVenta() {
		System.out.println("VENTA");
		for(int i=0;i<saleList.size();i++)
			System.out.println(saleList.get(i));
	}
	
	public Float getTotal() {
		Float total=0.0f;
		for(SaleDetail sd : saleList) {
			total = total + (sd.getSubTotal() - (sd.getProduct().getDiscount()/100) * sd.getSubTotal()) ;
		}
		return total;
	}
	
	public List<SaleDetail> getSaleList() {
		return saleList;
	}
	
	public List<Sale> showSales() {
		List<Sale> list = null;
		try {
			list = saleTableDao.getAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
