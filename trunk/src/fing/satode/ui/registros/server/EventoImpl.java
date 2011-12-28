package fing.satode.ui.registros.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.EventoDTO;
import fing.satode.data.TipoEventoDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.registros.client.IEvento;

public class EventoImpl extends ServerImpl implements IEvento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<EventoDTO> listaEventos() {
		// TODO Auto-generated method stub
		ArrayList<EventoDTO> lista= ServiceFactory.getInstance().getEventoService().listaEventos();
		return lista;
	}

	@Override
	public ArrayList<TipoEventoDTO> listaTipoEventos() {
		// TODO Auto-generated method stub
		ArrayList<TipoEventoDTO> lista= ServiceFactory.getInstance().getEventoService().listaTiposEventos();
		return lista;
	}

	
}
