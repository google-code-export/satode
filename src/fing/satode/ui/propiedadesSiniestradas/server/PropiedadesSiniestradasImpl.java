package fing.satode.ui.propiedadesSiniestradas.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.FotoDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.Parcela;
import fing.satode.pl.propiedadesSiniestradas.PropiedadesSiniestradasDAO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.deposito.client.IDeposito;
import fing.satode.ui.propiedadesSiniestradas.client.IPropiedadesSiniestradas;
import gwtupload.server.UploadListener;


public class PropiedadesSiniestradasImpl extends ServerImpl implements IPropiedadesSiniestradas {

	private static final long serialVersionUID = 1L;


	@Override
	public ArrayList<ParcelaDTO> listaParcelas() {
		
		return ServiceFactory.getInstance().getPropiedadesSiniestradasService().listaParcelas();
	}
	
	@Override
	public void nuevaParcela(ParcelaDTO dto) {
		ArrayList<FotoDTO> fotos=(ArrayList<FotoDTO>)perThreadRequest.get().getSession().getAttribute("foto.lista");
		if(fotos==null){
			fotos=new ArrayList<FotoDTO>();
		}else{
			perThreadRequest.get().getSession().removeAttribute("foto.lista");
		}
		dto.setFotos(fotos);
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().nuevaParcela(dto);
	}
	
	@Override
	public void modificarParcela(ParcelaDTO dto) {
		ArrayList<FotoDTO> fotos=(ArrayList<FotoDTO>)perThreadRequest.get().getSession().getAttribute("foto.lista");
		if(fotos==null){
			fotos=new ArrayList<FotoDTO>();
		}else{
			perThreadRequest.get().getSession().removeAttribute("foto.lista");
		}
		dto.setFotos(fotos);
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().modificarParcela(dto);
	
	}
	

	@Override
	public void eliminarParcela(ParcelaDTO dto) {
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().eliminarParcela(dto);
	}
	
	public void setFoto(Boolean antes) {
		perThreadRequest.get().getSession().setAttribute("foto.tipo", antes);
	}

	@Override
	public void borrarFotos(Long idParcela, ArrayList<FotoDTO> fotosBorradas) {
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().borrarFotos( idParcela,  fotosBorradas);
	}
	
	
}
