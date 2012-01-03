package fing.satode.bl.desastres;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DesastreDTO;
import fing.satode.dominio.Desastre;
import fing.satode.pl.desastres.DesastreDAO;

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

}
