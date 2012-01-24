package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fing.satode.data.HospitalDTO;
import fing.satode.data.PuntoReferenciaDTO;
import fing.satode.data.TipoPuntoReferencia;

@Entity @Table(name="hospitales")
@PrimaryKeyJoinColumn(name="puntoReferencia_Id")
public class Hospital extends PuntoReferencia {

	private int capacidad;
	private String serviciosEspeciales;
	
	public Hospital(){}
	
	public Hospital(boolean puntoEntrada, boolean puntoEntega, Ciudad ciudad,
			Departamento departamento, String direccion, String telefono,
			int tipo, int capacidad, String serviciosEspeciales) {
		super(puntoEntrada, puntoEntega, ciudad, departamento, direccion,
				telefono, tipo);
		this.capacidad = capacidad;
		this.serviciosEspeciales = serviciosEspeciales;
	}
	
	public Hospital(HospitalDTO dto) {
		super(dto.isPuntoEntrada(), dto.isPuntoEntrega(), new Ciudad(dto.getCiudad()), new Departamento(dto.getDepartamento()), dto.getDireccion(),
				dto.getTelefono(), dto.getTipo());
		this.capacidad = dto.getCapacidad();
		this.serviciosEspeciales = dto.getServiciosEspeciales();
	}
	
	public HospitalDTO getDTO() {
		HospitalDTO dto= new HospitalDTO();
		dto.setId(this.getId());
		dto.setDireccion(this.getDireccion());
		dto.setTelefono(this.getDireccion());
		dto.setPuntoEntrada(this.isPuntoEntrada());
		dto.setPuntoEntrega(this.ispuntoEntrega());
		dto.setTipo(this.getTipo());
		dto.setCiudad(this.getCiudad().getDTO());
		dto.setDepartamento(this.getDepartamento().getDTO());
		dto.setCapacidad(this.getCapacidad());
		dto.setServiciosEspeciales(this.getServiciosEspeciales());
		return dto;
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
