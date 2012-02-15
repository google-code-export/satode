package fing.satode.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PlanSuministroDTO implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private SolicitudSuministroDTO solicitudsuministro;
	
	private Set<SolicitudEnvioDTO> solicitudesEnvios= new HashSet<SolicitudEnvioDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolicitudSuministroDTO getSolicitudsuministro() {
		return solicitudsuministro;
	}

	public void setSolicitudsuministro(SolicitudSuministroDTO solicitudsuministro) {
		this.solicitudsuministro = solicitudsuministro;
	}

	public Set<SolicitudEnvioDTO> getSolicitudesEnvios() {
		return solicitudesEnvios;
	}

	public void setSolicitudesEnvios(Set<SolicitudEnvioDTO> solicitudesEnvios) {
		this.solicitudesEnvios = solicitudesEnvios;
	}
	
	
}
