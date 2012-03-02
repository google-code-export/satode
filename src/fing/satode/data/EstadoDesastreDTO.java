package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoDesastreDTO  implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<NecesidadDTO> necesidadesLocales;
	private ArrayList<NecesidadDTO> necesidadesRechazadas;
	private ArrayList<NecesidadDTO> necesidadesPendientes;
	private ArrayList<NecesidadDTO> necesidadesAprobadas;
	
	private ArrayList<SolicitudEnvioDTO> solicitudPendiente;
	private ArrayList<SolicitudEnvioDTO> solicitudEnvioRecibidas;
	private ArrayList<SolicitudEnvioDTO> solicitudEnviadasNoRecibidas;
	
	private ArrayList<CostoDTO> costos;

	public ArrayList<NecesidadDTO> getNecesidadesRechazadas() {
		return necesidadesRechazadas;
	}

	public void setNecesidadesRechazadas(
			ArrayList<NecesidadDTO> necesidadesRechazadas) {
		this.necesidadesRechazadas = necesidadesRechazadas;
	}

	public ArrayList<NecesidadDTO> getNecesidadesAprobadas() {
		return necesidadesAprobadas;
	}

	public void setNecesidadesAprobadas(ArrayList<NecesidadDTO> necesidadesAprobadas) {
		this.necesidadesAprobadas = necesidadesAprobadas;
	}

	public ArrayList<NecesidadDTO> getNecesidadesLocales() {
		return necesidadesLocales;
	}

	public void setNecesidadesLocales(ArrayList<NecesidadDTO> necesidadesLocales) {
		this.necesidadesLocales = necesidadesLocales;
	}

	
	public ArrayList<NecesidadDTO> getNecesidadesPendientes() {
		return necesidadesPendientes;
	}

	public void setNecesidadesPendientes(
			ArrayList<NecesidadDTO> necesidadesPendientes) {
		this.necesidadesPendientes = necesidadesPendientes;
	}

	public ArrayList<SolicitudEnvioDTO> getSolicitudPendiente() {
		return solicitudPendiente;
	}

	public void setSolicitudPendiente(
			ArrayList<SolicitudEnvioDTO> solicitudPendiente) {
		this.solicitudPendiente = solicitudPendiente;
	}

	public ArrayList<SolicitudEnvioDTO> getSolicitudEnvioRecibidas() {
		return solicitudEnvioRecibidas;
	}

	public void setSolicitudEnvioRecibidas(
			ArrayList<SolicitudEnvioDTO> solicitudEnvioRecibidas) {
		this.solicitudEnvioRecibidas = solicitudEnvioRecibidas;
	}

	public ArrayList<SolicitudEnvioDTO> getSolicitudEnviadasNoRecibidas() {
		return solicitudEnviadasNoRecibidas;
	}

	public void setSolicitudEnviadasNoRecibidas(
			ArrayList<SolicitudEnvioDTO> solicitudEnviadasNoRecibidas) {
		this.solicitudEnviadasNoRecibidas = solicitudEnviadasNoRecibidas;
	}

	public ArrayList<CostoDTO> getCostos() {
		return costos;
	}

	public void setCostos(ArrayList<CostoDTO> costos) {
		this.costos = costos;
	}
	

}
