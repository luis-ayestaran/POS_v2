package com.masterdev.student.dao;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.masterdev.student.entities.User;
import com.masterdev.student.entities.UserGroup;
import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.hbm.HibernateSessionFactory;
import com.masterdev.student.utils.Toolkit;

public class GenericDaoTest {
	
private HibernateSessionFactory sf;
	
	@Before
	public void setUp() throws Exception {
		sf = new HibernateSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
		if(sf.getSessionFactory() != null)
			sf.getSessionFactory().close();
	}

	@Test
	public void test() {
		try {
			Session session = sf.getSessionFactory().openSession();
			UserGroup userGroup = new UserGroup("admin");
			User user = new User("Admin","Admin","Admin","admin",Toolkit.strToMD5("admin"),userGroup);
			session.beginTransaction();
			session.save(userGroup);
			session.save(user);
			session.getTransaction().commit();
			session.close();
		} catch (ToolkitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
