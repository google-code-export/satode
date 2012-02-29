package fing.satode.bl.propiedadesSiniestradas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.BomberosDTO;
import fing.satode.data.CamineraDTO;
import fing.satode.data.CuartelDTO;
import fing.satode.data.FotoDTO;
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
import fing.satode.dominio.Foto;
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
		
		Parcela p=new Parcela(dto);
		Parcela pOrigen=PropiedadesSiniestradasDAO.getInstance().getParcela(dto.getId());
		p.getFotos().addAll(pOrigen.getFotos());
		
		PropiedadesSiniestradasDAO.getInstance().modificarParcela(p);
	}

	public void eliminarParcela(ParcelaDTO dto) {
		PropiedadesSiniestradasDAO.getInstance().eliminarParcela(new Parcela(dto));
	}
	
	public FotoDTO getFoto(Long id) {
		return PropiedadesSiniestradasDAO.getInstance().getFoto(id).getDTO();
	}


	public void borrarFotos(Long idParcela, ArrayList<FotoDTO> fotosBorradas) {
		ArrayList<Foto> fotosAEliminar = new ArrayList<Foto>();
		for(FotoDTO dto : fotosBorradas){
			Foto foto = new Foto(dto);
			fotosAEliminar.add(foto);
		}
		PropiedadesSiniestradasDAO.getInstance().borrarFotos( idParcela, fotosAEliminar);
		
	}
}
