package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public class PoliciaDTO extends PuntoReferenciaDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private int cantidadFuncionarios;
	private int cantidadVehiculos;
	
	public int getCantidadFuncionarios() {
		return cantidadFuncionarios;
	}
	public void setCantidadFuncionarios(int cantidadFuncionarios) {
		this.cantidadFuncionarios = cantidadFuncionarios;
	}
	public int getCantidadVehiculos() {
		return cantidadVehiculos;
	}
	public void setCantidadVehiculos(int cantidadVehiculos) {
		this.cantidadVehiculos = cantidadVehiculos;
	}
	
	
	
}
