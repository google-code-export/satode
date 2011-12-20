package fing.satode.ui.usuarios.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.dominio.Usuario;

@RemoteServiceRelativePath("usuario")
public interface IUsuario extends RemoteService {

	public boolean login(String usu,String pass);
	public ArrayList<Usuario> listaUsuarios();
}
