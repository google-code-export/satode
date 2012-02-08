package fing.satode.data;

import java.io.Serializable;


import com.google.gwt.user.client.rpc.IsSerializable;


public class SolicitudSuministroDTO   implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private int cantidad;
	
	private float costo;
	
	private TipoSuministroDTO tipoSuministro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public TipoSuministroDTO getTipoSuministro() {
		return tipoSuministro;
	}

	public void setTipoSuministro(TipoSuministroDTO tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}

	
}
