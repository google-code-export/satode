package fing.satode.ui.usuarios.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.dominio.Usuario;

public interface IUsuarioAsync {

	void login(String usu, String pass, AsyncCallback<Boolean> callback);

	void listaUsuarios(AsyncCallback<ArrayList<Usuario>> callback);


}
