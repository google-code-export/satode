package fing.satode.dominio;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="solicitudesenvios")
public class SolicitudEnvio {

	@Id @GeneratedValue
	private Long id;
	
	private Date fecha;
	
	private String observacionesEntrega;
	
	private String observacionesEnvio;
	
	private int estado;
	
	@ManyToOne
	@JoinColumn(name="deposito_id")
	private Deposito deposito;
	
	
	@ManyToOne
	@JoinColumn(name="puntoReferencia_id")
	private PuntoReferencia puntoEntrega;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="solicitudenvio_id")
	private Set<SolicitudEnvioSuministro> solicitudesEnvioSuministros;

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

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public PuntoReferencia getPuntoEntrega() {
		return puntoEntrega;
	}

	public void setPuntoEntrega(PuntoReferencia puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	public Set<SolicitudEnvioSuministro> getSolicitudesEnvioSuministros() {
		return solicitudesEnvioSuministros;
	}

	public void setSolicitudesEnvioSuministros(
			Set<SolicitudEnvioSuministro> solicitudesSuministros) {
		this.solicitudesEnvioSuministros = solicitudesSuministros;
	}
	
	
}
