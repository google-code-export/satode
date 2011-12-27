package fing.satode.ui.usuarios.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;

@RemoteServiceRelativePath("usuario")
public interface IUsuario extends RemoteService {

	public boolean login(String usu,String pass);
	public ArrayList<UsuarioDTO> listaUsuarios();
	public ArrayList<PerfilDTO> listaPerfiles();
	public ArrayList<PermisoDTO> listaPermisos();
	public void nuevoPerfil(PerfilDTO perfil);
	public void modificarPerfil(PerfilDTO perfil);
	public void eliminarPerfil(PerfilDTO perfil);
	public void nuevoUsuario(UsuarioDTO usuario);
	public void modificarUsuario(UsuarioDTO usuario);
	public void eliminarUsuario(UsuarioDTO usuario);
	public UsuarioDTO buscarUsuario(String usuario);
	public void limpiarSession();
}
