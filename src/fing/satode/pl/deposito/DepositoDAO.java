package fing.satode.pl.deposito;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Deposito;
import fing.satode.pl.base.DAOBase;


public class DepositoDAO extends DAOBase {
	
private static DepositoDAO instance;
	
	private DepositoDAO(){}
	
	public static DepositoDAO getInstance(){
		if(instance==null){
			instance = new DepositoDAO();
		}
		
		return instance;
	}

	public ArrayList<Deposito> listaDepositos() {
		List list=sess().createQuery("from Deposito order by responsable").list();
		ArrayList<Deposito> res= new ArrayList<Deposito>(list);
		
		return res;
	}

	public void nuevoDeposito(Deposito deposito) {
		
		sess().save(deposito);
	}

	public void modificarDeposito(Deposito deposito) {
		
		sess().update(deposito);
	}

	public void eliminarDeposito(Deposito deposito) {
		// TODO Auto-generated method stub
		sess().delete(deposito);
	}


}
