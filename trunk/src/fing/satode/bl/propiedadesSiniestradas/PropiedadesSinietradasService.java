package fing.satode.bl.propiedadesSiniestradas;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.BomberosDTO;
import fing.satode.data.CamineraDTO;
import fing.satode.data.CuartelDTO;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PROtrosDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.PoliciaDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.RefugioDTO;
import fing.satode.constantes.TipoPuntoReferencia;
import fing.satode.dominio.Bomberos;
import fing.satode.dominio.Caminera;
import fing.satode.dominio.Cuartel;
import fing.satode.dominio.Hospital;
import fing.satode.dominio.PROtros;
import fing.satode.dominio.Parcela;
import fing.satode.dominio.Policia;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.dominio.Refugio;
import fing.satode.dominio.Util;
import fing.satode.pl.propiedadesSiniestradas.PropiedadesSiniestradasDAO;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PropiedadesSinietradasService extends ServiceBase {

	public ArrayList<ParcelaDTO> listaParcelas() {
		
		ArrayList<ParcelaDTO> listaDTOS= new ArrayList<ParcelaDTO>();
		ArrayList<Parcela> listaParcelas= PropiedadesSiniestradasDAO.getInstance().listaParcelas();
		
		for(Parcela p: listaParcelas){
			listaDTOS.add(p.getDTO());	
		}
		
		return listaDTOS;
	}
	

	public void nuevaParcela(ParcelaDTO dto) {
		PropiedadesSiniestradasDAO.getInstance().nuevaParcela(new Parcela(dto));
	}
	
	
	public void modificarParcela(ParcelaDTO dto) {
		PropiedadesSiniestradasDAO.getInstance().modificarParcela(new Parcela(dto));
	}

	public void eliminarParcela(ParcelaDTO dto) {
		PropiedadesSiniestradasDAO.getInstance().eliminarParcela(new Parcela(dto));
	}
}
