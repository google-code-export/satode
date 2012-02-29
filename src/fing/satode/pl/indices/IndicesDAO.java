package fing.satode.pl.indices;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.CalculoIndice;
import fing.satode.dominio.GestionNecesidad;
import fing.satode.dominio.Necesidad;
import fing.satode.pl.base.DAOBase;


public class IndicesDAO extends DAOBase {

	private static IndicesDAO instance;
	
	private IndicesDAO(){}
	
	public static IndicesDAO getInstance(){
		if(instance==null){
			instance = new IndicesDAO();
		}
		
		return instance;
	}

	public ArrayList<CalculoIndice> listaCalculoIndice() {
		List list=sess().createQuery("from CalculoIndice order by fecha , tipo desc").list();
		ArrayList<CalculoIndice> res= new ArrayList<CalculoIndice>(list);
		
		return res;
	}

	public void nuevoCalculoIndice(CalculoIndice necesidad) {
		// TODO Auto-generated method stub
		sess().save(necesidad);
	}

	public void modificarCalculoIndice(CalculoIndice necesidad) {
		// TODO Auto-generated method stub
		sess().update(necesidad);
	}

	public void eliminarCalculoIndice(CalculoIndice necesidad) {
		// TODO Auto-generated method stub
		sess().delete(necesidad);
	}

	public ArrayList<CalculoIndice> buscarCalculoIndice(int tipo ) {
		List list=sess().createQuery("from CalculoIndice where tipo="+tipo+" or "+tipo+"=0 order by fecha , tipo desc").list();
		ArrayList<CalculoIndice> res= new ArrayList<CalculoIndice>(list);
		
		return res;
	}

	public GestionNecesidad buscarGestionNecesidadPorNecesidad(Long idNecesidad) {
		if(idNecesidad!=null){
			return (GestionNecesidad)sess().createQuery("from GestionNecesidad where( necesidad.id="+idNecesidad+" ) ").uniqueResult();
		}else{
			return null;
		}
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
