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
@Table(name="gestionesnecesidades")
public class GestionNecesidad {
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="necesidad_id")
	private Necesidad necesidad;
	
	@ManyToOne
    @JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="gestionnecesidad_id")
	private Set<PlanSuministro> planesSuministros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Necesidad getNecesidad() {
		return necesidad;
	}

	public void setNecesidad(Necesidad necesidad) {
		this.necesidad = necesidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<PlanSuministro> getPlanesSuministros() {
		return planesSuministros;
	}

	public void setPlanesSuministros(Set<PlanSuministro> planesSuministros) {
		this.planesSuministros = planesSuministros;
	}
	
	
}
