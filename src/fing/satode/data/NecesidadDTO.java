package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.user.client.rpc.IsSerializable;


public class NecesidadDTO  implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private boolean recursosLocales;
	
	private String descripcion;
	
	private Date fecha;
	
	private UsuarioDTO usuarioCreador;
	
	private int estado=1;
	
	private DesastreDTO desastre;
	
	
	private ArrayList<SolicitudSuministroDTO> solicitudesSuministros;

	private PuntoReferenciaDTO puntoEntrega;
	
	public NecesidadDTO(){

		solicitudesSuministros= new ArrayList<SolicitudSuministroDTO>();
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

	public UsuarioDTO getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(UsuarioDTO usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public ArrayList<SolicitudSuministroDTO> getSolicitudesSuministros() {
		return solicitudesSuministros;
	}

	public void setSolicitudesSuministros(
			ArrayList<SolicitudSuministroDTO> solicitudesSuministros) {
		this.solicitudesSuministros = solicitudesSuministros;
	}

	public DesastreDTO getDesastre() {
		return desastre;
	}

	public void setDesastre(DesastreDTO desastre) {
		this.desastre = desastre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public PuntoReferenciaDTO getPuntoEntrega() {
		return puntoEntrega;
	}

	public void setPuntoEntrega(PuntoReferenciaDTO puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	
}
