package fing.satode.dominio;

import java.util.Collection;
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
@Table(name="palessuministros")
public class PlanSuministro {

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="solicitudsuministro_id")
	private SolicitudSuministro solicitudsuministro;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="plansuministro_id")
	private Set<SolicitudEnvio> solicitudesEnvios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolicitudSuministro getSolicitudsuministro() {
		return solicitudsuministro;
	}

	public void setSolicitudsuministro(SolicitudSuministro solicitudsuministro) {
		this.solicitudsuministro = solicitudsuministro;
	}

	public Set<SolicitudEnvio> getSolicitudesEnvios() {
		return solicitudesEnvios;
	}

	public void setSolicitudesEnvios(Set<SolicitudEnvio> solicitudesEnvios) {
		this.solicitudesEnvios = solicitudesEnvios;
	}
	
	
}
