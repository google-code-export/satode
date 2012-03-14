package fing.satode.dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import fing.satode.data.CalculoIndiceDTO;
import fing.satode.data.IdlDTO;
import fing.satode.data.IdlDepartamentoDTO;

@Entity @Table(name="indice_idl")
@PrimaryKeyJoinColumn(name="calculoIndice_Id")
public class IDL extends CalculoIndice {

	private static final long serialVersionUID = 1L;
	
	private Date fechaInicio;
	private Date fechaFino;
	private float valorVivindaSocial;
	private float hectariaDeCultivo;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade(
			value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
			)
	@JoinColumn(name="idl_id")
	private Set<IdlDepartamento> departamentos;

	public IDL(){super();}
		
	public IDL(IdlDTO dto)
	{
		super(dto);
		fechaInicio=dto.getFechaInicio();
		fechaFino=dto.getFechaFino();
		departamentos=new HashSet<IdlDepartamento>();
		for(IdlDepartamentoDTO d:dto.getDepartamentos())
		{
			departamentos.add(new IdlDepartamento(d));
		}
		valorVivindaSocial=dto.getValorVivindaSocial();
		hectariaDeCultivo=dto.getHectariaDeCultivo();
		
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

	public Set<IdlDepartamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<IdlDepartamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	public IdlDTO getDTO()
	{
		IdlDTO dto=new IdlDTO();
		dto.setId(getId());
		dto.setFecha(getFecha());
		dto.setObservaciones(getObservaciones());
		dto.setUsuario(getUsuario().getDTO());
		dto.setValor(getValor());
		dto.setTipo(getTipo());
		dto.setValorVivindaSocial(valorVivindaSocial);
		dto.setHectariaDeCultivo(hectariaDeCultivo);
		
		dto.setFechaFino(fechaFino);
		dto.setFechaInicio(fechaInicio);
		Set<IdlDepartamentoDTO> dtos=new HashSet<IdlDepartamentoDTO>();
		for(IdlDepartamento d:departamentos)
		{
			dtos.add(d.getDTO());
		}
		
		dto.setDepartamentos(dtos);
		
		return dto;
	}
	
	public CalculoIndiceDTO getDTOSimple() {
		IdlDTO dto=new IdlDTO();
		dto.setId(getId());
		dto.setFecha(getFecha());
		dto.setObservaciones(getObservaciones());
		dto.setUsuario(getUsuario().getDTO());
		dto.setValor(getValor());
		dto.setTipo(getTipo());
		dto.setValorVivindaSocial(valorVivindaSocial);
		dto.setHectariaDeCultivo(hectariaDeCultivo);
		
		dto.setFechaFino(fechaFino);
		dto.setFechaInicio(fechaInicio);
		Set<IdlDepartamentoDTO> dtos=new HashSet<IdlDepartamentoDTO>();
		dto.setDepartamentos(dtos);
		return dto;
	}
}
