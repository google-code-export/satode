package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.DatosVivienda;

public class TipoParcelaDTO implements Serializable , IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private boolean vivienda;
	
	private String otrosUsos;
	
	private DatosViviendaDTO datosVivienda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isVivienda() {
		return vivienda;
	}

	public void setVivienda(boolean vivienda) {
		this.vivienda = vivienda;
	}

	public String getOtrosUsos() {
		return otrosUsos;
	}

	public void setOtrosUsos(String otrosUsos) {
		this.otrosUsos = otrosUsos;
	}

	public DatosViviendaDTO getDatosVivienda() {
		return datosVivienda;
	}

	public void setDatosVivienda(DatosViviendaDTO datosVivienda) {
		this.datosVivienda = datosVivienda;
	}

	

		
}
