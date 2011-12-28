package fing.satode.ui.registros.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.EventoDTO;
import fing.satode.data.TipoEventoDTO;



@RemoteServiceRelativePath("evento")
public interface IEvento extends RemoteService {

	public ArrayList<EventoDTO> listaEventos();
	public ArrayList<TipoEventoDTO> listaTipoEventos();
}
