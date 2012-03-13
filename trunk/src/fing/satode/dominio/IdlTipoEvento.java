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

import fing.satode.data.EventoDTO;
import fing.satode.data.IdlTipoEventoDTO;

@Entity
@Table(name="idltipoevento")
public class IdlTipoEvento {
	
	@Id @GeneratedValue
	private Long id;
	
	private float pocentaje;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
			)
	@JoinColumn(name="idltipoevento_id")
	private Set<IdlEvento> eventos;
	
	@ManyToOne
    @JoinColumn(name="tipoevento_id")
	private TipoEvento tipoEvento;

	public IdlTipoEvento(){}
	
	public IdlTipoEvento(IdlTipoEventoDTO dto){
		id=dto.getId();
		pocentaje=dto.getPocentaje();
		eventos= new HashSet<IdlEvento>();
		for(EventoDTO e: dto.getEventos()){
			eventos.add(new IdlEvento(e));
		}
		tipoEvento=new TipoEvento(dto.getTipoEvento());
	}
	
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPocentaje() {
		return pocentaje;
	}

	public void setPocentaje(float pocentaje) {
		this.pocentaje = pocentaje;
	}

	public Set<IdlEvento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<IdlEvento> eventos) {
		this.eventos = eventos;
	}
	
	public IdlTipoEventoDTO getDTO(){
		IdlTipoEventoDTO dto=new IdlTipoEventoDTO();
		dto.setId(id);
		dto.setPocentaje(pocentaje);
		ArrayList<EventoDTO> eventosDTOs=new ArrayList<EventoDTO>();
		for(IdlEvento e: eventos){
			eventosDTOs.add(e.getDTO());
		}
		dto.setEventos(eventosDTOs);
		dto.setTipoEvento(tipoEvento.getDTO());
		return dto;
	}
}
