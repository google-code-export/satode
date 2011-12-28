package fing.satode.ui.general.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.DepartamentoDTO;
import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;

@RemoteServiceRelativePath("basicos")
public interface IBasicos extends RemoteService {

	public ArrayList<DepartamentoDTO> listaDepartamentos();
	}
