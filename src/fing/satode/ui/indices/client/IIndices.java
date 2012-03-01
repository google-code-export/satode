package fing.satode.ui.indices.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.DepartamentoDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;

@RemoteServiceRelativePath("indices")
public interface IIndices extends RemoteService {

	public ArrayList<CalculoIndiceDTO> buscarCalculoIndice(int tipo);
	public void calcularIDL(CalculoIndiceDTO dto);
	public void exportarGraficosIDL();

}
