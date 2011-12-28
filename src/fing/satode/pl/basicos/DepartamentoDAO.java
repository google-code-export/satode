package fing.satode.pl.basicos;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Departamento;
import fing.satode.dominio.Usuario;
import fing.satode.pl.base.DAOBase;

public class DepartamentoDAO extends DAOBase {

	private static DepartamentoDAO instance;
	
	private DepartamentoDAO(){}
	
	public static DepartamentoDAO getInstance(){
		if(instance==null){
			instance = new DepartamentoDAO();
		}
		
		return instance;
	}

	public ArrayList<Departamento> listaDepartamentos() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from Departamento order by nombre").list();
		ArrayList<Departamento> res= new ArrayList<Departamento>(list);
		
		return res;
	}
}
