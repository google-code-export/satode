package fing.satode.ui.usuarios.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;

public interface IUsuarioAsync {

	void login(String usu, String pass, AsyncCallback<Boolean> callback);

	void listaUsuarios(AsyncCallback<ArrayList<UsuarioDTO>> callback);

	void listaPerfiles(AsyncCallback<ArrayList<PerfilDTO>> callback);

	void listaPermisos(AsyncCallback<ArrayList<PermisoDTO>> callback);

	void nuevoPerfil(PerfilDTO perfil, AsyncCallback<Void> callback);

	void modificarPerfil(PerfilDTO perfil, AsyncCallback<Void> callback);

	void eliminarPerfil(PerfilDTO perfil, AsyncCallback<Void> callback);

	void nuevoUsuario(UsuarioDTO usuario, AsyncCallback<Void> callback);

	void modificarUsuario(UsuarioDTO usuario, AsyncCallback<Void> callback);

	void eliminarUsuario(UsuarioDTO usuario, AsyncCallback<Void> callback);

	void buscarUsuario(String usuario, AsyncCallback<UsuarioDTO> callback);

	void limpiarSession(AsyncCallback<Void> callback);

	void getUsuarioLogin(AsyncCallback<UsuarioDTO> callback);


}
