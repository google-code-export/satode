package fing.satode.pl.desastres;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Desastre;
import fing.satode.dominio.Usuario;
import fing.satode.pl.base.DAOBase;


public class DesastreDAO extends DAOBase {

	private static DesastreDAO instance;
	
	private DesastreDAO(){}
	
	public static DesastreDAO getInstance(){
		if(instance==null){
			instance = new DesastreDAO();
		}
		
		return instance;
	}

	public ArrayList<Desastre> listaDesastres() {
		List list=sess().createQuery("from Desastre order by fechaDeclaracion desc").list();
		ArrayList<Desastre> res= new ArrayList<Desastre>(list);
		
		return res;
	}

	public void nuevoDesastre(Desastre desastre) {
		// TODO Auto-generated method stub
		sess().save(desastre);
	}

	public void modificarDesastre(Desastre desastre) {
		// TODO Auto-generated method stub
		sess().update(desastre);
	}

	public void eliminarDesastre(Desastre desastre) {
		// TODO Auto-generated method stub
		sess().delete(desastre);
	}

}
