package fing.satode.pl.necesidades;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Necesidad;
import fing.satode.pl.base.DAOBase;


public class NecesidadesDAO extends DAOBase {

	private static NecesidadesDAO instance;
	
	private NecesidadesDAO(){}
	
	public static NecesidadesDAO getInstance(){
		if(instance==null){
			instance = new NecesidadesDAO();
		}
		
		return instance;
	}

	public ArrayList<Necesidad> listaNecesidades() {
		List list=sess().createQuery("from Necesidad order by fecha desc").list();
		ArrayList<Necesidad> res= new ArrayList<Necesidad>(list);
		
		return res;
	}

	public void nuevoNecesidad(Necesidad necesidad) {
		// TODO Auto-generated method stub
		sess().save(necesidad);
	}

	public void modificarNecesidad(Necesidad necesidad) {
		// TODO Auto-generated method stub
		sess().update(necesidad);
	}

	public void eliminarNecesidad(Necesidad necesidad) {
		// TODO Auto-generated method stub
		sess().delete(necesidad);
	}

	public ArrayList<Necesidad> buscarNecesidades(Long idDesastre, Long idEstado) {
		List list=sess().createQuery("from Necesidad where( (estado="+idEstado+" or "+idEstado+"=0) and (desastre.id="+idDesastre+" or "+idDesastre+"=0) ) order by fecha desc").list();
		ArrayList<Necesidad> res= new ArrayList<Necesidad>(list);
		
		return res;
	}

}
