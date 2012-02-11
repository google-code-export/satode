package fing.satode.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fing.satode.data.EventoDTO;

@Entity
@Table(name="datosviviendas")
public class DatosVivienda implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private int conservacionVivienda;
	
	@OneToOne
    @JoinColumn(name="problemasvivienda_id")
	private ProblemasVivienda problemasVivienda;
	
	@OneToOne
    @JoinColumn(name="hacinamiento_id")
	private Hacinamiento hacinamiento;
	
	@OneToOne
    @JoinColumn(name="inundacion_id")
	private Inundacion inundacion;
	
	//Referencias a constantes
	private int banios;
	private int propietarioOtras;
	private int tenenciaVivienda;
	private int mudarse;
	private int procedenciaAguaConsumo;
	private int procedenciaAguaVivienda;
	private int basura;
	private int categoriaVivienda;
	private int sanitario;
	private int estadoTerminacion;
	private int energia;
	private int materialParedes;
	private int estadoPared;
	private int ocupacion;
	private int materialTecho;
	private int materialPiso;
	 
	
	//Atributos
	private String ObsMaterialParedes;
	private String ObsProcedenciaAgua;
	private String ObsSanitaria;
	private String ObsBasura;
	private String ObsMaterialPared;
	private String ObsMaterialTecho;
	
	
	public DatosVivienda(){}
	
	public DatosVivienda(DatosViviendaDTO dto){
		id=dto.getId();
		duracionMedida=dto.getDuracionMedida();
		observaciones=dto.getObservaciones();
	}

	
	
	public EventoDTO getDTO(){
		DatosViviendaDTO dto= new DatosViviendaDTO();
		dto.setId(id);
		dto.setFechaInicio(fechaInicio);
		
		
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getConservacionVivienda() {
		return conservacionVivienda;
	}

	public void setConservacionVivienda(int conservacionVivienda) {
		this.conservacionVivienda = conservacionVivienda;
	}

	public ProblemasVivienda getProblemasVivienda() {
		return problemasVivienda;
	}

	public void setProblemasVivienda(ProblemasVivienda problemasVivienda) {
		this.problemasVivienda = problemasVivienda;
	}

	public Hacinamiento getHacinamiento() {
		return hacinamiento;
	}

	public void setHacinamiento(Hacinamiento hacinamiento) {
		this.hacinamiento = hacinamiento;
	}

	public Inundacion getInundacion() {
		return inundacion;
	}

	public void setInundacion(Inundacion inundacion) {
		this.inundacion = inundacion;
	}

	public int getBanios() {
		return banios;
	}

	public void setBanios(int banios) {
		this.banios = banios;
	}

	public int getPropietarioOtras() {
		return propietarioOtras;
	}

	public void setPropietarioOtras(int propietarioOtras) {
		this.propietarioOtras = propietarioOtras;
	}

	public int getTenenciaVivienda() {
		return tenenciaVivienda;
	}

	public void setTenenciaVivienda(int tenenciaVivienda) {
		this.tenenciaVivienda = tenenciaVivienda;
	}

	public int getMudarse() {
		return mudarse;
	}

	public void setMudarse(int mudarse) {
		this.mudarse = mudarse;
	}

	public int getProcedenciaAguaConsumo() {
		return procedenciaAguaConsumo;
	}

	public void setProcedenciaAguaConsumo(int procedenciaAguaConsumo) {
		this.procedenciaAguaConsumo = procedenciaAguaConsumo;
	}

	public int getProcedenciaAguaVivienda() {
		return procedenciaAguaVivienda;
	}

	public void setProcedenciaAguaVivienda(int procedenciaAguaVivienda) {
		this.procedenciaAguaVivienda = procedenciaAguaVivienda;
	}

	public int getBasura() {
		return basura;
	}

	public void setBasura(int basura) {
		this.basura = basura;
	}

	public int getCategoriaVivienda() {
		return categoriaVivienda;
	}

	public void setCategoriaVivienda(int categoriaVivienda) {
		this.categoriaVivienda = categoriaVivienda;
	}

	public int getSanitario() {
		return sanitario;
	}

	public void setSanitario(int sanitario) {
		this.sanitario = sanitario;
	}

	public int getEstadoTerminacion() {
		return estadoTerminacion;
	}

	public void setEstadoTerminacion(int estadoTerminacion) {
		this.estadoTerminacion = estadoTerminacion;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getMaterialParedes() {
		return materialParedes;
	}

	public void setMaterialParedes(int materialParedes) {
		this.materialParedes = materialParedes;
	}

	public int getEstadoPared() {
		return estadoPared;
	}

	public void setEstadoPared(int estadoPared) {
		this.estadoPared = estadoPared;
	}

	public int getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}

	public int getMaterialTecho() {
		return materialTecho;
	}

	public void setMaterialTecho(int materialTecho) {
		this.materialTecho = materialTecho;
	}

	public int getMaterialPiso() {
		return materialPiso;
	}

	public void setMaterialPiso(int materialPiso) {
		this.materialPiso = materialPiso;
	}

	public String getObsMaterialParedes() {
		return ObsMaterialParedes;
	}

	public void setObsMaterialParedes(String obsMaterialParedes) {
		ObsMaterialParedes = obsMaterialParedes;
	}

	public String getObsProcedenciaAgua() {
		return ObsProcedenciaAgua;
	}

	public void setObsProcedenciaAgua(String obsProcedenciaAgua) {
		ObsProcedenciaAgua = obsProcedenciaAgua;
	}

	public String getObsSanitaria() {
		return ObsSanitaria;
	}

	public void setObsSanitaria(String obsSanitaria) {
		ObsSanitaria = obsSanitaria;
	}

	public String getObsBasura() {
		return ObsBasura;
	}

	public void setObsBasura(String obsBasura) {
		ObsBasura = obsBasura;
	}

	public String getObsMaterialPared() {
		return ObsMaterialPared;
	}

	public void setObsMaterialPared(String obsMaterialPared) {
		ObsMaterialPared = obsMaterialPared;
	}

	public String getObsMaterialTecho() {
		return ObsMaterialTecho;
	}

	public void setObsMaterialTecho(String obsMaterialTecho) {
		ObsMaterialTecho = obsMaterialTecho;
	}
	
	

	
}
