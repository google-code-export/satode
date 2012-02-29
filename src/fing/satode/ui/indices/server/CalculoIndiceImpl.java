package fing.satode.ui.indices.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CalculoIndiceDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.indices.client.IIndices;

public class CalculoIndiceImpl extends ServerImpl implements IIndices {

	private static final long serialVersionUID = 1L;

	
	@Override
	public ArrayList<CalculoIndiceDTO> buscarCalculoIndice(int tipo) {
		return 	ServiceFactory.getInstance().getIndicesService().buscarCalculoIndice(tipo);
	}

	@Override
	public void calcularIDL(CalculoIndiceDTO dto) {
		ServiceFactory.getInstance().getIndicesService().calcularIDL(dto);
	}

}
