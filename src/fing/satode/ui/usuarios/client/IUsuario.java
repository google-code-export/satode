package fing.satode.ui.usuarios.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usuario")
public interface IUsuario extends RemoteService {

	public boolean login(String usu,String pass);
}
