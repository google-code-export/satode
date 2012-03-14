package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.data.CuentaCorrienteSuministroDTO;
import fing.satode.dominio.CuentaCorrienteSuministro;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Necesidad;
import fing.satode.dominio.Suministro;
import fing.satode.dominio.TipoSuministro;
import fing.satode.pl.base.DAOBase;

public class CuentaCorrienteSuministroDAO extends DAOBase {
	
	private static CuentaCorrienteSuministroDAO instance;
	
	private CuentaCorrienteSuministroDAO(){}
	
	public static CuentaCorrienteSuministroDAO getInstance(){
		if(instance==null){
			instance = new CuentaCorrienteSuministroDAO();
		}
		
		return instance;
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

	public CuentaCorrienteSuministro getCuentaCorriente(Long idDeposto,	Long idTipoSuministro) {
		CuentaCorrienteSuministro cuenta=(CuentaCorrienteSuministro)sess().createQuery("from CuentaCorrienteSuministro where deposito.id="+idDeposto +" and tipoSuministro.id="+idTipoSuministro+"").uniqueResult();
		if(cuenta==null){
			cuenta = new CuentaCorrienteSuministro();
			cuenta.setTipoSuministro((TipoSuministro)sess().get(TipoSuministro.class, idTipoSuministro));
			cuenta.setCantidad(0);
			cuenta.setDeposito((Deposito)sess().get(Deposito.class, idDeposto));
			Long id=(Long)sess().save(cuenta);
			cuenta.setId(id);
		}		
		return cuenta;
	}
	public void modificarCuentaCorriente(CuentaCorrienteSuministro cuenta) {
		// TODO Auto-generated method stub
		sess().update(cuenta);
	}

	public ArrayList<CuentaCorrienteSuministro> buscarCuentaCorrienteSuministro(
			Long idDeposito, Long idTipoSuministro) {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from CuentaCorrienteSuministro where( (deposito.id="+idDeposito+" or "+idDeposito+"=0) and (tipoSuministro.id="+idTipoSuministro+" or "+idTipoSuministro+"=0) ) order by tipoSuministro.nombre").list();
		ArrayList<CuentaCorrienteSuministro> res= new ArrayList<CuentaCorrienteSuministro>(list);
		
		return res;
	}

	public void eliminarCuentaCorriente(CuentaCorrienteSuministro cuenta) {
		// TODO Auto-generated method stub
		sess().delete(cuenta);
	}
}
