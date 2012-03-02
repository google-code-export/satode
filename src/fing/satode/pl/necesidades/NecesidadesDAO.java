package fing.satode.pl.necesidades;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.GestionNecesidad;
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

	public GestionNecesidad buscarGestionNecesidadPorNecesidad(Long idNecesidad) {
		if(idNecesidad!=null){
			return (GestionNecesidad)sess().createQuery("from GestionNecesidad where( necesidad.id="+idNecesidad+" ) ").uniqueResult();
		}else{
			return null;
		}
	}

	public ArrayList<GestionNecesidad>  buscarGestionNecesidadesPorDesastre(Long idDesastre) {
			List list=sess().createQuery("from GestionNecesidad where( necesidad.desastre.id="+idDesastre+" ) ").list();
			ArrayList<GestionNecesidad> res= new ArrayList<GestionNecesidad>(list);
			return res;
	}
	
	public void nuevoGestionNecesidad(GestionNecesidad necesidad) {
		Long id=(Long)sess().save(necesidad);
		necesidad.setId(id);
	}

	public void modificarGestionNecesidad(GestionNecesidad necesidad) {
		sess().update(necesidad);
	}

	public ArrayList<Necesidad> buscarNecesidades(Long idDesastre,
			Long idEstado, boolean recursosLocales) {
		List list=sess().createQuery("from Necesidad where( (estado="+idEstado+" or "+idEstado+"=0) and (desastre.id="+idDesastre+" or "+idDesastre+"=0) and recursosLocales="+recursosLocales+") order by fecha desc").list();
		ArrayList<Necesidad> res= new ArrayList<Necesidad>(list);
		
		return res;
	}

}
