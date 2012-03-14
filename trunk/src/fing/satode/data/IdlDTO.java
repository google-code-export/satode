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
	private float valorVivindaSocial;
	private float hectariaDeCultivo;
	
	
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
