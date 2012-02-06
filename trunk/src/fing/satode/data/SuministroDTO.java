package fing.satode.data;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.TipoSuministro;

public class SuministroDTO implements Serializable, IsSerializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private TipoSuministroDTO tipo;
	private int cantidad;
	private int estado;
	private Date fechaVencimiento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoSuministroDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoSuministroDTO tipo) {
		this.tipo = tipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
}
