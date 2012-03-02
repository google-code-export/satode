package fing.satode.ui.desastres.server;

import java.util.ArrayList;

import fing.satode.bl.base.ServiceFactory;
import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.EstadoDesastreDTO;
import fing.satode.data.TipoCostoDTO;
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

	public ArrayList<TipoCostoDTO> listaTipoCosto() {
		return ServiceFactory.getInstance().getDesastresService().listaTipoCosto();
	}

	public void nuevoTipoCosto(TipoCostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().nuevoTipoCosto(dto);
	}

	public void modificarTipoCosto(TipoCostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().modificarTipoCosto(dto);
	}

	public void eliminarTipoCosto(TipoCostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().eliminarTipoCosto(dto);
	}

	@Override
	public ArrayList<CostoDTO> listaCosto() {
		// TODO Auto-generated method stub
		return ServiceFactory.getInstance().getDesastresService().listaCosto();
	}

	@Override
	public void nuevoCosto(CostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().nuevoCosto(dto);
	}

	@Override
	public void modificarCosto(CostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().modificarCosto(dto);
	}

	@Override
	public void eliminarCosto(CostoDTO dto) {
		ServiceFactory.getInstance().getDesastresService().eliminarCosto(dto);
	}

	@Override
	public ArrayList<CostoDTO> listaCosto(Long idDesastre) {
		return ServiceFactory.getInstance().getDesastresService().listaCosto(idDesastre);
	}

	@Override
	public EstadoDesastreDTO reporteEstadoDesastre(Long idDesastre) {
		return ServiceFactory.getInstance().getDesastresService().reporteEstadoDesastre(idDesastre);
	}

}
