package fing.satode.data;

import java.io.Serializable;
import java.util.Date;


import com.google.gwt.user.client.rpc.IsSerializable;


public class CalculoIndiceDTO  implements Serializable, IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fecha;
	private float valor;
	private float valorVivindaSocial;
	private float hectariaDeCultivo;
	private String observaciones;
	private UsuarioDTO usuario;
	
	private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public float getValorVivindaSocial() {
		return valorVivindaSocial;
	}

	public void setValorVivindaSocial(float valorVivindaSocial) {
		this.valorVivindaSocial = valorVivindaSocial;
	}

	public float getHectariaDeCultivo() {
		return hectariaDeCultivo;
	}

	public void setHectariaDeCultivo(float hectariaDeCultivo) {
		this.hectariaDeCultivo = hectariaDeCultivo;
	}
	
	
}
