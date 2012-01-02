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
	
	public PuntoReferencia buscarPuntoReferencia(int puntoRef){
		return (PuntoReferencia) sess().createQuery("from PuntoReferencia Where id ='"+puntoRef+"'").uniqueResult();
	}

	public ArrayList<PuntoReferencia> listaPuntosReferencia() {
		List list=sess().createQuery("from PuntoReferencia order by id").list();
		ArrayList<PuntoReferencia> res= new ArrayList<PuntoReferencia>(list);
		
		return res;
	}

	public void nuevoPuntoReferencia(PuntoReferencia puntoRef) {
		sess().save(puntoRef);
	}

	public void modificarPuntoReferencia(PuntoReferencia puntoRef) {
		sess().update(puntoRef);
	}

	public void eliminarPuntoReferencia(PuntoReferencia puntoRef) {
		sess().delete(puntoRef);
	}

	
	
}

