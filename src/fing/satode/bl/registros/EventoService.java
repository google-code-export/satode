package fing.satode.bl.registros;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.EventoDTO;
import fing.satode.data.TipoEventoDTO;
import fing.satode.dominio.Evento;
import fing.satode.dominio.TipoEvento;
import fing.satode.pl.registros.EventoDAO;
@Transactional
public class EventoService extends ServiceBase {

	public ArrayList<EventoDTO> listaEventos() {
		// TODO Auto-generated method stub
		ArrayList<Evento> lista= EventoDAO.getInstance().listaEventos();
		ArrayList<EventoDTO> res= new ArrayList<EventoDTO>();
		for(Evento e:lista){
			res.add(e.getDTO());
		}
		return res;
	}

	public ArrayList<TipoEventoDTO> listaTiposEventos() {
		// TODO Auto-generated method stub
		ArrayList<TipoEvento> lista= EventoDAO.getInstance().listaTiposEventos();
		ArrayList<TipoEventoDTO> res= new ArrayList<TipoEventoDTO>();
		for(TipoEvento e:lista){
			res.add(e.getDTO());
		}
		return res;
	}

}
