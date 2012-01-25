package fing.satode.bl.puntoReferencia;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.DepositoDTO;
import fing.satode.data.HospitalDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.TipoPuntoReferencia;
import fing.satode.dominio.Deposito;
import fing.satode.dominio.Hospital;
import fing.satode.dominio.PuntoReferencia;
import fing.satode.pl.deposito.DepositoDAO;
import fing.satode.pl.puntoReferencia.PuntoReferenciaDAO;


@Transactional
public class PuntoReferenciaService extends ServiceBase {

	public ArrayList<PuntoReferenciaDTO> listaPuntosReferencia() {
		
		ArrayList<PuntoReferenciaDTO> listaDTOS= new ArrayList<PuntoReferenciaDTO>();
		ArrayList<PuntoReferencia> listaDes= PuntoReferenciaDAO.getInstance().listaPuntosReferencias();
		for(PuntoReferencia d: listaDes){
			switch (d.getTipo()) 
			{
			case TipoPuntoReferencia.HOSPITAL:
				listaDTOS.add(((Hospital)d).getDTO());
			break;
			}	
			
		}
		return listaDTOS;
	}
	

	public void nuevoPuntoReferencia(PuntoReferenciaDTO dto) {
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().nuevoPuntoReferencia(puntoReferencia);
	}
	
	/*public void nuevoHospital(HospitalDTO dto) {
		Hospital puntoReferencia= new Hospital(dto);
		PuntoReferenciaDAO.getInstance().nuevoHospital(puntoReferencia);
	}*/
	
	public HospitalDTO buscarHospital(long id) {
		return PuntoReferenciaDAO.getInstance().buscarHospital(id).getDTO();
	}

	public void modificarPuntoReferencia(PuntoReferenciaDTO dto) {
		//PuntoReferencia puntoReferencia= new PuntoReferencia(dto);
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().modificarPuntoReferencia(puntoReferencia);
	}

	public void eliminarPuntoReferencia(PuntoReferenciaDTO dto) {
		//PuntoReferencia ``untoReferencia= new PuntoReferencia(dto);
		PuntoReferencia puntoReferencia = null;
		switch (dto.getTipo()) 
		{
		case TipoPuntoReferencia.HOSPITAL:
			puntoReferencia = new Hospital((HospitalDTO)dto);
		break;
		}
		PuntoReferenciaDAO.getInstance().eliminarPuntoReferencia(puntoReferencia);
	}

	


}
