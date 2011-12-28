package fing.satode.bl.puntoReferencias;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PuntoReferenciaService extends ServiceBase {

	public ArrayList<PuntoReferencia> listaPuntoReferencia() {
		
		ArrayList<PuntoReferencia> lista= PuntoReferenciaDAO.getInstance().listaPuntosReferencias();
		return lista;
	}

}
