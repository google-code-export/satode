package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.SolicitudEnvio;
import fing.satode.pl.base.DAOBase;

public class SolicitudEnvioDAO extends DAOBase {

	private static SolicitudEnvioDAO instance;
	
	private SolicitudEnvioDAO(){}
	
	public static SolicitudEnvioDAO getInstance(){
		if(instance==null){
			instance = new SolicitudEnvioDAO();
		}
		
		return instance;
	}
	
	public ArrayList<SolicitudEnvio> buscarSolicitudesEnvio(Long idPuntoEntrega, Long idDeposito, int estado){
		List list=null;
		if(estado!=0){
			list=sess().createQuery("from SolicitudEnvio where (deposito.id="+idDeposito+" or "+idDeposito+"=0) and (puntoEntrega.id="+idPuntoEntrega+" or "+idPuntoEntrega+"=0) and ( estado="+estado+" ) )  order by estado asc , fecha asc ").list();
		}else{
			list=sess().createQuery("from SolicitudEnvio where (deposito.id="+idDeposito+" or "+idDeposito+"=0) and (puntoEntrega.id="+idPuntoEntrega+" or "+idPuntoEntrega+"=0) and ( estado<>0 ) )  order by estado asc , fecha asc ").list();
		}
		ArrayList<SolicitudEnvio> res= new ArrayList<SolicitudEnvio>(list);
		
		return res;
	}
	
	public void modificarSolicitudEnvio(SolicitudEnvio solenv){
		sess().update(solenv);
	}
	
	
}
