package fing.satode.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.CalculoIndiceDTO;

@Entity
@Table(name="calculosdeindices")
public class CalculoIndice implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue
	private Long id;
	private Date fecha;
	private float valor;
	private String observaciones;
	private int tipo;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public CalculoIndice(){}
	
	public CalculoIndice(CalculoIndiceDTO dto){
		id=dto.getId();
		fecha=dto.getFecha();
		valor=dto.getValor();
		usuario=new Usuario(dto.getUsuario());
		observaciones=dto.getObservaciones();
		tipo=dto.getTipo();
	}
	
	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public CalculoIndiceDTO getDTO(){
		CalculoIndiceDTO dto=new CalculoIndiceDTO();
		dto.setId(id);
		dto.setFecha(fecha);
		dto.setObservaciones(observaciones);
		dto.setUsuario(usuario.getDTO());
		dto.setValor(valor);
		dto.setTipo(tipo);
		
		return dto;
	}
	
	
}
