package fing.satode.bl.registros;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.dominio.Evento;
import fing.satode.pl.registros.EventoDAO;
@Transactional
public class EventoService extends ServiceBase {

	public ArrayList<Evento> listaEventos() {
		// TODO Auto-generated method stub
		ArrayList<Evento> lista= EventoDAO.getInstance().listaEventos();
		return lista;
	}

}
