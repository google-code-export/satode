package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Suministro;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.base.DAOBase;


public class SuministroDAO extends DAOBase {
	
private static SuministroDAO instance;
	
	private SuministroDAO(){}
	
	public static SuministroDAO getInstance(){
		if(instance==null){
			instance = new SuministroDAO();
		}
		
		return instance;
	}

	public ArrayList<Suministro> listaSuministros() {
		List list=sess().createQuery("from Suministro order by tipo").list();
		ArrayList<Suministro> res= new ArrayList<Suministro>(list);
		
		return res;
	}

	public void nuevoSuministro(Suministro suministro) {
		
		sess().save(suministro);
	}

	public void modificarSuministro(Suministro suministro) {
		
		sess().update(suministro);
	}

	public void eliminarSuministro(Suministro suministro) {
		sess().delete(suministro);
	}


}
