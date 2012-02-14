package fing.satode.ui.propiedadesSiniestradas.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.data.DepositoDTO;
import fing.satode.data.DonacionDTO;
import fing.satode.data.ParcelaDTO;
import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;
import fing.satode.dominio.Parcela;
import fing.satode.pl.propiedadesSiniestradas.PropiedadesSiniestradasDAO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.deposito.client.IDeposito;
import fing.satode.ui.propiedadesSiniestradas.client.IPropiedadesSiniestradas;


public class PropiedadesSiniestradasImpl extends ServerImpl implements IPropiedadesSiniestradas {

	private static final long serialVersionUID = 1L;


	@Override
	public ArrayList<ParcelaDTO> listaParcelas() {
		
		return ServiceFactory.getInstance().getPropiedadesSiniestradasService().listaParcelas();
	}
	
	@Override
	public void nuevaParcela(ParcelaDTO dto) {
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().nuevaParcela(dto);
	}
	
	@Override
	public void modificarParcela(ParcelaDTO dto) {
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().modificarParcela(dto);
	}
	
	@Override
	public void eliminarParcela(ParcelaDTO dto) {
		ServiceFactory.getInstance().getPropiedadesSiniestradasService().eliminarParcela(dto);
	}
	
	
}
