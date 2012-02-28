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
		String[] archivos = new File("/tmp/"+ dto.getUsuario().getUsuario()+"/antes/" ).list();
		ArrayList<FotoDTO> fotos=new ArrayList<FotoDTO>();
		for(String imagenPath:archivos){
			File file=new File("/tmp/"+ dto.getUsuario().getUsuario()+"/antes/"+imagenPath);
			try {
				FileInputStream stream=new FileInputStream(file);
				int size=stream.available();
				byte[] buff=new byte[size];
				stream.read(buff, 0, size);
				FotoDTO foto=new FotoDTO();
				foto.setAntes(true);
				foto.setDatos(buff);
				fotos.add(foto);
				file.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		archivos = new File("/tmp/"+ dto.getUsuario().getUsuario()+"/despues/" ).list();
		for(String imagenPath:archivos){
			File file=new File("/tmp/"+ dto.getUsuario().getUsuario()+"/despues/"+imagenPath);
			try {
				FileInputStream stream=new FileInputStream(file);
				int size=stream.available();
				byte[] buff=new byte[size];
				stream.read(buff, 0, size);
				FotoDTO foto=new FotoDTO();
				foto.setAntes(false);
				foto.setDatos(buff);
				fotos.add(foto);
				file.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		dto.setFotos(fotos);
		
		
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().nuevaParcela(dto);
	}
	
	@Override
	public void modificarParcela(ParcelaDTO dto) {
		String[] archivos = new File("/tmp/"+ dto.getUsuario().getUsuario()+"/antes/" ).list();
		ArrayList<FotoDTO> fotos=new ArrayList<FotoDTO>();
		for(String imagenPath:archivos){
			File file=new File("/tmp/"+ dto.getUsuario().getUsuario()+"/antes/"+imagenPath);
			try {
				FileInputStream stream=new FileInputStream(file);
				int size=stream.available();
				byte[] buff=new byte[size];
				stream.read(buff, 0, size);
				FotoDTO foto=new FotoDTO();
				foto.setAntes(true);
				foto.setDatos(buff);
				foto.setNombre(file.getName());
				fotos.add(foto);
				file.delete();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		archivos = new File("/tmp/"+ dto.getUsuario().getUsuario()+"/despues/" ).list();
		for(String imagenPath:archivos){
			File file=new File("/tmp/"+ dto.getUsuario().getUsuario()+"/despues/"+imagenPath);
			try {
				FileInputStream stream=new FileInputStream(file);
				int size=stream.available();
				byte[] buff=new byte[size];
				stream.read(buff, 0, size);
				FotoDTO foto=new FotoDTO();
				foto.setAntes(false);
				foto.setNombre(file.getName());
				foto.setDatos(buff);
				fotos.add(foto);
				file.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
	
	
}
