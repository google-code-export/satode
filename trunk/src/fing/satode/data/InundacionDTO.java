package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class InundacionDTO implements Serializable , IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private boolean inundadaAnteriormente;
	private String anios;
	private int antiguedadEnCasa;
	private boolean irsePorInundacion;
	private String observacionesNoIrse;
	private boolean abandonoViviendaPorInund;
	private String obsalojamientoEnInundacion;
	private int cntDiasFuera;
	private Long alturaAgua;
	private String obsAguaVivienda;
	
	private int alojamientoInundacion;
	private int persepcionVivienda;
	private int aguaEnVivienda;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isInundadaAnteriormente() {
		return inundadaAnteriormente;
	}
	public void setInundadaAnteriormente(boolean inundadaAnteriormente) {
		this.inundadaAnteriormente = inundadaAnteriormente;
	}
	public String getAnios() {
		return anios;
	}
	public void setAnios(String anios) {
		this.anios = anios;
	}
	public int getAntiguedadEnCasa() {
		return antiguedadEnCasa;
	}
	public void setAntiguedadEnCasa(int antiguedadEnCasa) {
		this.antiguedadEnCasa = antiguedadEnCasa;
	}
	public boolean isIrsePorInundacion() {
		return irsePorInundacion;
	}
	public void setIrsePorInundacion(boolean irsePorInundacion) {
		this.irsePorInundacion = irsePorInundacion;
	}
	public String getObservacionesNoIrse() {
		return observacionesNoIrse;
	}
	public void setObservacionesNoIrse(String observacionesNoIrse) {
		this.observacionesNoIrse = observacionesNoIrse;
	}
	public boolean isAbandonoViviendaPorInund() {
		return abandonoViviendaPorInund;
	}
	public void setAbandonoViviendaPorInund(boolean abandonoViviendaPorInund) {
		this.abandonoViviendaPorInund = abandonoViviendaPorInund;
	}
	public String getObsAlojamientoEnInundacion() {
		return obsalojamientoEnInundacion;
	}
	public void setObsAlojamientoEnInundacion(String obsAlojamientoEnInundacion) {
		this.obsalojamientoEnInundacion = obsAlojamientoEnInundacion;
	}
	public int getCntDiasFuera() {
		return cntDiasFuera;
	}
	public void setCntDiasFuera(int cntDiasFuera) {
		this.cntDiasFuera = cntDiasFuera;
	}
	public Long getAlturaAgua() {
		return alturaAgua;
	}
	public void setAlturaAgua(Long alturaAgua) {
		this.alturaAgua = alturaAgua;
	}
	public String getObsAguaVivienda() {
		return obsAguaVivienda;
	}
	public void setObsAguaVivienda(String obsAguaVivienda) {
		this.obsAguaVivienda = obsAguaVivienda;
	}
	public int getAlojamientoInundacion() {
		return alojamientoInundacion;
	}
	public void setAlojamientoInundacion(int alojamientoInundacion) {
		this.alojamientoInundacion = alojamientoInundacion;
	}
	public int getPersepcionVivienda() {
		return persepcionVivienda;
	}
	public void setPersepcionVivienda(int persepcionVivienda) {
		this.persepcionVivienda = persepcionVivienda;
	}
	public int getAguaEnVivienda() {
		return aguaEnVivienda;
	}
	public void setAguaEnVivienda(int aguaEnVivienda) {
		this.aguaEnVivienda = aguaEnVivienda;
	}
	
	
		
}
