package fing.satode.ui.deposito.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.DepositoDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.deposito.client.IDeposito;


public class DepositoImpl extends ServerImpl implements IDeposito {

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<DepositoDTO> listaDepositos() {
		return ServiceFactory.getInstance().getDepositoService().listaDepositos();
	}

	@Override
	public void nuevoDeposito(DepositoDTO dto) {
		ServiceFactory.getInstance().getDepositoService().nuevoDeposito(dto);
	}

	@Override
	public void modificarDeposito(DepositoDTO dto) {
		ServiceFactory.getInstance().getDepositoService().modificarDeposito(dto);
	}

	@Override
	public void eliminarDeposito(DepositoDTO dto) {
		ServiceFactory.getInstance().getDepositoService().eliminarDeposito(dto);
	}

	@Override
	public ArrayList<TipoSuministroDTO> listaTipoSuministros() {
		return ServiceFactory.getInstance().getDepositoService().listaTipoSuministros();
	}

	@Override
	public void nuevoTipoSuministro(TipoSuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().nuevoTipoSuministro(dto);
	}

	@Override
	public void modificarTipoSuministro(TipoSuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().modificarTipoSuministro(dto);
	}

	@Override
	public void eliminarTipoSuministro(TipoSuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().eliminarTipoSuministro(dto);
	}

	@Override
	public ArrayList<SuministroDTO> listaSuministros() {
		return ServiceFactory.getInstance().getDepositoService().listaSuministros();
	}

	@Override
	public void nuevoSuministro(SuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().nuevoSuministro(dto);
	}

	@Override
	public void modificarSuministro(SuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().modificarSuministro(dto);
		
	}

	@Override
	public void eliminarSuministro(SuministroDTO dto) {
		ServiceFactory.getInstance().getDepositoService().eliminarSuministro(dto); 
		
	}

}
