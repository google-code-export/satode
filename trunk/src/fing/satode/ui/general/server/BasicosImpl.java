package fing.satode.ui.general.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.DepartamentoDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.general.client.IBasicos;

public class BasicosImpl extends ServerImpl implements IBasicos {

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<DepartamentoDTO> listaDepartamentos() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getBasicosService().listaDepartamentos();
	}

}
