package fing.satode.bl.basicos;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DepartamentoDTO;
import fing.satode.dominio.Departamento;
import fing.satode.pl.basicos.DepartamentoDAO;

@Transactional
public class BasicosService extends ServiceBase {

	public ArrayList<DepartamentoDTO> listaDepartamentos() {
		ArrayList<Departamento> list=DepartamentoDAO.getInstance().listaDepartamentos();
		ArrayList<DepartamentoDTO> res= new ArrayList<DepartamentoDTO>();
		for(Departamento d: list){
			res.add(d.getDTO());
		}
		return res;
		
	}

	
}
