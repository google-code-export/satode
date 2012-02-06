package fing.satode.ui.deposito.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.DepositoDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;




@RemoteServiceRelativePath("deposito")
public interface IDeposito extends RemoteService {


	public ArrayList<DepositoDTO> listaDepositos();
	public void nuevoDeposito(DepositoDTO dto);
	public void modificarDeposito(DepositoDTO dto);
	public void eliminarDeposito(DepositoDTO dto);
	
	public ArrayList<TipoSuministroDTO> listaTipoSuministros();
	public void nuevoTipoSuministro(TipoSuministroDTO dto);
	public void modificarTipoSuministro(TipoSuministroDTO dto);
	public void eliminarTipoSuministro(TipoSuministroDTO dto);
	
	public ArrayList<SuministroDTO> listaSuministros();
	public void nuevoSuministro(SuministroDTO dto);
	public void modificarSuministro(SuministroDTO dto);
	public void eliminarSuministro(SuministroDTO dto);
	
}
