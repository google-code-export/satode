package fing.satode.dominio;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.TipoSuministroDTO;


@Entity
@Table(name="tiposuministros")
public class TipoSuministro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean refrigeracion;
	private Boolean fechaVencimiento;
	
	public TipoSuministro(){};
	
	public TipoSuministro(TipoSuministroDTO dto) {
		setId(dto.getId());
		setNombre(dto.getNombre());
		setDescripcion(dto.getDescripcion());
		setRefrigeracion(dto.getRefrigeracion());
		setFechaVencimiento(dto.getFechaVencimiento());
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

	public Boolean getRefrigeracion() {
		return refrigeracion;
	}

	public void setRefrigeracion(Boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}

	public Boolean getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Boolean fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public TipoSuministroDTO getDTO(){
		TipoSuministroDTO dto = new TipoSuministroDTO();
		dto.setId(id);
		dto.setNombre(nombre);
		dto.setDescripcion(descripcion);
		dto.setFechaVencimiento(fechaVencimiento);
		dto.setRefrigeracion(refrigeracion);
		
		return dto;
	}
	  
}
