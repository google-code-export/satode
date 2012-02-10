package fing.satode.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.DepositoDTO;
import fing.satode.data.UnidadParcelaDTO;


@Entity
@Table(name="unidadesparcela")
public class UnidadParcela {

	@Id @GeneratedValue
	private Long id;
	
	private String descripcion;
	private Float nivelAgua;
	private int metros2afectados;
	private int nivelPiso;
	 
	public UnidadParcela(){}
	
	public UnidadParcela(UnidadParcelaDTO dto){
		id= dto.getId();
		descripcion = dto.getDescripcion();
		nivelAgua = dto.getNivelAgua();
		metros2afectados = dto.getMetros2afectados();
		nivelPiso = dto.getNivelPiso();
	}
	
	
	public UnidadParcelaDTO getDTO() {
		UnidadParcelaDTO dto= new UnidadParcelaDTO();
		dto.setId(id);
		dto.setDescripcion(descripcion);
		dto.setNivelAgua(nivelAgua);
		dto.setMetros2afectados(metros2afectados);
		dto.setNivelPiso(nivelPiso);
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getNivelAgua() {
		return nivelAgua;
	}

	public void setNivelAgua(Float nivelAgua) {
		this.nivelAgua = nivelAgua;
	}

	public int getMetros2afectados() {
		return metros2afectados;
	}

	public void setMetros2afectados(int metros2afectados) {
		this.metros2afectados = metros2afectados;
	}

	public int getNivelPiso() {
		return nivelPiso;
	}

	public void setNivelPiso(int nivelPiso) {
		this.nivelPiso = nivelPiso;
	}

		
	
	
}
