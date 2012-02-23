package fing.satode.data;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CostoDTO implements Serializable , IsSerializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date fecha;
	
	private String observaciones;
	
	private DesastreDTO desastre;
	
	private TipoCostoDTO tipocosto;

	private float cantidadPesos;
	
	private float cantidadDolares;
	
	

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

	public DesastreDTO getDesastre() {
		return desastre;
	}

	public void setDesastre(DesastreDTO desastre) {
		this.desastre = desastre;
	}

	public TipoCostoDTO getTipocosto() {
		return tipocosto;
	}

	public void setTipocosto(TipoCostoDTO tipocosto) {
		this.tipocosto = tipocosto;
	}

	
	
}
