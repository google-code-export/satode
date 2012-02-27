package fing.satode.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fing.satode.data.TipoEventoDTO;
import fing.satode.data.TipoParcelaDTO;

@Entity
@Table(name="tiposparcelas")
public class TipoParcela implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private boolean vivienda;
	
	private String otrosUsos;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="datosvivienda_id")
	private DatosVivienda datosVivienda;
	
	public TipoParcela(){}
	
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

	public DatosVivienda getDatosVivienda() {
		return datosVivienda;
	}

	public void setDatosVivienda(DatosVivienda datosVivienda) {
		this.datosVivienda = datosVivienda;
	}

	public TipoParcela(TipoParcelaDTO dto){
		id = dto.getId();
		vivienda = dto.isVivienda();
		otrosUsos= dto.getOtrosUsos();
		datosVivienda = new DatosVivienda(dto.getDatosVivienda());
	}
	
		
	public TipoParcelaDTO getDTO(){
		TipoParcelaDTO dto= new TipoParcelaDTO();
		dto.setId(id);
		dto.setVivienda(vivienda);
		dto.setOtrosUsos(otrosUsos);
		dto.setDatosVivienda(datosVivienda.getDTO());
		
		return dto;
		
	}
	
}
