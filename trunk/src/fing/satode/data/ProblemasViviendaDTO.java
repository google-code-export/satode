package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProblemasViviendaDTO implements Serializable , IsSerializable{

	private static final long serialVersionUID = 1L;

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
