package fing.satode.dominio;

import java.util.ArrayList;
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

import com.google.gwt.dev.util.collect.HashSet;

import fing.satode.data.IdlDepartamentoDTO;
import fing.satode.data.IdlTipoEventoDTO;

@Entity
@Table(name="idldepartamento")
public class IdlDepartamento {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	private float porcentaje;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
			)
	@JoinColumn(name="idldepartamento_id")
	private Set<IdlTipoEvento> tiposEventos;

	public IdlDepartamento(){}
	
	public IdlDepartamento(IdlDepartamentoDTO dto)
	{
		id=dto.getId();
		porcentaje=dto.getPorcentaje();
		setDepartamento(new Departamento(dto.getDepartamento()));
		tiposEventos=new HashSet<IdlTipoEvento>();
		for(IdlTipoEventoDTO e:dto.getTiposEventos())
		{
			tiposEventos.add(new IdlTipoEvento(e));
		}
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Set<IdlTipoEvento> getTiposEventos() {
		return tiposEventos;
	}

	public void setTiposEventos(Set<IdlTipoEvento> tiposEventos) {
		this.tiposEventos = tiposEventos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public IdlDepartamentoDTO getDTO()
	{
		IdlDepartamentoDTO dto=new IdlDepartamentoDTO();
		dto.setId(id);
		dto.setPorcentaje(porcentaje);
		dto.setDepartamento(departamento.getDTO());
		ArrayList<IdlTipoEventoDTO> eventos=new ArrayList<IdlTipoEventoDTO>();
		for(IdlTipoEvento e:tiposEventos)
		{
			eventos.add(e.getDTO());
		}
		dto.setTiposEventos(eventos);
		
		return dto;
	}

}
