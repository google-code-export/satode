package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fing.satode.data.PoliciaDTO;



@Entity @Table(name="policias")
@PrimaryKeyJoinColumn(name="puntoReferencia_Id")
public class Policia extends PuntoReferencia {

	private int cantidadFuncionarios;
	private int cantidadVehiculos;
	
	public Policia(){}
	
	public Policia(boolean puntoEntrada, boolean puntoEntega, Ciudad ciudad,
			Departamento departamento, String direccion, String telefono,
			int tipo, int cantidadFuncionarios, int cantidadVehiculos) {
		super(puntoEntrada, puntoEntega, ciudad, departamento, direccion,
				telefono, tipo);
		this.cantidadFuncionarios = cantidadFuncionarios;
		this.cantidadVehiculos = cantidadVehiculos;
	}
	
	public Policia(PoliciaDTO dto) {
		super(dto);
		this.cantidadFuncionarios = dto.getCantidadFuncionarios();
		this.cantidadVehiculos = dto.getCantidadVehiculos();
	}
	
	public PoliciaDTO getDTO() {
		PoliciaDTO dto= new PoliciaDTO();
		dto.setId(this.getId());
		dto.setDireccion(this.getDireccion());
		dto.setTelefono(this.getTelefono());
		dto.setPuntoEntrada(this.isPuntoEntrada());
		dto.setPuntoEntrega(this.ispuntoEntrega());
		dto.setTipo(this.getTipo());
		dto.setCiudad(this.getCiudad().getDTO());
		dto.setDepartamento(this.getDepartamento().getDTO());
		dto.setCantidadFuncionarios(this.cantidadFuncionarios);
		dto.setCantidadVehiculos(this.cantidadVehiculos);
		return dto;
	}

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
