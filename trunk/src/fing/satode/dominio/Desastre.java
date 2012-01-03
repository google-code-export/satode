package fing.satode.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.DesastreDTO;

@Entity
@Table(name="desastres")
public class Desastre {

	@Id @GeneratedValue
	private Long id;
	
	private Date fechaDeclaracion;
	
	 @ManyToOne
	 @JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	 @ManyToOne
	 @JoinColumn(name="evento_id")
	private Evento evento;
	 
	
	public Desastre(){}
	
	public Desastre(DesastreDTO dto){
		id= dto.getId();
		usuario = new Usuario(dto.getUsuario());
		evento= new Evento(dto.getEvento());
		fechaDeclaracion= dto.getFechaDeclaracion();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDeclaracion() {
		return fechaDeclaracion;
	}

	public void setFechaDeclaracion(Date fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public DesastreDTO getDTO() {
		DesastreDTO dto= new DesastreDTO();
		dto.setId(id);
		dto.setUsuario(usuario.getDTO());
		dto.setEvento(evento.getDTO());
		dto.setFechaDeclaracion(fechaDeclaracion);
		return dto;
	}
	
	
	
}
