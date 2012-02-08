package fing.satode.bl.puntoReferencia;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.BomberosDTO;
import fing.satode.data.CamineraDTO;
import fing.satode.data.CuartelDTO;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PROtrosDTO;
import fing.satode.data.PoliciaDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.RefugioDTO;
import fing.satode.constantes.TipoPuntoReferencia;
import fing.satode.dominio.Bomberos;
import fing.satode.dominio.Caminera;
import fing.satode.dominio.Cuartel;
import fing.satode.dominio.Hospital;
import fing.satode.dominio.PROtros;
import fing.satode.dominio.Policia;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.dominio.Refugio;
import fing.satode.dominio.Util;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PuntoReferenciaService extends ServiceBase {

	public ArrayList<PuntoReferenciaDTO> listaPuntosReferencia() {
		
		ArrayList<PuntoReferenciaDTO> listaDTOS= new ArrayList<PuntoReferenciaDTO>();
		ArrayList<PuntoReferencia> listaDes= PuntoReferenciaDAO.getInstance().listaPuntosReferencias();
		
		for(PuntoReferencia d: listaDes){
			listaDTOS.add(Util.crearPuntoReferenciaDTO(d));	
		}
		
		return listaDTOS;
	}
	

	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = Util.crearPuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().nuevoPuntoReferencia(puntoReferencia);
	}
	
	
	public void modificarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = Util.crearPuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().modificarPuntoReferencia(puntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = Util.crearPuntoReferencia(dto);
		PuntoReferenciaDAO.getInstance().eliminarPuntoReferencia(puntoReferencia);
	}




	public ArrayList<PuntoReferenciaDTO> listPuntoEntrada() {
		// TODO Auto-generated method stub
		ArrayList<PuntoReferencia> lista=PuntoReferenciaDAO.getInstance().listPuntoEntrada();
		ArrayList<PuntoReferenciaDTO> res=new ArrayList<PuntoReferenciaDTO>();
		for(PuntoReferencia p:lista){
			res.add(p.getDTO());
		}
		return res;
	}


	public ArrayList<PuntoReferenciaDTO> listPuntoEntrega() {
		ArrayList<PuntoReferencia> lista=PuntoReferenciaDAO.getInstance().listPuntoEntrega();
		ArrayList<PuntoReferenciaDTO> res=new ArrayList<PuntoReferenciaDTO>();
		for(PuntoReferencia p:lista){
			res.add(p.getDTO());
		}
		return res;
	}

	


}
