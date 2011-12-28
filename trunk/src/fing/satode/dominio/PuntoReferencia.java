package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity @Table(name="puntosreferencia")
@Inheritance(strategy=InheritanceType.JOINED)
public class PuntoReferencia {

	@Id @GeneratedValue
	private Long id;
	
	private boolean puntoEntrada;

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

	
}
