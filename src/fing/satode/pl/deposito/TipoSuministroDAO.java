package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.base.DAOBase;


public class TipoSuministroDAO extends DAOBase {
	
private static TipoSuministroDAO instance;
	
	private TipoSuministroDAO(){}
	
	public static TipoSuministroDAO getInstance(){
		if(instance==null){
			instance = new TipoSuministroDAO();
		}
		
		return instance;
	}

	public ArrayList<TipoSuministro> listaTipoSuministros() {
		List list=sess().createQuery("from TipoSuministro order by nombre").list();
		ArrayList<TipoSuministro> res= new ArrayList<TipoSuministro>(list);
		
		return res;
	}

	public void nuevoTipoSuministro(TipoSuministro tiposuministro) {
		
		sess().save(tiposuministro);
	}

	public void modificarTipoSuministro(TipoSuministro tiposuministro) {
		
		sess().update(tiposuministro);
	}

	public void eliminarTipoSuministro(TipoSuministro tiposuministro) {
		// TODO Auto-generated method stub
		sess().delete(tiposuministro);
	}


}
