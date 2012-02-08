package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fing.satode.data.PROtrosDTO;

@Entity @Table(name="otros")
@PrimaryKeyJoinColumn(name="puntoReferencia_Id")
public class PROtros extends PuntoReferencia {

	private String  descripcion;
	
	
	public PROtros(){}
	
	public PROtros(boolean puntoEntrada, boolean puntoEntega, Ciudad ciudad,
			Departamento departamento, String direccion, String telefono,
			int tipo, String  descripcion) {
		super(puntoEntrada, puntoEntega, ciudad, departamento, direccion,
				telefono, tipo);
		this.descripcion = descripcion;
	}
	
	public PROtros(PROtrosDTO dto) {
		super(dto);
		this.descripcion =  dto.getDescripcion();
	}
	
	public PROtrosDTO getDTO() {
		PROtrosDTO dto= new PROtrosDTO();
		dto.setId(this.getId());
		dto.setDireccion(this.getDireccion());
		dto.setTelefono(this.getTelefono());
		dto.setPuntoEntrada(this.isPuntoEntrada());
		dto.setPuntoEntrega(this.ispuntoEntrega());
		dto.setTipo(this.getTipo());
		dto.setCiudad(this.getCiudad().getDTO());
		dto.setDepartamento(this.getDepartamento().getDTO());
		dto.setDescripcion(this.descripcion);
		return dto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	
	
}
