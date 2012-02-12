package fing.satode.dominio;

import java.io.Serializable;

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
	
	@OneToOne
    @JoinColumn(name="datosvivienda_id")
	private DatosVivienda datosVivienda;
	
	public TipoParcela(){}
	
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
