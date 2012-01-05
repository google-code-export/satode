package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fing.satode.data.TipoPuntoReferencia;

@Entity @Table(name="hospitales")
@PrimaryKeyJoinColumn(name="puntoReferencia_Id")
public class Hospital extends PuntoReferencia {

	private int capacidad;
	private String serviciosEspeciales;
	
	
	
	public Hospital(boolean puntoEntrada, boolean puntoEntega, Ciudad ciudad,
			Departamento departamento, String direccion, String telefono,
			TipoPuntoReferencia tipo, int capacidad, String serviciosEspeciales) {
		super(puntoEntrada, puntoEntega, ciudad, departamento, direccion,
				telefono, tipo);
		this.capacidad = capacidad;
		this.serviciosEspeciales = serviciosEspeciales;
	}
	
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
