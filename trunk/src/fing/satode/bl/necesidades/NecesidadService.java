package fing.satode.bl.necesidades;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.NecesidadDTO;
import fing.satode.dominio.Necesidad;
import fing.satode.pl.necesidades.NecesidadesDAO;

@Transactional
public class NecesidadService extends ServiceBase {

	public ArrayList<NecesidadDTO> listaNecesidades() {
		// TODO Auto-generated method stub
		ArrayList<NecesidadDTO> listaDTOS= new ArrayList<NecesidadDTO>();
		ArrayList<Necesidad> listaDes= NecesidadesDAO.getInstance().listaNecesidades();
		for(Necesidad d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

	public void nuevoNecesidade(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		Necesidad necesidad= new Necesidad(dto);
		NecesidadesDAO.getInstance().nuevoNecesidad(necesidad);
	}

	public void modificarNecesidad(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		Necesidad necesidad= new Necesidad(dto);
		NecesidadesDAO.getInstance().modificarNecesidad(necesidad);
	}

	public void eliminarNecesidad(NecesidadDTO dto) {
		// TODO Auto-generated method stub
		Necesidad necesidad= new Necesidad(dto);
		NecesidadesDAO.getInstance().eliminarNecesidad(necesidad);
	}

	public ArrayList<NecesidadDTO> buscarNecesidades(Long idDesastre,
			Long idEstado) {
		// TODO Auto-generated method stub
		ArrayList<NecesidadDTO> listaDTOS= new ArrayList<NecesidadDTO>();
		ArrayList<Necesidad> listaDes= NecesidadesDAO.getInstance().buscarNecesidades(idDesastre,idEstado);
		for(Necesidad d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

}
