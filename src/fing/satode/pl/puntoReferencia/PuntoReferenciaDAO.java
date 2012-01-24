package fing.satode.pl.puntoReferencia;

import java.util.ArrayList;
import java.util.List;


import fing.satode.data.TipoPuntoReferencia;
import fing.satode.dominio.Hospital;
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
	
	public ArrayList<Hospital> listaHospitales() {
		List list=sess().createQuery("from Hospital order by id").list();
		ArrayList<Hospital> res= new ArrayList<Hospital>(list);
		
		return res;
	}

	

	public void nuevoPuntoReferencia(PuntoReferencia puntoReferencia) {
		switch (puntoReferencia.getTipo())
		{
		case TipoPuntoReferencia.HOSPITAL:
			Hospital hospital = (Hospital) puntoReferencia;
			sess().save(hospital);
		break;	
		}
		//sess().save(puntoReferencia);
	}
	
	public void nuevoHospital(Hospital puntoReferencia) {
		sess().save(puntoReferencia);
	}
	
	public Hospital buscarHospital (Long id){
		return (Hospital) sess().createQuery(" from Hospital where puntoReferencia_Id ='"+ id +"'").uniqueResult();
	}


	public void modificarPuntoReferencia(PuntoReferencia puntoReferencia) {
		sess().update(puntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferencia puntoReferencia) {
		sess().delete(puntoReferencia);
	}
	
}
