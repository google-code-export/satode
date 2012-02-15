package fing.satode.bl.base;

import org.springframework.beans.factory.BeanFactory;

import fing.satode.bl.basicos.BasicosService;
import fing.satode.bl.deposito.DepositoService;
import fing.satode.bl.desastres.DesastresService;
import fing.satode.bl.necesidades.NecesidadService;
import fing.satode.bl.propiedadesSiniestradas.PropiedadesSinietradasService;
import fing.satode.bl.puntoReferencia.PuntoReferenciaService;
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
	
	public PuntoReferenciaService getPuntoReferenciaService() {
		return   (PuntoReferenciaService) beanFactory.getBean("puntoReferenciaService");
	}
	
	public BasicosService getBasicosService() {
		return   (BasicosService) beanFactory.getBean("basicosService");
	}
	
	public DesastresService getDesastresService() {
		return   (DesastresService) beanFactory.getBean("desastresService");
	}
	
	public DepositoService getDepositoService() {
		return   (DepositoService) beanFactory.getBean("depositoService");
	}
	
	public NecesidadService getNecesidadService() {
		return   (NecesidadService) beanFactory.getBean("necesidadService");
	}
	
	public PropiedadesSinietradasService getPropiedadesSiniestradasService() {
		return   (PropiedadesSinietradasService) beanFactory.getBean("propiedadesSinietradasService");
	}
	
}
