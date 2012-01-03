package fing.satode.ui.desastres.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.DesastreDTO;

public interface IDesastreAsync {

	void listaDesastres(AsyncCallback<ArrayList<DesastreDTO>> callback);

	void nuevoDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

	void eliminarDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

	void modificarDesastre(DesastreDTO dto, AsyncCallback<Void> callback);

}
