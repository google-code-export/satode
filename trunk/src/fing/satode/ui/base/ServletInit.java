package fing.satode.ui.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.bl.usuarios.UsuarioService;
import fing.satode.pl.base.DAOBase;

public class ServletInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void init() throws ServletException {
		
		BeanFactory factory = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SessionFactory session= (SessionFactory) factory.getBean("sessionFactory");
		
		//Session Hibernate
		DAOBase.setSessionFactory(session);
		
		//Servicios
		ServiceFactory.getInstance().setBeanFactory(factory);
		
		
	}
}
