package com.masterdev.student.services;

import java.util.List;

import org.hibernate.NonUniqueResultException;

import com.masterdev.student.dao.ProductDao;
import com.masterdev.student.dao.ProductTableDao;
import com.masterdev.student.dao.ProductBatchDao;
import com.masterdev.student.dao.ProductBatchTableDao;
import com.masterdev.student.dao.ProductTypeDao;
import com.masterdev.student.dao.ProductTypeTableDao;
import com.masterdev.student.exceptions.DaoException;
import com.masterdev.student.entities.Product;
import com.masterdev.student.entities.ProductBatch;
import com.masterdev.student.entities.ProductType;

public class WarehouseService {
	//private ProductDao productDao;
	//private ProductBatchDao productBatchDao;
	//private ProductTypeDao productTypeDao;
	private ProductTableDao productTableDao;
	private ProductBatchTableDao productBatchTableDao;
	private ProductTypeTableDao productTypeTableDao;
	
	public WarehouseService() {
		//this.productDao = new ProductDao();
		//this.productTypeDao = new ProductTypeDao();
		//this.productBatchDao = new ProductBatchDao();
		this.productTableDao = new ProductTableDao();
		this.productTypeTableDao = new ProductTypeTableDao();
		this.productBatchTableDao = new ProductBatchTableDao();
	}
	
	
	//------------------------------ PRODUCT ----------------------------//
	public Product searchProduct(Product product) {
		Product productParam = null;
		try {
			//productParam = productDao.find(product);
			System.out.println("Vamos a buscar un producto");
			productParam = productTableDao.find(product);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
		} catch (NonUniqueResultException e) {
			
		}
		return productParam;
	}
	
	public Integer addProduct(Product product) {
		Integer exit = 0;
		Boolean saved = false;
		ProductType pt = null;
		try {
			//pt = productTypeDao.find(product.getProductType());
			pt = productTypeTableDao.find(product.getProductType());
		} catch(DaoException e) {
			e.printStackTrace();
		}
		try {
			if(pt != null)
			{
				product.setProductType(pt);
				//saved = productDao.save(product);
				/*saved = */productTableDao.save(product);
				/*if(!saved)
					exit = 1;*/
			}
			else
				exit = 2;
		} catch(DaoException e) {
			e.printStackTrace();
		}
		return exit;
	}
	
	public void updateProduct(Product product) {
		try {
			//productDao.update(product);
			productTableDao.update(product);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> showProducts() {
		List<Product> list = null;
		try {
			//list = productDao.getAll();
			list = productTableDao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Integer deleteProduct(Product product) {
		Integer exit = 0;
		try {
			//productDao.delete(product);
			productTableDao.delete(product);
		} catch(DaoException e) {
			exit = 1;
			e.printStackTrace();
		}
		return exit;
	}
	
	//--------------------- PRODUCT TYPE ---------------------------//
	public ProductType searchProductType(ProductType type) {
		ProductType typeParam = null;
		try {
			//typeParam = productTypeDao.find(type);
			typeParam = productTypeTableDao.find(type);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeParam;
	}
	
	public Integer addProductType(ProductType productType) {
		Integer exit = 0;
		boolean saved = false;
		ProductType pt = searchProductType(productType);
		try {
			if(pt == null) {
				//saved = productTypeDao.save(productType);
				/*saved = */productTypeTableDao.save(productType);
				/*if(!saved)
					exit = 1;*/
			}
			else
				exit = 2;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return exit;
	}
	
	public void updateProductType(ProductType productType) {
		try {
			//productTypeDao.update(productType);
			productTypeTableDao.update(productType);	
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductType> showProductTypes(){
		List<ProductType> list = null;
		try {
			//list = productTypeDao.getAll();
			list = productTypeTableDao.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//----------------------------- PRODUCT BATCH -------------------------------//
	public ProductBatch searchProductBatch(ProductBatch batch) {
		ProductBatch batchParam = null;
		try {
			//batchParam = productBatchDao.find(batch);
			batchParam = productBatchTableDao.find(batch);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return batchParam;
	}
	
	public Integer addProductBatch(ProductBatch productBatch) {
		Integer exit = 0;
		boolean saved = false;
		//ProductBatch pb = searchProductBatch(productBatch);
		try {
			//if(pb == null)
			//{
				//saved = productBatchDao.save(productBatch);
				/*saved = */productBatchTableDao.save(productBatch);
				/*if(!saved)
					exit = 1;*/
			//}
			//else
			//	exit = 2;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return exit;
	}
	
	public void updateProductBatch(ProductBatch productBatch) {
		try {
			//productBatchDao.update(productBatch);
			productBatchTableDao.update(productBatch);
		} catch(DaoException e) {
			e.printStackTrace();
		}
	}
	
	public Integer deleteProductBatch(ProductBatch productBatch) {
		Integer exit = 0;
		try {
			//productBatchDao.delete(productBatch);
			productBatchTableDao.delete(productBatch);
		} catch(DaoException e) {
			exit = 1;
			e.printStackTrace();
		}
		return exit;
	}
}
