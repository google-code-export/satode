package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TipoSuministroDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean refrigeracion;
	private Boolean fechaVencimiento;
	
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
	
	
}
