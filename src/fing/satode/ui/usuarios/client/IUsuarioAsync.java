package fing.satode.ui.usuarios.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IUsuarioAsync {

	void login(String usu, String pass, AsyncCallback<Boolean> callback);

}
