package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.DepartamentoDTO;

@Entity
@Table(name="departamentos")
public class Departamento {
	
	@Id @GeneratedValue
	private Long id;
	
	private String nombre;

	public Departamento(DepartamentoDTO dto){
		setId(dto.getId());
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
	
	public DepartamentoDTO getDTO(){
		DepartamentoDTO dto=new DepartamentoDTO();
		dto.setId(id);
		dto.setNombre(nombre);
		
		return dto;
	}
	
}
