package fing.satode.bl.puntoReferencia;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PuntoReferenciaService extends ServiceBase {

	public ArrayList<PuntoReferencia> listaPuntosReferencia() {
		
		ArrayList<PuntoReferencia> lista= PuntoReferenciaDAO.getInstance().listaPuntosReferencias();
		return lista;
	}
	

	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia= new PuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().nuevoPuntoReferencia(puntoReferencia);
	}

	public void modificarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia PuntoReferencia= new PuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().modificarPuntoReferencia(PuntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia PuntoReferencia= new PuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().eliminarPuntoReferencia(PuntoReferencia);
	}

	


}
