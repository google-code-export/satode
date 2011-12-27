package fing.satode.ui.registros.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.dominio.Evento;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.registros.client.IEvento;

public class EventoImpl extends ServerImpl implements IEvento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Evento> listaEventos() {
		// TODO Auto-generated method stub
		ArrayList<Evento> lista= ServiceFactory.getInstance().getEventoService().listaEventos();
		return lista;
	}

	
}
