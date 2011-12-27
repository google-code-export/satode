package fing.satode.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.TipoEventoDTO;

@Entity
@Table(name="tipoeventos")
public class TipoEvento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;
	
	@Column(length=1000)
	private String descripcion;

	public TipoEvento(TipoEventoDTO dto){
		setId(dto.getId());
		setDescripcion(dto.getDescripcion());
		setNombre(dto.getNombre());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoEventoDTO getDTO(){
		TipoEventoDTO dto= new TipoEventoDTO();
		dto.setId(id);
		dto.setDescripcion(descripcion);
		dto.setNombre(nombre);
		
		return dto;
		
	}
	
}
