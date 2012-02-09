package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class HacinamientoDTO implements Serializable , IsSerializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private int cantFamilias;
	private int cantHabitaciones;
	private int cantHabDormitorio;
	private boolean actividadLaboralEnViv;
	private int cantHabTrabajo;
	
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
