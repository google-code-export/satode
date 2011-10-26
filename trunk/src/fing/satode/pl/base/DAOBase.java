package fing.satode.pl.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOBase {

		
	private static SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void setSessionFactory(SessionFactory  session) {
		sessionFactory= session;
	}
	
	public Session sess() {
		return sessionFactory.getCurrentSession();
	}
	
}
