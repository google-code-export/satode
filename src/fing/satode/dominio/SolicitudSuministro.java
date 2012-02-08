package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.SolicitudSuministroDTO;

@Entity
@Table(name="solicitudessuministros")
public class SolicitudSuministro {

	@Id @GeneratedValue
	private Long id;
	
	private int cantidad;
	
	private float costo;
	
	@ManyToOne
	@JoinColumn(name="tipoSuministro_id")
	private TipoSuministro tipoSuministro;

	
	public SolicitudSuministro(){
		
	}
	
	public SolicitudSuministro(SolicitudSuministroDTO dto){
		id= dto.getId();
		cantidad=dto.getCantidad();
		costo=dto.getCosto();
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

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public TipoSuministro getTipoSuministro() {
		return tipoSuministro;
	}

	public void setTipoSuministro(TipoSuministro tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}
	
	public SolicitudSuministroDTO getDTO(){
		SolicitudSuministroDTO dto=new SolicitudSuministroDTO();
		dto.setId(id);
		dto.setCantidad(cantidad);
		dto.setCosto(costo);
		dto.setTipoSuministro(tipoSuministro.getDTO());
		
		return dto;
	}
	
	
	
}
