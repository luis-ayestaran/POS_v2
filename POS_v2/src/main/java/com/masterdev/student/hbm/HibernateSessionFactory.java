package com.masterdev.student.hbm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactory {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	public HibernateSessionFactory() {
		/*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
	        throw new ExceptionInInitializerError(ex);
		}
	}
		
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
