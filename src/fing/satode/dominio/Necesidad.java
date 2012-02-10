package fing.satode.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fing.satode.data.NecesidadDTO;
import fing.satode.data.SolicitudSuministroDTO;

@Entity
@Table(name="necesidades")
public class Necesidad {

	@Id @GeneratedValue
	private Long id;
	
	private boolean recursosLocales;
	
	private Date fecha;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="usuarioCreador_id")
	private Usuario usuarioCreador;
	
	@ManyToOne
	@JoinColumn(name="desastre_id")
	private Desastre desastre;
	
	@ManyToOne
	@JoinColumn(name="puntoEntrega_id")
	private PuntoReferencia puntoEntrega;
	
	private int estado;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="necesidad_id")
	private Collection<SolicitudSuministro> solicitudesSuministros;

	
	public Necesidad(){
		
	}
	
	public Necesidad(NecesidadDTO dto){
		id=dto.getId();
		recursosLocales=dto.isRecursosLocales();
		descripcion=dto.getDescripcion();
		usuarioCreador=new Usuario(dto.getUsuarioCreador());
		estado= dto.getEstado();
		solicitudesSuministros= new ArrayList<SolicitudSuministro>();
		for(SolicitudSuministroDTO sdto:dto.getSolicitudesSuministros()){
			solicitudesSuministros.add(new SolicitudSuministro(sdto));
		}
		desastre= new Desastre(dto.getDesastre());
		fecha=dto.getFecha();
		puntoEntrega= Util.crearPuntoReferencia(dto.getPuntoEntrega());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRecursosLocales() {
		return recursosLocales;
	}

	public void setRecursosLocales(boolean recursosLocales) {
		this.recursosLocales = recursosLocales;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Collection<SolicitudSuministro> getSolicitudesSuministros() {
		return solicitudesSuministros;
	}

	public void setSolicitudesSuministros(
			Collection<SolicitudSuministro> solicitudesSuministros) {
		this.solicitudesSuministros = solicitudesSuministros;
	}
	
	public Desastre getDesastre() {
		return desastre;
	}

	public void setDesastre(Desastre desastre) {
		this.desastre = desastre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public PuntoReferencia getPuntoEntrega() {
		return puntoEntrega;
	}

	public void setPuntoEntrega(PuntoReferencia puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	public NecesidadDTO getDTO(){
		NecesidadDTO dto= new NecesidadDTO();
		dto.setId(id);
		dto.setRecursosLocales(recursosLocales);
		dto.setDescripcion(descripcion);
		dto.setUsuarioCreador(usuarioCreador.getDTO());
		dto.setEstado(estado);
		ArrayList<SolicitudSuministroDTO> lista= new ArrayList<SolicitudSuministroDTO>();
		for(SolicitudSuministro s:solicitudesSuministros){
			lista.add(s.getDTO());
		}
		
		dto.setSolicitudesSuministros(lista);
		dto.setDesastre(desastre.getDTO());
		dto.setFecha(fecha);
		dto.setPuntoEntrega(Util.crearPuntoReferenciaDTO(puntoEntrega));
		
		return dto;
	}
	
}
