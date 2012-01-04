package fing.satode.ui.deposito.client;


import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fing.satode.data.DepositoDTO;




@RemoteServiceRelativePath("deposito")
public interface IDeposito extends RemoteService {


	public ArrayList<DepositoDTO> listaDepositos();
	public void nuevoDeposito(DepositoDTO dto);
	public void modificarDeposito(DepositoDTO dto);
	public void eliminarDeposito(DepositoDTO dto);
	
}
