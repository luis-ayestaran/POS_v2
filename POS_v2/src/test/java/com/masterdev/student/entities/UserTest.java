package com.masterdev.student.entities;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.masterdev.student.exceptions.ToolkitException;
import com.masterdev.student.hbm.HibernateSessionFactory;
import com.masterdev.student.utils.Toolkit;

public class UserTest {
	
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
			UserGroup userGroup = new UserGroup("user");
			User user = new User("Luis Fer","Libreros","Ayestaran","luisF",Toolkit.strToMD5("luisF"),userGroup);
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
