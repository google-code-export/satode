package fing.satode.pl.registros;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Evento;
import fing.satode.dominio.TipoEvento;
import fing.satode.dominio.Usuario;
import fing.satode.pl.base.DAOBase;

public class EventoDAO extends DAOBase {

private static EventoDAO instance;
	
	private EventoDAO(){}
	
	public static EventoDAO getInstance(){
		if(instance==null){
			instance = new EventoDAO();
		}
		
		return instance;
	}

	public ArrayList<Evento> listaEventos() {
		List list=sess().createQuery("from Evento order by fechaInicio").list();
		ArrayList<Evento> res= new ArrayList<Evento>(list);
		return res;
	}

	public ArrayList<TipoEvento> listaTiposEventos() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from TipoEvento order by nombre").list();
		ArrayList<TipoEvento> res= new ArrayList<TipoEvento>(list);
		return res;
	}
	
}
