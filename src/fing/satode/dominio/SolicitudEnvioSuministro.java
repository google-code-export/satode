package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.SolicitudEnvioSuministroDTO;
import fing.satode.data.SolicitudSuministroDTO;

@Entity
@Table(name="solicitudesenviossuministros")
public class SolicitudEnvioSuministro {

	@Id @GeneratedValue
	private Long id;
	
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="tipoSuministro_id")
	private TipoSuministro tipoSuministro;

	
	public SolicitudEnvioSuministro(){
		
	}
	
	
	public SolicitudEnvioSuministro(SolicitudEnvioSuministroDTO dto) {
		// TODO Auto-generated constructor stub
		id= dto.getId();
		cantidad=dto.getCantidad();
		tipoSuministro= new TipoSuministro(dto.getTipoSuministro());
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	public TipoSuministro getTipoSuministro() {
		return tipoSuministro;
	}

	public void setTipoSuministro(TipoSuministro tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}

	
	public SolicitudEnvioSuministroDTO getDTO(){
		SolicitudEnvioSuministroDTO dto=new SolicitudEnvioSuministroDTO();
		dto.setId(id);
		dto.setCantidad(cantidad);
		dto.setTipoSuministro(tipoSuministro.getDTO());
		return dto;
	}

	
	
	
}
