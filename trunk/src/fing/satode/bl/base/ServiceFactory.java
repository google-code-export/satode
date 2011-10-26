package fing.satode.bl.base;

import fing.satode.bl.usuarios.UsuarioService;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	private UsuarioService usuarioService;
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		if(instance==null){
			instance= new ServiceFactory();
		}
		
		return instance;
	}
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	

}
