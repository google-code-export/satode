package fing.satode.ui.registros.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.dominio.Evento;

public interface IEventoAsync {

	void listaEventos(AsyncCallback<ArrayList<Evento>> callback);

}
