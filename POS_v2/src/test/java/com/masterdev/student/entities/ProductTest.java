package com.masterdev.student.entities;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.masterdev.student.hbm.HibernateSessionFactory;

public class ProductTest {

	private HibernateSessionFactory hsf;
	
	@Before
	public void setUp() throws Exception {
		hsf = new HibernateSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
		if(hsf.getSessionFactory() != null)
			hsf.getSessionFactory().close();
	}

	@Test
	public void test() {
		Session session = hsf.getSessionFactory().openSession();
		session.beginTransaction();
		ProductType productType = new ProductType("lacteo");
		session.save(productType);
		Product product1 = new Product("Leche",
				"La vaquita",150.0f,12.5f,175f, 15f, 1001f,"Caja", 
				"Pieza", 12f, "Caja", "Pieza", 12.0f, 15.0f, 50f, 2000f,
				12345l, productType, true);
		ProductBatch productBatch1 = new ProductBatch(product1, 500.0f, Date.valueOf("2019-01-01"), Date.valueOf("2019-12-31"));
		ProductBatch productBatch2 = new ProductBatch(product1, 500.0f, Date.valueOf("2019-01-02"), Date.valueOf("2019-12-30"));
		product1.setQuantity(productBatch1.getQuantity() + productBatch2.getQuantity());
		session.save(product1);
		session.save(productBatch1);
		session.save(productBatch2);
		session.getTransaction().commit();
		session.close();
		
		session = hsf.getSessionFactory().openSession();
		session.beginTransaction();
		List<Product> list = session.createQuery("FROM Product").list();
		for(Product p : (List<Product>) list) {
			System.out.println(p);
		}
		session.getTransaction().commit();
		session.close();
	}

}
