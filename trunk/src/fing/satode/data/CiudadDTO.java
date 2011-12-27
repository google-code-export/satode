package fing.satode.data;

import java.io.Serializable;


public class CiudadDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private DepartamentoDTO departamento;
	
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
	public DepartamentoDTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

	
}
