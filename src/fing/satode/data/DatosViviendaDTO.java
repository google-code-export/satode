package fing.satode.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.google.gwt.user.client.rpc.IsSerializable;

import fing.satode.dominio.Inundacion;
import fing.satode.dominio.ProblemasVivienda;


public class DatosViviendaDTO implements Serializable,IsSerializable {

	private static final long serialVersionUID = 1L;

private Long id;
	
	private int conservacionVivienda;
	private ProblemasViviendaDTO problemasVivienda;
	private HacinamientoDTO hacinamiento;
	private InundacionDTO inundacion;
	
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
	private String obsMaterialParedes;
	private String obsProcedenciaAgua;
	private String obsSanitaria;
	private String obsBasura;
	private String obsMaterialTecho;
	
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
	public ProblemasViviendaDTO getProblemasVivienda() {
		return problemasVivienda;
	}
	public void setProblemasVivienda(ProblemasViviendaDTO problemasVivienda) {
		this.problemasVivienda = problemasVivienda;
	}
	public HacinamientoDTO getHacinamiento() {
		return hacinamiento;
	}
	public void setHacinamiento(HacinamientoDTO hacinamiento) {
		this.hacinamiento = hacinamiento;
	}
	public InundacionDTO getInundacion() {
		return inundacion;
	}
	public void setInundacion(InundacionDTO inundacion) {
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
		return obsMaterialParedes;
	}
	public void setObsMaterialParedes(String obsMaterialParedes) {
		obsMaterialParedes = obsMaterialParedes;
	}
	public String getObsProcedenciaAgua() {
		return obsProcedenciaAgua;
	}
	public void setObsProcedenciaAgua(String obsProcedenciaAgua) {
		obsProcedenciaAgua = obsProcedenciaAgua;
	}
	public String getObsSanitaria() {
		return obsSanitaria;
	}
	public void setObsSanitaria(String obsSanitaria) {
		obsSanitaria = obsSanitaria;
	}
	public String getObsBasura() {
		return obsBasura;
	}
	public void setObsBasura(String obsBasura) {
		obsBasura = obsBasura;
	}
	
	public String getObsMaterialTecho() {
		return obsMaterialTecho;
	}
	public void setObsMaterialTecho(String obsMaterialTecho) {
		obsMaterialTecho = obsMaterialTecho;
	}
	
	
}
