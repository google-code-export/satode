package fing.satode.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.CostoDTO;

@Entity
@Table(name="costos")
public class Costo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	
	private Date fecha;
	
	private String observaciones;
	
	@ManyToOne
	@JoinColumn(name="desastre_id")
	private Desastre desastre;
	
	@ManyToOne
	@JoinColumn(name="tipocosto_id")
	private TipoCosto tipocosto;

	private float cantidadPesos;
	
	private float cantidadDolares;
	
	public Costo(){}
	
	

	public Costo(CostoDTO dto){
		id= dto.getId();
		fecha=dto.getFecha();
		observaciones=dto.getObservaciones();
		desastre=new Desastre(dto.getDesastre());
		tipocosto=new TipoCosto(dto.getTipocosto());
		cantidadPesos = dto.getCantidadPesos();
		cantidadDolares=dto.getCantidadDolares();
		
	}
	
	public float getCantidadPesos() {
		return cantidadPesos;
	}

	public void setCantidadPesos(float cantidadPesos) {
		this.cantidadPesos = cantidadPesos;
	}

	public float getCantidadDolares() {
		return cantidadDolares;
	}

	public void setCantidadDolares(float cantidadDolares) {
		this.cantidadDolares = cantidadDolares;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Desastre getDesastre() {
		return desastre;
	}

	public void setDesastre(Desastre desastre) {
		this.desastre = desastre;
	}

	public TipoCosto getTipocosto() {
		return tipocosto;
	}

	public void setTipocosto(TipoCosto tipocosto) {
		this.tipocosto = tipocosto;
	}
	
	public CostoDTO getDTO(){
		CostoDTO dto= new CostoDTO();
		dto.setId(id);
		dto.setFecha(fecha);
		dto.setDesastre(desastre.getDTO());
		dto.setObservaciones(observaciones);
		dto.setTipocosto(tipocosto.getDTO());
		dto.setCantidadDolares(cantidadDolares);
		dto.setCantidadPesos(cantidadPesos);
		
		return dto;
	}
	
}
