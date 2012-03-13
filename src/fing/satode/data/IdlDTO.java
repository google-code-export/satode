package fing.satode.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;



public class IdlDTO extends CalculoIndiceDTO implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	private Date fechaInicio;
	private Date fechaFino;
	private Set<IdlDepartamentoDTO> departamentos;
	
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFino() {
		return fechaFino;
	}
	public void setFechaFino(Date fechaFino) {
		this.fechaFino = fechaFino;
	}
	public Set<IdlDepartamentoDTO> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(Set<IdlDepartamentoDTO> departamentos) {
		this.departamentos = departamentos;
	}

	
}
