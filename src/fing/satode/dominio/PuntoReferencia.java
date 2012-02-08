package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.PuntoReferenciaDTO;

@Entity @Table(name="puntosreferencia")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class PuntoReferencia {

	@Id @GeneratedValue
	private Long id;
	
	private boolean puntoEntrada;
	
	private boolean puntoEntrega;
	
	 @ManyToOne
	 @JoinColumn(name="ciudad_id")
	private Ciudad ciudad;
	 
	@ManyToOne
    @JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	private String direccion;
	
	private String telefono;
	
	private int tipo;
	
	public PuntoReferencia(){};

	
	public PuntoReferencia(boolean puntoEntrada, boolean puntoEntrega,
			Ciudad ciudad, Departamento departamento, String direccion,
			String telefono, int tipo) {
		this.puntoEntrada = puntoEntrada;
		this.puntoEntrega = puntoEntrega;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public PuntoReferencia(PuntoReferenciaDTO dto) {
		id= dto.getId();
		puntoEntrada = dto.isPuntoEntrada();
		puntoEntrega = dto.isPuntoEntrega();
		ciudad = new Ciudad(dto.getCiudad());
		departamento = new Departamento(dto.getDepartamento());
		direccion = dto.getDireccion();
		telefono = dto.getTelefono();
		tipo = dto.getTipo();
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPuntoEntrada() {
		return puntoEntrada;
	}

	public void setPuntoEntrada(boolean puntoEntrada) {
		this.puntoEntrada = puntoEntrada;
	}

	public boolean ispuntoEntrega() {
		return puntoEntrega;
	}

	public void setpuntoEntrega(boolean puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public abstract PuntoReferenciaDTO getDTO();
	
	

	
}
