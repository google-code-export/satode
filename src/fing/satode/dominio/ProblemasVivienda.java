package fing.satode.dominio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.DepositoDTO;
import fing.satode.data.ProblemasViviendaDTO;


@Entity
@Table(name="problemasviviendas")
public class ProblemasVivienda {

	@Id @GeneratedValue
	private Long id;
	
	private boolean humedadesTecho;
	private boolean goterasTecho;
	private boolean puertasVentanasMalEstado;
	private boolean grietasPiso;
	private boolean caidaRevoques;
	private boolean cielorasoDesprendido;
	private boolean pocaLuzSolar;
	private boolean escazaVentilacion;
	private boolean peligroDerrumbe;
	private boolean instalacionesMalas;
	private boolean manchasParedesTechos;
	private boolean descalceDeCimientos;
	private boolean pozoNegroMalEstado;
	 
	 
	
	public ProblemasVivienda(){}
	
	public ProblemasVivienda(ProblemasViviendaDTO dto){
		id= dto.getId();
		humedadesTecho = dto.isHumedadesTecho();
		goterasTecho= dto.isGoterasTecho();
		puertasVentanasMalEstado= dto.isPuertasVentanasMalEstado();
		grietasPiso= dto.isGrietasPiso();
		caidaRevoques= dto.isCaidaRevoques();
		cielorasoDesprendido= dto.isCielorasoDesprendido();
		pocaLuzSolar= dto.isPocaLuzSolar();
		escazaVentilacion= dto.isEscazaVentilacion();
		peligroDerrumbe= dto.isPeligroDerrumbe();
		instalacionesMalas= dto.isInstalacionesMalas();
		manchasParedesTechos= dto.isManchasParedesTechos();
		descalceDeCimientos= dto.isDescalceDeCimientos();
		pozoNegroMalEstado= dto.isPozoNegroMalEstado();
	
	}



	
	public ProblemasViviendaDTO getDTO() {
		ProblemasViviendaDTO dto= new ProblemasViviendaDTO();
		dto.setId(id);
		dto.setCaidaRevoques(caidaRevoques);
		dto.setCielorasoDesprendido(cielorasoDesprendido);
		dto.setDescalceDeCimientos(descalceDeCimientos);
		dto.setEscazaVentilacion(escazaVentilacion);
		dto.setGoterasTecho(goterasTecho);
		dto.setGrietasPiso(grietasPiso);
		dto.setHumedadesTecho(humedadesTecho);
		dto.setInstalacionesMalas(instalacionesMalas);
		dto.setManchasParedesTechos(manchasParedesTechos);
		dto.setPeligroDerrumbe(peligroDerrumbe);
		dto.setPocaLuzSolar(pocaLuzSolar);
		dto.setPozoNegroMalEstado(pozoNegroMalEstado);
		dto.setPuertasVentanasMalEstado(puertasVentanasMalEstado);
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isHumedadesTecho() {
		return humedadesTecho;
	}

	public void setHumedadesTecho(boolean humedadesTecho) {
		this.humedadesTecho = humedadesTecho;
	}

	public boolean isGoterasTecho() {
		return goterasTecho;
	}

	public void setGoterasTecho(boolean goterasTecho) {
		this.goterasTecho = goterasTecho;
	}

	public boolean isPuertasVentanasMalEstado() {
		return puertasVentanasMalEstado;
	}

	public void setPuertasVentanasMalEstado(boolean puertasVentanasMalEstado) {
		this.puertasVentanasMalEstado = puertasVentanasMalEstado;
	}

	public boolean isGrietasPiso() {
		return grietasPiso;
	}

	public void setGrietasPiso(boolean grietasPiso) {
		this.grietasPiso = grietasPiso;
	}

	public boolean isCaidaRevoques() {
		return caidaRevoques;
	}

	public void setCaidaRevoques(boolean caidaRevoques) {
		this.caidaRevoques = caidaRevoques;
	}

	public boolean isCielorasoDesprendido() {
		return cielorasoDesprendido;
	}

	public void setCielorasoDesprendido(boolean cielorasoDesprendido) {
		this.cielorasoDesprendido = cielorasoDesprendido;
	}

	public boolean isPocaLuzSolar() {
		return pocaLuzSolar;
	}

	public void setPocaLuzSolar(boolean pocaLuzSolar) {
		this.pocaLuzSolar = pocaLuzSolar;
	}

	public boolean isEscazaVentilacion() {
		return escazaVentilacion;
	}

	public void setEscazaVentilacion(boolean escazaVentilacion) {
		this.escazaVentilacion = escazaVentilacion;
	}

	public boolean isPeligroDerrumbe() {
		return peligroDerrumbe;
	}

	public void setPeligroDerrumbe(boolean peligroDerrumbe) {
		this.peligroDerrumbe = peligroDerrumbe;
	}

	public boolean isInstalacionesMalas() {
		return instalacionesMalas;
	}

	public void setInstalacionesMalas(boolean instalacionesMalas) {
		this.instalacionesMalas = instalacionesMalas;
	}

	public boolean isManchasParedesTechos() {
		return manchasParedesTechos;
	}

	public void setManchasParedesTechos(boolean manchasParedesTechos) {
		this.manchasParedesTechos = manchasParedesTechos;
	}

	public boolean isDescalceDeCimientos() {
		return descalceDeCimientos;
	}

	public void setDescalceDeCimientos(boolean descalceDeCimientos) {
		this.descalceDeCimientos = descalceDeCimientos;
	}

	public boolean isPozoNegroMalEstado() {
		return pozoNegroMalEstado;
	}

	public void setPozoNegroMalEstado(boolean pozoNegroMalEstado) {
		this.pozoNegroMalEstado = pozoNegroMalEstado;
	}

	
	
	
}
