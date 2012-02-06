package fing.satode.dominio;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.SuministroDTO;
import fing.satode.data.TipoSuministroDTO;


@Entity
@Table(name="suministros")
public class Suministro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tipoSuministro_id")
	private TipoSuministro tipo;
	 
	private int cantidad;
	private int estado;
	private Date fechaVencimiento;
	
	public Suministro(){};
	
	public Suministro(SuministroDTO dto) {
		id = dto.getId();
		tipo = new TipoSuministro(dto.getTipo());
		cantidad = dto.getCantidad();
		estado = dto.getEstado();
		fechaVencimiento = dto.getFechaVencimiento();
	}
	
	
	
	public SuministroDTO getDTO(){
		SuministroDTO dto = new SuministroDTO();
		dto.setId(id);
		dto.setTipo(tipo.getDTO());
		dto.setCantidad(cantidad);
		dto.setEstado(estado);
		dto.setFechaVencimiento(fechaVencimiento);
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoSuministro getTipo() {
		return tipo;
	}

	public void setTipo(TipoSuministro tipo) {
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
