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
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PuntoReferenciaService extends ServiceBase {

	public ArrayList<PuntoReferenciaDTO> listaPuntosReferencia() {
		
		ArrayList<PuntoReferenciaDTO> listaDTOS= new ArrayList<PuntoReferenciaDTO>();
		ArrayList<PuntoReferencia> listaDes= PuntoReferenciaDAO.getInstance().listaPuntosReferencias();
		for(PuntoReferencia d: listaDes){
			switch (d.getTipo()) 
			{
			case TipoPuntoReferencia.HOSPITAL:
				listaDTOS.add(((Hospital)d).getDTO());
			break;
			case TipoPuntoReferencia.REFUGIO:
				listaDTOS.add(((Refugio)d).getDTO());
			break;
			case TipoPuntoReferencia.BOMBEROS:
				listaDTOS.add(((Bomberos)d).getDTO());
			break;
			case TipoPuntoReferencia.CAMINERA:
				listaDTOS.add(((Caminera)d).getDTO());
			break;
			case TipoPuntoReferencia.CUARTEL:
				listaDTOS.add(((Cuartel)d).getDTO());
			break;
			case TipoPuntoReferencia.OTROS:
				listaDTOS.add(((PROtros)d).getDTO());
			break;
			case TipoPuntoReferencia.POLICIA:
				listaDTOS.add(((Policia)d).getDTO());
			break;
			
			}
			
			
		}
		return listaDTOS;
	}
	

	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		case TipoPuntoReferencia.REFUGIO:
			puntoReferencia = new Refugio((RefugioDTO)dto);
		break;
		case TipoPuntoReferencia.BOMBEROS:
			puntoReferencia = new Bomberos((BomberosDTO)dto);
		break;
		case TipoPuntoReferencia.CAMINERA:
			puntoReferencia = new Caminera((CamineraDTO)dto);
		break;
		case TipoPuntoReferencia.CUARTEL:
			puntoReferencia = new Cuartel((CuartelDTO)dto);
		break;
		case TipoPuntoReferencia.OTROS:
			puntoReferencia = new PROtros((PROtrosDTO)dto);
		break;
		case TipoPuntoReferencia.POLICIA:
			puntoReferencia = new Policia((PoliciaDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().nuevoPuntoReferencia(puntoReferencia);
	}
	
	
	public void modificarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		case TipoPuntoReferencia.REFUGIO:
			puntoReferencia = new Refugio((RefugioDTO)dto);
		break;
		case TipoPuntoReferencia.BOMBEROS:
			puntoReferencia = new Bomberos((BomberosDTO)dto);
		break;
		case TipoPuntoReferencia.CAMINERA:
			puntoReferencia = new Caminera((CamineraDTO)dto);
		break;
		case TipoPuntoReferencia.CUARTEL:
			puntoReferencia = new Cuartel((CuartelDTO)dto);
		break;
		case TipoPuntoReferencia.OTROS:
			puntoReferencia = new PROtros((PROtrosDTO)dto);
		break;
		case TipoPuntoReferencia.POLICIA:
			puntoReferencia = new Policia((PoliciaDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().modificarPuntoReferencia(puntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		case TipoPuntoReferencia.REFUGIO:
			puntoReferencia = new Refugio((RefugioDTO)dto);
		break;
		case TipoPuntoReferencia.BOMBEROS:
			puntoReferencia = new Bomberos((BomberosDTO)dto);
		break;
		case TipoPuntoReferencia.CAMINERA:
			puntoReferencia = new Caminera((CamineraDTO)dto);
		break;
		case TipoPuntoReferencia.CUARTEL:
			puntoReferencia = new Cuartel((CuartelDTO)dto);
		break;
		case TipoPuntoReferencia.OTROS:
			puntoReferencia = new PROtros((PROtrosDTO)dto);
		break;
		case TipoPuntoReferencia.POLICIA:
			puntoReferencia = new Policia((PoliciaDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().eliminarPuntoReferencia(puntoReferencia);
	}

	


}
