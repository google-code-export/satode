package fing.satode.pl.registros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import fing.satode.dominio.Evento;
import fing.satode.dominio.TipoEvento;
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
		List list=sess().createQuery("from Evento order by fechaInicio desc").list();
		ArrayList<Evento> res= new ArrayList<Evento>(list);
		return res;
	}

	public ArrayList<TipoEvento> listaTiposEventos() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from TipoEvento order by nombre").list();
		ArrayList<TipoEvento> res= new ArrayList<TipoEvento>(list);
		return res;
	}

	public void nuevoEvento(Evento evento) {
		// TODO Auto-generated method stub
		sess().save(evento);
	}

	public void modificarEvento(Evento evento) {
		// TODO Auto-generated method stub
		sess().update(evento);
	}

	public void eliminarEvento(Evento evento) {
		// TODO Auto-generated method stub
		sess().delete(evento);
	}

//	public ArrayList<Evento> listaEventosPorDepratamentoYTiposDeEvento(Long idDepartamento ,ArrayList<Long> tiposEventos) {
	//		
	//	String tiposEventosQuery="( ";
	//	boolean entra=false;
	//	for(Long id:tiposEventos){
	//		if(entra)tiposEventosQuery+=" , ";
	//		tiposEventosQuery+=id;
	//		entra=true;
	//	}
	//	tiposEventosQuery+=" )";
	//	List list=sess().createQuery("from Evento where ( departamento.id="+idDepartamento+" and tipoEvento.id in "+tiposEventosQuery+" )order by fechaInicio desc").list();
	//	ArrayList<Evento> res= new ArrayList<Evento>(list);
	//	return res;
	//}

	public ArrayList<Evento> listaEventosPorDepratamentoYTiposDeEvento(Date fechaFino,	Date fechaInicio, Long idDepartamento, ArrayList<Long> tiposEventos) {
		String tiposEventosQuery="( ";
		boolean entra=false;
		for(Long id:tiposEventos){
			if(entra)tiposEventosQuery+=" , ";
			tiposEventosQuery+=id;
			entra=true;
		}
		tiposEventosQuery+=" )";
		List list=null;
		if(idDepartamento!=0){
			list=sess().createQuery("from Evento where ( fechaInicio>=:fi and fechaInicio <= :ff  and departamento.id="+idDepartamento+" and tipoEvento.id in "+tiposEventosQuery+" )order by fechaInicio desc").setDate("fi", fechaInicio).setDate("ff", fechaFino).list();
		}else{
			list=sess().createQuery("from Evento where ( fechaInicio>=:fi and fechaInicio <= :ff  and tipoEvento.id in "+tiposEventosQuery+" )order by fechaInicio desc").setDate("fi", fechaInicio).setDate("ff", fechaFino).list();
		}
		ArrayList<Evento> res= new ArrayList<Evento>(list);
		return res;	}
	
}
