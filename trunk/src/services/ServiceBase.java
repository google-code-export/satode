package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ServiceBase {

	protected SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory value) {
		sessionFactory = value;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	// Shortcut for sessionFactory.getCurrentSession()
	public Session sess() {
		return sessionFactory.getCurrentSession();
	}
	
}
