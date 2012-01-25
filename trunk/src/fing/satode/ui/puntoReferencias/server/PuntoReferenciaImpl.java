package fing.satode.ui.puntoReferencias.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.puntoReferencias.client.IPuntoReferencia;


public class PuntoReferenciaImpl extends ServerImpl implements IPuntoReferencia{

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<PuntoReferenciaDTO> listaPuntoReferencia() {
		
		ArrayList<PuntoReferenciaDTO> lista= ServiceFactory.getInstance().getPuntoReferenciaService().listaPuntosReferencia();
		return lista;
	}

	@Override
	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto) {
		ServiceFactory.getInstance().getPuntoReferenciaService().nuevoPuntoReferencia(dto);
		
	}
	
	/*@Override
	public void nuevoHospital(HospitalDTO dto) {
		ServiceFactory.getInstance().getPuntoReferenciaService().nuevoHospital(dto);
		
	}*/

	@Override
	public void modificarPuntoReferencia(PuntoReferenciaDTO dto) {
		ServiceFactory.getInstance().getPuntoReferenciaService().modificarPuntoReferencia(dto);
		
	}

	@Override
	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto) {
		ServiceFactory.getInstance().getPuntoReferenciaService().eliminarPuntoReferencia(dto);
		
	}
	
	@Override
	public HospitalDTO buscarHospital(Long id) {
		return ServiceFactory.getInstance().getPuntoReferenciaService().buscarHospital(id);
	}

	
}
