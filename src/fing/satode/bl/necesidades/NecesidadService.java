package fing.satode.bl.necesidades;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.GestionNecesidadDTO;
import fing.satode.data.NecesidadDTO;
import fing.satode.dominio.GestionNecesidad;
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

	public GestionNecesidadDTO buscarGestionNecesidadPorNecesidad(Long idNecesidad) {
		// TODO Auto-generated method stub
		GestionNecesidad gesnec= NecesidadesDAO.getInstance().buscarGestionNecesidadPorNecesidad(idNecesidad);
		if(gesnec!=null){
			return gesnec.getDTO();
		}else{
			return null;
		}
	}

	public void nuevoGestionNecesidad(GestionNecesidadDTO dto) {
		GestionNecesidad necesidad= new GestionNecesidad(dto);
		NecesidadesDAO.getInstance().nuevoGestionNecesidad(necesidad);
		dto.setId(necesidad.getId());
	}
	
	public void modificarGestionNecesidad(GestionNecesidadDTO dto) {
		GestionNecesidad necesidad= new GestionNecesidad(dto);
		NecesidadesDAO.getInstance().modificarGestionNecesidad(necesidad);
	}

	public ArrayList<NecesidadDTO> buscarNecesidades(Long idDesastre,
			Long idEstado, boolean recursosLocales) {
		ArrayList<NecesidadDTO> listaDTOS= new ArrayList<NecesidadDTO>();
		ArrayList<Necesidad> listaDes= NecesidadesDAO.getInstance().buscarNecesidades(idDesastre,idEstado,recursosLocales);
		for(Necesidad d: listaDes){
			listaDTOS.add(d.getDTO());
		}
		return listaDTOS;
	}

}
