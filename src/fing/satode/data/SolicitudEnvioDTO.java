package fing.satode.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.Deposito;


public class SolicitudEnvioDTO implements Serializable, IsSerializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date fecha;
	
	private String observacionesEntrega;
	
	private String observacionesEnvio;
	
	private int estado;
	
	private DepositoDTO deposito;
	
	
	private PuntoReferenciaDTO puntoEntrega;
	
	private Set<SolicitudEnvioSuministroDTO> solicitudesEnvioSuministros= new HashSet<SolicitudEnvioSuministroDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacionesEntrega() {
		return observacionesEntrega;
	}

	public void setObservacionesEntrega(String observacionesEntrega) {
		this.observacionesEntrega = observacionesEntrega;
	}

	public String getObservacionesEnvio() {
		return observacionesEnvio;
	}

	public void setObservacionesEnvio(String observacionesEnvio) {
		this.observacionesEnvio = observacionesEnvio;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public DepositoDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}

	public PuntoReferenciaDTO getPuntoEntrega() {
		return puntoEntrega;
	}

	public void setPuntoEntrega(PuntoReferenciaDTO puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	public Set<SolicitudEnvioSuministroDTO> getSolicitudesEnvioSuministros() {
		return solicitudesEnvioSuministros;
	}

	public void setSolicitudesEnvioSuministros(
			Set<SolicitudEnvioSuministroDTO> solicitudesEnvioSuministros) {
		this.solicitudesEnvioSuministros = solicitudesEnvioSuministros;
	}

	
	
}
