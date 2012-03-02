package fing.satode.ui.desastres.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EstadoDesastreDTO;
import fing.satode.data.TipoCostoDTO;

public interface IDesastreAsync {

	void listaDesastres(AsyncCallback<ArrayList<DesastreDTO>> callback);

	void nuevoDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

	void eliminarDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

	void modificarDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

	void listaTipoCosto(AsyncCallback<ArrayList<TipoCostoDTO>> callback);

	void nuevoTipoCosto(TipoCostoDTO dto, AsyncCallback<Void> callback);

	void modificarTipoCosto(TipoCostoDTO dto, AsyncCallback<Void> callback);

	void eliminarTipoCosto(TipoCostoDTO dto, AsyncCallback<Void> callback);

	void listaCosto(AsyncCallback<ArrayList<CostoDTO>> callback);

	void nuevoCosto(CostoDTO dto, AsyncCallback<Void> callback);

	void modificarCosto(CostoDTO dto, AsyncCallback<Void> callback);

	void eliminarCosto(CostoDTO dto, AsyncCallback<Void> callback);

	void listaCosto(Long idDesastre, AsyncCallback<ArrayList<CostoDTO>> callback);

	void reporteEstadoDesastre(Long idDesastre,
			AsyncCallback<EstadoDesastreDTO> callback);

}
