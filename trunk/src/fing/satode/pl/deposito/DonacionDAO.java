package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.CuentaCorrienteSuministro;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Donacion;
import fing.satode.dominio.Suministro;

import fing.satode.pl.base.DAOBase;

public class DonacionDAO extends DAOBase {

	private static DonacionDAO instance;
	
	private DonacionDAO(){}
	
	public static DonacionDAO getInstance(){
		if(instance==null){
			instance = new DonacionDAO();
		}
		
		return instance;
	}
	
	public ArrayList<Donacion> listaDonaciones() {
		List list=sess().createQuery("from Donacion order by fecha").list();
		ArrayList<Donacion> res= new ArrayList<Donacion>(list);
		
		return res;
	}

	public void nuevoDonacion(Donacion donacion) {
		
		sess().save(donacion);
		sess().flush();

	}

	public void modificarDonacion(Donacion donacion) {
		
		sess().update(donacion);
	}

	public void eliminarDonacion(Donacion donacion) {
		sess().delete(donacion);
	}

	
}
