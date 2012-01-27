package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fing.satode.data.RefugioDTO;




@Entity @Table(name="refugios")
@PrimaryKeyJoinColumn(name="puntoReferencia_Id")
public class Refugio extends PuntoReferencia {

	private int capacidad;
	private int banios;
	private boolean techado;
	private int m2ParaCarpas;
	
	public Refugio(){}
	
	public Refugio(boolean puntoEntrada, boolean puntoEntega, Ciudad ciudad,
			Departamento departamento, String direccion, String telefono,
			int tipo, int capacidad, int banios, boolean techado, int m2paraCarpas) {
		super(puntoEntrada, puntoEntega, ciudad, departamento, direccion,
				telefono, tipo);
		this.capacidad = capacidad;
		this.banios = banios;
		this.techado = techado;
		this.m2ParaCarpas = m2paraCarpas;
	}

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
	
	public Refugio(RefugioDTO dto) {
		super(dto);
		this.capacidad = dto.getCapacidad();
		this.banios = dto.getBanios();
		this.techado = dto.isTechado();
		this.m2ParaCarpas = dto.getM2ParaCarpas();
	}
	
	public RefugioDTO getDTO() {
		RefugioDTO dto= new RefugioDTO();
		dto.setId(this.getId());
		dto.setDireccion(this.getDireccion());
		dto.setTelefono(this.getTelefono());
		dto.setPuntoEntrada(this.isPuntoEntrada());
		dto.setPuntoEntrega(this.ispuntoEntrega());
		dto.setTipo(this.getTipo());
		dto.setCiudad(this.getCiudad().getDTO());
		dto.setDepartamento(this.getDepartamento().getDTO());
		dto.setCapacidad(this.getCapacidad());
		dto.setBanios(this.banios);
		dto.setTechado(this.techado);
		dto.setM2ParaCarpas(this.m2ParaCarpas);
		return dto;
	}

	
	
}
