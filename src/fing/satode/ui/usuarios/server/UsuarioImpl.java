package fing.satode.ui.usuarios.server;


import java.util.ArrayList;
import java.util.List;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.usuarios.client.IUsuario;

public class UsuarioImpl extends ServerImpl implements IUsuario {

	@Override
	public boolean login(String usu, String pass) {
		// TODO Auto-generated method stub
		
		Boolean res= ServiceFactory.getInstance().getUsuarioService().login(usu, pass);
		
		if(res){
			UsuarioDTO usuarioSession=ServiceFactory.getInstance().getUsuarioService().buscarUsuario(usu);
			perThreadRequest.get().getSession().setAttribute("usuario",usuarioSession);
		}
		
		return res;
	}

	public ArrayList<UsuarioDTO> listaUsuarios() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getUsuarioService().listaUsuarios();
	}

	@Override
	public ArrayList<PerfilDTO> listaPerfiles() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getUsuarioService().listaPerfiles();
	}


	public ArrayList<PermisoDTO> listaPermisos() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getUsuarioService().listaPermisos();
	}

	@Override
	public void nuevoPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().nuevoPerfil(perfil);
	}
	

	@Override
	public void modificarPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().modificarPerfil(perfil);
	}

	@Override
	public void eliminarPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().eliminarPerfil(perfil);
	}

	@Override
	public void nuevoUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().nuevoUsuario(usuario);
	}

	@Override
	public void modificarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().modificarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getUsuarioService().eliminarUsuario(usuario);
	}

	@Override
	public UsuarioDTO buscarUsuario(String usuario) {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getUsuarioService().buscarUsuario(usuario);
	}

	@Override
	public void limpiarSession() {
		// TODO Auto-generated method stub
		perThreadRequest.get().getSession().setAttribute("usuario",null);
	}
	
	public UsuarioDTO getUsuarioLogin(){
		return (UsuarioDTO)perThreadRequest.get().getSession().getAttribute("usuario");
	}
	
}
