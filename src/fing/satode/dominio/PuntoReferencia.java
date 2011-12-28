package fing.satode.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="puntoReferencia")
public class PuntoReferencia implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private boolean puntoEntrada;
	private boolean puntoSalida;
	
	@ManyToOne
    @JoinColumn(name="ciudad_id")
	private Ciudad ciudad;
	
	private String direccion;
	private String telefono;
	private int tipo;
	
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
	public boolean isPuntoSalida() {
		return puntoSalida;
	}
	public void setPuntoSalida(boolean puntoSalida) {
		this.puntoSalida = puntoSalida;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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

}
