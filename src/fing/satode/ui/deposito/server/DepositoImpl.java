package fing.satode.ui.deposito.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.DepositoDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.deposito.client.IDeposito;


public class DepositoImpl extends ServerImpl implements IDeposito {

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<DepositoDTO> listaDepositos() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getDepositoService().listaDepositos();
	}

	@Override
	public void nuevoDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().nuevoDeposito(dto);
	}

	@Override
	public void modificarDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().modificarDeposito(dto);
	}

	@Override
	public void eliminarDeposito(DepositoDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().eliminarDeposito(dto);
	}

	@Override
	public ArrayList<TipoSuministroDTO> listaTipoSuministros() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getDepositoService().listaTipoSuministros();
	}

	@Override
	public void nuevoTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().nuevoTipoSuministro(dto);
	}

	@Override
	public void modificarTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().modificarTipoSuministro(dto);
	}

	@Override
	public void eliminarTipoSuministro(TipoSuministroDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDepositoService().eliminarTipoSuministro(dto);
	}

}
