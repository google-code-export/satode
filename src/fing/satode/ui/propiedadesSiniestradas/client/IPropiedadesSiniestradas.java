package fing.satode.ui.propiedadesSiniestradas.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;




@RemoteServiceRelativePath("propiedadesSiniestradas")
public interface IPropiedadesSiniestradas extends RemoteService {


	public ArrayList<ParcelaDTO> listaParcelas();
	public void  nuevaParcela(ParcelaDTO dto);
	public void modificarParcela(ParcelaDTO dto);
	public void eliminarParcela(ParcelaDTO dto);
	public void setFoto(Boolean antes);
		
	
	
	
}
