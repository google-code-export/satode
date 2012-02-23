package fing.satode.bl.desastres;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.CostoDTO;
import fing.satode.data.DesastreDTO;
import fing.satode.data.TipoCostoDTO;
import fing.satode.dominio.Costo;
import fing.satode.dominio.Desastre;
import fing.satode.dominio.TipoCosto;
import fing.satode.pl.desastres.CostoDAO;
import fing.satode.pl.desastres.DesastreDAO;
import fing.satode.pl.desastres.TipoCostoDAO;

@Transactional
public class DesastresService extends ServiceBase {

	public ArrayList<DesastreDTO> listaDesastres() {
		// TODO Auto-generated method stub
		ArrayList<DesastreDTO> listaDTOS= new ArrayList<DesastreDTO>();
		ArrayList<Desastre> listaDes= DesastreDAO.getInstance().listaDesastres();
		for(Desastre d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		Desastre desastre= new Desastre(dto);
		DesastreDAO.getInstance().nuevoDesastre(desastre);
	}

	public void modificarDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		Desastre desastre= new Desastre(dto);
		DesastreDAO.getInstance().modificarDesastre(desastre);
	}

	public void eliminarDesastre(DesastreDTO dto) {
		// TODO Auto-generated method stub
		Desastre desastre= new Desastre(dto);
		DesastreDAO.getInstance().eliminarDesastre(desastre);
	}
	
	public ArrayList<TipoCostoDTO> listaTipoCosto() {
		// TODO Auto-generated method stub
		ArrayList<TipoCostoDTO> listaDTOS= new ArrayList<TipoCostoDTO>();
		ArrayList<TipoCosto> listaDes= TipoCostoDAO.getInstance().listaTipoCosto();
		for(TipoCosto d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoTipoCosto(TipoCostoDTO dto) {
		// TODO Auto-generated method stub
		TipoCosto tipoCosto= new TipoCosto(dto);
		TipoCostoDAO.getInstance().nuevoTipoCosto(tipoCosto);
	}

	public void modificarTipoCosto(TipoCostoDTO dto) {
		// TODO Auto-generated method stub
		TipoCosto tipoCosto= new TipoCosto(dto);
		TipoCostoDAO.getInstance().modificarTipoCosto(tipoCosto);
	}

	public void eliminarTipoCosto(TipoCostoDTO dto) {
		// TODO Auto-generated method stub
		TipoCosto tipoCosto= new TipoCosto(dto);
		TipoCostoDAO.getInstance().eliminarTipoCosto(tipoCosto);
	}

	public ArrayList<CostoDTO> listaCosto() {
		// TODO Auto-generated method stub
		ArrayList<CostoDTO> listaDTOS= new ArrayList<CostoDTO>();
		ArrayList<Costo> listaDes= CostoDAO.getInstance().listaoCosto();
		for(Costo d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoCosto(CostoDTO dto) {
		Costo costo= new Costo(dto);
		CostoDAO.getInstance().nuevoCosto(costo);
	}

	public void modificarCosto(CostoDTO dto) {
		Costo costo= new Costo(dto);
		CostoDAO.getInstance().modificarCosto(costo);
	}

	public void eliminarCosto(CostoDTO dto) {
		Costo costo= new Costo(dto);
		CostoDAO.getInstance().eliminarCosto(costo);
	}

	public ArrayList<CostoDTO> listaCosto(Long idDesastre) {
		ArrayList<CostoDTO> listaDTOS= new ArrayList<CostoDTO>();
		ArrayList<Costo> listaDes= CostoDAO.getInstance().listaoCosto(idDesastre);
		for(Costo d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}
}
