package fing.satode.ui.desastres.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.DesastreDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.desastres.client.IDesastre;

public class DesastreImpl extends ServerImpl implements IDesastre {

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<DesastreDTO> listaDesastres() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getDesastresService().listaDesastres();
	}

	@Override
	public void nuevoDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDesastresService().nuevoDesastre(dto);
	}

	@Override
	public void modificarDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDesastresService().modificarDesastre(dto);
	}

	@Override
	public void eliminarDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getDesastresService().eliminarDesastre(dto);
	}

}
