package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.InundacionDTO;


@Entity
@Table(name="inundaciones")
public class Inundacion {

	@Id @GeneratedValue
	private Long id;
	
	private boolean inundadaAnteriormente;
	private String anios;
	private int antiguedadEnCasa;
	private boolean irsePorInundacion;
	private String observacionesNoIrse;
	private boolean abandonoViviendaPorInund;
	private String obsAlojamientoEnInundacion;
	private int cntDiasFuera;
	private Long alturaAgua;
	private String obsAguaVivienda;
	
	private int alojamientoInundacion;
	private int persepcionVivienda;
	private int aguaEnVivienda;
	 
	 
	
	public Inundacion(){}
	
	public Inundacion(InundacionDTO dto){
		id= dto.getId();
		inundadaAnteriormente = dto.isInundadaAnteriormente();
		anios= dto.getAnios();
		antiguedadEnCasa= dto.getAntiguedadEnCasa();
		irsePorInundacion= dto.isIrsePorInundacion();
		observacionesNoIrse= dto.getObservacionesNoIrse();
		abandonoViviendaPorInund= dto.isAbandonoViviendaPorInund();
		obsAlojamientoEnInundacion= dto.getObsAlojamientoEnInundacion();
		cntDiasFuera= dto.getCntDiasFuera();
		alturaAgua= dto.getAlturaAgua();
		obsAguaVivienda= dto.getObsAguaVivienda();
		
		alojamientoInundacion = dto.getAlojamientoInundacion();
		persepcionVivienda= dto.getPersepcionVivienda();
		aguaEnVivienda= dto.getAguaEnVivienda();
	
	}



	
	public InundacionDTO getDTO() {
		InundacionDTO dto= new InundacionDTO();
		dto.setId(id);
		dto.setInundadaAnteriormente(inundadaAnteriormente);
		dto.setAnios(anios);
		dto.setAntiguedadEnCasa(antiguedadEnCasa);
		dto.setIrsePorInundacion(irsePorInundacion);
		dto.setObservacionesNoIrse(observacionesNoIrse);
		dto.setAbandonoViviendaPorInund(abandonoViviendaPorInund);
		dto.setObsAlojamientoEnInundacion(obsAlojamientoEnInundacion);
		dto.setCntDiasFuera(cntDiasFuera);
		dto.setAlturaAgua(alturaAgua);
		dto.setObsAguaVivienda(obsAguaVivienda);
		
		dto.setAlojamientoInundacion(alojamientoInundacion);
		dto.setPersepcionVivienda(persepcionVivienda);
		dto.setAguaEnVivienda(aguaEnVivienda);
		
		return dto;
	}

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
		return obsAlojamientoEnInundacion;
	}

	public void setObsAlojamientoEnInundacion(String obsAlojamientoEnInundacion) {
		this.obsAlojamientoEnInundacion = obsAlojamientoEnInundacion;
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
