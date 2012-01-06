package fing.satode.pl.puntoReferencia;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.PuntoReferencia;
import fing.satode.pl.base.DAOBase;

public class PuntoReferenciaDAO extends DAOBase {

	private static PuntoReferenciaDAO instance;
	
	private PuntoReferenciaDAO(){}
	
	public static PuntoReferenciaDAO getInstance(){
		if(instance==null){
			instance = new PuntoReferenciaDAO();
		}
		
		return instance;
	}
	
	public PuntoReferencia buscarPuntoReferencia(int id){
		return (PuntoReferencia) sess().createQuery("from PuntoReferencia Where id ='"+ id +"'").uniqueResult();
	}

	public ArrayList<PuntoReferencia> listaPuntosReferencias() {
		List list=sess().createQuery("from PuntoReferencia order by tipo").list();
		ArrayList<PuntoReferencia> res= new ArrayList<PuntoReferencia>(list);
		
		return res;
	}

	

	public void nuevoPuntoReferencia(PuntoReferencia puntoReferencia) {
		sess().save(puntoReferencia);
	}

	public void modificarPuntoReferencia(PuntoReferencia puntoReferencia) {
		sess().update(puntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferencia puntoReferencia) {
		sess().delete(puntoReferencia);
	}
	
}
