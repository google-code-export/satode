package fing.satode.ui.necesidades.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.GestionNecesidadDTO;
import fing.satode.data.NecesidadDTO;

public interface INecesidadAsync {

	void eliminarNecesidad(NecesidadDTO dto, AsyncCallback<Void> callback);

	void modificarNecesidad(NecesidadDTO dto, AsyncCallback<Void> callback);

	void nuevoNecesidad(NecesidadDTO dto, AsyncCallback<Void> callback);

	void buscarNecesidades(Long idDesastre, Long idEstado,
			AsyncCallback<ArrayList<NecesidadDTO>> callback);

	void buscarGestionNecesidadPorNecesidad(Long idNecesidad,
			AsyncCallback<GestionNecesidadDTO> callback);

	void nuevoGestionNecesidad(GestionNecesidadDTO dto,
			AsyncCallback<Void> callback);

	void modificarGestionNecesidad(GestionNecesidadDTO dto,
			AsyncCallback<Void> callback);

}
