package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IdlDepartamentoDTO implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private DepartamentoDTO departamento;
	
	private float porcentaje;
	
	private ArrayList<IdlTipoEventoDTO> tiposEventos;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public ArrayList<IdlTipoEventoDTO> getTiposEventos() {
		return tiposEventos;
	}

	public void setTiposEventos(ArrayList<IdlTipoEventoDTO> tiposEventos) {
		this.tiposEventos = tiposEventos;
	}


}
