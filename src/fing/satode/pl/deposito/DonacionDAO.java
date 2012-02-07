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

	public CuentaCorrienteSuministro getCuentaCorriente(Suministro s,
			Deposito deposito) {
		CuentaCorrienteSuministro cuenta=(CuentaCorrienteSuministro)sess().createQuery("from CuentaCorrienteSuministro where deposito.id="+deposito.getId()+" and tipoSuministro.id="+s.getTipo().getId()+"").uniqueResult();
		if(cuenta==null){
			cuenta = new CuentaCorrienteSuministro();
			cuenta.setTipoSuministro(s.getTipo());
			cuenta.setCantidad(0);
			cuenta.setDeposito(deposito);
			Long id=(Long)sess().save(cuenta);
			cuenta.setId(id);
		}
				
		return cuenta;
	}

	public void modificarCuentaCorriente(CuentaCorrienteSuministro cuenta) {
		// TODO Auto-generated method stub
		sess().update(cuenta);
	}
}
