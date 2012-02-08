package fing.satode.ui.necesidades.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.NecesidadDTO;
import fing.satode.ui.base.ServerImpl;
import fing.satode.ui.necesidades.client.INecesidad;

public class NecesidadImpl extends ServerImpl implements INecesidad {

	private static final long serialVersionUID = 1L;

	@Override
	public void nuevoNecesidad(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getNecesidadService().nuevoNecesidade(dto);
	}

	@Override
	public void modificarNecesidad(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getNecesidadService().modificarNecesidad(dto);
	}

	@Override
	public void eliminarNecesidad(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		ServiceFactory.getInstance().getNecesidadService().eliminarNecesidad(dto);
	}

	@Override
	public ArrayList<NecesidadDTO> buscarNecesidades(Long idDesastre,
			Long idEstado) {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getNecesidadService().buscarNecesidades(idDesastre,idEstado);
	}

}
