package fing.satode.ui.puntoReferencias.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.dominio.PuntoReferencia;

public interface IPuntoReferenciaAsync {

	void listaPuntoReferencia(AsyncCallback<ArrayList<PuntoReferencia>> callback);

}
