package fing.satode.pl.desastres;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Costo;
import fing.satode.pl.base.DAOBase;

public class CostoDAO extends DAOBase {

	private static CostoDAO instance;
	
	private CostoDAO(){}
	
	public static CostoDAO getInstance(){
		if(instance==null){
			instance = new CostoDAO();
		}
		
		return instance;
	}
	
	public ArrayList<Costo> listaoCosto() {
		List list=sess().createQuery("from Costo order by fecha desc").list();
		ArrayList<Costo> res= new ArrayList<Costo>(list);
		
		return res;
	}

	public ArrayList<Costo> listaoCosto(Long idDesastre) {
		List list=sess().createQuery("from Costo where desastre.id="+idDesastre+" or "+idDesastre+"=0 order by fecha desc").list();
		ArrayList<Costo> res= new ArrayList<Costo>(list);
		
		return res;
	}
	public void nuevoCosto(Costo costo) {
		// TODO Auto-generated method stub
		sess().save(costo);
	}

	public void modificarCosto(Costo costo) {
		// TODO Auto-generated method stub
		sess().update(costo);
	}

	public void eliminarCosto(Costo costo) {
		// TODO Auto-generated method stub
		sess().delete(costo);
	}

}
