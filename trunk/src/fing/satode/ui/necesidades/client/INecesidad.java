package fing.satode.ui.necesidades.client;



import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import fing.satode.data.GestionNecesidadDTO;
import fing.satode.data.NecesidadDTO;


@RemoteServiceRelativePath("Necesidad")
public interface INecesidad extends RemoteService {

	public void nuevoNecesidad(NecesidadDTO dto);
	public void modificarNecesidad(NecesidadDTO dto);
	public void eliminarNecesidad(NecesidadDTO dto);
	public ArrayList<NecesidadDTO> buscarNecesidades(Long idDesastre,Long idEstado);
	
	public GestionNecesidadDTO buscarGestionNecesidadPorNecesidad(Long idNecesidad);
	public void nuevoGestionNecesidad(GestionNecesidadDTO dto);
	public void modificarGestionNecesidad(GestionNecesidadDTO dto);
	
}
