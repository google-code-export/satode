package fing.satode.ui.usuarios.server;


import java.util.ArrayList;
import java.util.List;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.dominio.Usuario;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.usuarios.client.IUsuario;

public class UsuarioImpl extends ServerImpl implements IUsuario {

	@Override
	public boolean login(String usu, String pass) {
		// TODO Auto-generated method stub
		
		return ServiceFactory.getInstance().getUsuarioService().login(usu, pass);
	}

	public ArrayList<Usuario> listaUsuarios() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getUsuarioService().listaUsuarios();
	}
	

}
