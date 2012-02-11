package fing.satode.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.HacinamientoDTO;


@Entity
@Table(name="hacinamiento")
public class Hacinamiento {

	@Id @GeneratedValue
	private Long id;
	
	private int cantFamilias;
	private int cantHabitaciones;
	private int cantHabDormitorio;
	private boolean actividadLaboralEnViv;
	private int cantHabTrabajo;
	 
	 
	
	public Hacinamiento(){}
	
	public Hacinamiento(HacinamientoDTO dto){
		id= dto.getId();
		cantFamilias= dto.getCantFamilias();
		cantHabitaciones= dto.getCantHabitaciones();
		cantHabDormitorio= dto.getCantHabDormitorio();
		actividadLaboralEnViv= dto.isActividadLaboralEnViv();
		cantHabTrabajo= dto.getCantHabTrabajo();
	
	}



	
	public HacinamientoDTO getDTO() {
		HacinamientoDTO dto= new HacinamientoDTO();
		dto.setId(id);
		dto.setActividadLaboralEnViv(actividadLaboralEnViv);
		dto.setCantFamilias(cantFamilias);
		dto.setCantHabDormitorio(cantHabDormitorio);
		dto.setCantHabitaciones(cantHabitaciones);
		dto.setCantHabTrabajo(cantHabTrabajo);
		
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantFamilias() {
		return cantFamilias;
	}

	public void setCantFamilias(int cantFamilias) {
		this.cantFamilias = cantFamilias;
	}

	public int getCantHabitaciones() {
		return cantHabitaciones;
	}

	public void setCantHabitaciones(int cantHabitaciones) {
		this.cantHabitaciones = cantHabitaciones;
	}

	public int getCantHabDormitorio() {
		return cantHabDormitorio;
	}

	public void setCantHabDormitorio(int cantHabDormitorio) {
		this.cantHabDormitorio = cantHabDormitorio;
	}

	public boolean isActividadLaboralEnViv() {
		return actividadLaboralEnViv;
	}

	public void setActividadLaboralEnViv(boolean actividadLaboralEnViv) {
		this.actividadLaboralEnViv = actividadLaboralEnViv;
	}

	public int getCantHabTrabajo() {
		return cantHabTrabajo;
	}

	public void setCantHabTrabajo(int cantHabTrabajo) {
		this.cantHabTrabajo = cantHabTrabajo;
	}

	
	
	
	
}
