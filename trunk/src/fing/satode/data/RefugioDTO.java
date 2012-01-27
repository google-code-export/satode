package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public class RefugioDTO extends PuntoReferenciaDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private int capacidad;
	private int banios;
	private boolean techado;
	private int m2ParaCarpas;
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getBanios() {
		return banios;
	}
	public void setBanios(int banios) {
		this.banios = banios;
	}
	public boolean isTechado() {
		return techado;
	}
	public void setTechado(boolean techado) {
		this.techado = techado;
	}
	public int getM2ParaCarpas() {
		return m2ParaCarpas;
	}
	public void setM2ParaCarpas(int m2ParaCarpas) {
		this.m2ParaCarpas = m2ParaCarpas;
	}
	


	
	
}
