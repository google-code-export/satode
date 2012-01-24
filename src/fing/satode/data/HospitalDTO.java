package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public class HospitalDTO extends PuntoReferenciaDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private int capacidad;
	private String serviciosEspeciales;
	


	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public String getServiciosEspeciales() {
		return serviciosEspeciales;
	}
	public void setServiciosEspeciales(String serviciosEspeciales) {
		this.serviciosEspeciales = serviciosEspeciales;
	}
	
}
