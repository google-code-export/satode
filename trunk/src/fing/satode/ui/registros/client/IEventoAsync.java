package fing.satode.ui.registros.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fing.satode.data.EventoDTO;
import fing.satode.data.TipoEventoDTO;
import fing.satode.dominio.Evento;

public interface IEventoAsync {

	void listaEventos(AsyncCallback<ArrayList<EventoDTO>> callback);

	void listaTipoEventos(AsyncCallback<ArrayList<TipoEventoDTO>> callback);

	void nuevoEvento(EventoDTO dto, AsyncCallback<Void> callback);

	void modificarEvento(EventoDTO dto, AsyncCallback<Void> callback);

	void eliminarEvento(EventoDTO dto, AsyncCallback<Void> callback);

}
