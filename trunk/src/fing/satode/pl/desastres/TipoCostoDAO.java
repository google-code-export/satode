package fing.satode.pl.desastres;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Desastre;
import fing.satode.dominio.TipoCosto;
import fing.satode.pl.base.DAOBase;

public class TipoCostoDAO extends DAOBase {

	private static TipoCostoDAO instance;
	
	private TipoCostoDAO(){}
	
	public static TipoCostoDAO getInstance(){
		if(instance==null){
			instance = new TipoCostoDAO();
		}
		
		return instance;
	}
	
	public ArrayList<TipoCosto> listaTipoCosto() {
		List list=sess().createQuery("from TipoCosto order by nombre desc").list();
		ArrayList<TipoCosto> res= new ArrayList<TipoCosto>(list);
		
		return res;
	}

	public void nuevoTipoCosto(TipoCosto tipoCosto) {
		// TODO Auto-generated method stub
		sess().save(tipoCosto);
	}

	public void modificarTipoCosto(TipoCosto tipoCosto) {
		// TODO Auto-generated method stub
		sess().update(tipoCosto);
	}

	public void eliminarTipoCosto(TipoCosto tipoCosto) {
		// TODO Auto-generated method stub
		sess().delete(tipoCosto);
	}

}
