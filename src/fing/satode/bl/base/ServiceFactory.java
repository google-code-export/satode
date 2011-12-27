package fing.satode.bl.base;

import org.springframework.beans.factory.BeanFactory;

import fing.satode.bl.registros.EventoService;
import fing.satode.bl.usuarios.UsuarioService;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	private BeanFactory beanFactory;
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		if(instance==null){
			instance= new ServiceFactory();
		}
		
		return instance;
	}
	
	public void setBeanFactory(BeanFactory beanFact){
		this.beanFactory = beanFact;
	}
	
	
	public UsuarioService getUsuarioService() {
		return   (UsuarioService) beanFactory.getBean("usuarioService");
	}

	public EventoService getEventoService() {
		return   (EventoService) beanFactory.getBean("eventoService");
	}
	

}
