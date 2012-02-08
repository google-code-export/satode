package fing.satode.dominio;

import fing.satode.constantes.TipoPuntoReferencia;
import fing.satode.data.BomberosDTO;
import fing.satode.data.CamineraDTO;
import fing.satode.data.CuartelDTO;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PROtrosDTO;
import fing.satode.data.PoliciaDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.RefugioDTO;

public class Util {

	public static PuntoReferencia crearPuntoReferencia(PuntoReferenciaDTO dto ){
		PuntoReferencia p=null;
		switch (dto.getTipo())
		{
			case TipoPuntoReferencia.HOSPITAL:
				p= new Hospital((HospitalDTO)dto);
			break;
			case TipoPuntoReferencia.REFUGIO:
				p= new Refugio((RefugioDTO)dto);
			break;
			case TipoPuntoReferencia.CAMINERA:
				p= new Caminera((CamineraDTO)dto);
			break;
			case TipoPuntoReferencia.BOMBEROS:
				p= new Bomberos((BomberosDTO)dto);
			break;
			case TipoPuntoReferencia.CUARTEL:
				p= new Cuartel((CuartelDTO)dto);
			break;
			case TipoPuntoReferencia.OTROS:
				p= new PROtros((PROtrosDTO)dto);
			break;
			case TipoPuntoReferencia.POLICIA:
				p= new Policia((PoliciaDTO)dto);
			break;
		}
		
		return p;
	}
	
	public static PuntoReferenciaDTO crearPuntoReferenciaDTO(PuntoReferencia puntoReferencia ){
		PuntoReferenciaDTO p=null;
		switch (puntoReferencia.getTipo()) 
		{
			case TipoPuntoReferencia.HOSPITAL:
				p=((Hospital)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.REFUGIO:
				p=((Refugio)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.BOMBEROS:
				p=((Bomberos)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.CAMINERA:
				p=((Caminera)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.CUARTEL:
				p=((Cuartel)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.OTROS:
				p=((PROtros)puntoReferencia).getDTO();
			break;
			case TipoPuntoReferencia.POLICIA:
				p=((Policia)puntoReferencia).getDTO();
			break;
	
		}
		return p;
	}
}
