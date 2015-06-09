package org.mines.nantes.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.inject.Singleton;


public final class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static ServiceRegistryBuilder serviceRegistryBuilder;

	static {
		Configuration configuration = new Configuration().configure();
		serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private HibernateUtil() {
	}
}
