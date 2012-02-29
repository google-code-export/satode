package fing.satode.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.EventoDTO;

@Entity
@Table(name="eventos")
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private Date fechaInicio;
	
	@ManyToOne
    @JoinColumn(name="tipoevento_id")
	private TipoEvento tipoEvento;
	
	@ManyToOne
    @JoinColumn(name="ciudad_id")
	private Ciudad ciudad;
	
	@ManyToOne
    @JoinColumn(name="departamento_id")
	private Departamento departamento;
	
	
	private Boolean latitudlongitud=false;
	
	private Float latitud=0F;
	
	private Float longitud=0F;
	
	private String fuente="";
	
	private int muertos=0;
	
	private int afectados=0;
	
	private Float perdidasPesos=0F;
	
	private Float perdidasDolares=0F;
	
	private Float viasAfectadas=0F;
	
	private String otrasPerdidas="";
	
	private int desaparecidos=0;
	
	private  float cultivosBosques=0F;
	
	private int heridosEnfermos=0;
	
	private int ganado=0;
	
	private int centrosEducacion=0;
	
	private int reubicados=0;
	
	private int centrosHospitalarios=0;
	
	private int vivAfectadas=0;
	
	private int evacuados=0;
	
	private int damnificados=0;
	
	private int vivDestruida=0;
	
	private Boolean transporte=false;
	
	private Boolean comunicaciones=false;
	
	private Boolean instalacionesSocorro=false;
	
	private Boolean agropecuario=false;
	
	private Boolean acueducto=false;
	
	private Boolean alcantarillado=false;
	
	private Boolean energia=false;
	
	private Boolean industria=false;
	
	private Boolean salud=false;
	
	private String otros="";
	
	private float duracion=0F;
	
	private int duracionMedida=1;
	
	private String observaciones;
	
	public Evento(){}
	
	public Evento(EventoDTO dto){
		id=dto.getId();
		fechaInicio=dto.getFechaInicio();
		tipoEvento= new TipoEvento(dto.getTipoEvento());
		ciudad= new Ciudad(dto.getCiudad()); 
		setDepartamento(new Departamento(dto.getDepartamento())); 
		latitudlongitud=dto.getLatitudlongitud();
		latitud=dto.getLatitud();
		longitud=dto.getLongitud();
		fuente=dto.getFuente();
		muertos=dto.getMuertos();
		perdidasPesos=dto.getPerdidasPesos();
		perdidasDolares=dto.getPerdidasDolares();
		viasAfectadas=dto.getViasAfectadas();
		otrasPerdidas=dto.getOtrasPerdidas();
		desaparecidos=dto.getDesaparecidos();
		cultivosBosques=dto.getCultivosBosques();
		heridosEnfermos=dto.getHeridosEnfermos();
		ganado=dto.getGanado();
		centrosEducacion=dto.getCentrosEducacion();
		reubicados=dto.getReubicados();
		centrosHospitalarios=dto.getCentrosHospitalarios();
		vivAfectadas=dto.getVivAfectadas();
		evacuados=dto.getEvacuados();
		damnificados=dto.getDamnificados();
		vivDestruida=dto.getVivDestruida();
		transporte=dto.getTransporte();
		comunicaciones=dto.getComunicaciones();
		instalacionesSocorro=dto.getInstalacionesSocorro();
		agropecuario=dto.getAgropecuario();
		acueducto=dto.getAcueducto();
		alcantarillado=dto.getAlcantarillado();
		energia=dto.getEnergia();
		industria=dto.getIndustria();
		salud=dto.getSalud();
		otros=dto.getOtros();
		duracion=dto.getDuracion();
		duracionMedida=dto.getDuracionMedida();
		observaciones=dto.getObservaciones();
		afectados= dto.getAfectados();
	}

	public int getAfectados() {
		return afectados;
	}

	public void setAfectados(int afectados) {
		this.afectados = afectados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Boolean getLatitudlongitud() {
		return latitudlongitud;
	}

	public void setLatitudlongitud(Boolean latitudlongitud) {
		this.latitudlongitud = latitudlongitud;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public int getMuertos() {
		return muertos;
	}

	public void setMuertos(int muertos) {
		this.muertos = muertos;
	}

	public Float getPerdidasPesos() {
		return perdidasPesos;
	}

	public void setPerdidasPesos(Float perdidasPesos) {
		this.perdidasPesos = perdidasPesos;
	}

	public Float getPerdidasDolares() {
		return perdidasDolares;
	}

	public void setPerdidasDolares(Float perdidasDolares) {
		this.perdidasDolares = perdidasDolares;
	}

	public Float getViasAfectadas() {
		return viasAfectadas;
	}

	public void setViasAfectadas(Float viasAfectadas) {
		this.viasAfectadas = viasAfectadas;
	}

	public String getOtrasPerdidas() {
		return otrasPerdidas;
	}

	public void setOtrasPerdidas(String otrasPerdidas) {
		this.otrasPerdidas = otrasPerdidas;
	}

	public int getDesaparecidos() {
		return desaparecidos;
	}

	public void setDesaparecidos(int desaparecidos) {
		this.desaparecidos = desaparecidos;
	}

	public float getCultivosBosques() {
		return cultivosBosques;
	}

	public void setCultivosBosques(float cultivosBosques) {
		this.cultivosBosques = cultivosBosques;
	}

	public int getHeridosEnfermos() {
		return heridosEnfermos;
	}

	public void setHeridosEnfermos(int heridosEnfermos) {
		this.heridosEnfermos = heridosEnfermos;
	}

	public int getGanado() {
		return ganado;
	}

	public void setGanado(int ganado) {
		this.ganado = ganado;
	}

	public int getCentrosEducacion() {
		return centrosEducacion;
	}

	public void setCentrosEducacion(int centrosEducacion) {
		this.centrosEducacion = centrosEducacion;
	}

	public int getReubicados() {
		return reubicados;
	}

	public void setReubicados(int reubicados) {
		this.reubicados = reubicados;
	}

	public int getCentrosHospitalarios() {
		return centrosHospitalarios;
	}

	public void setCentrosHospitalarios(int centrosHospitalarios) {
		this.centrosHospitalarios = centrosHospitalarios;
	}

	public int getVivAfectadas() {
		return vivAfectadas;
	}

	public void setVivAfectadas(int vivAfectadas) {
		this.vivAfectadas = vivAfectadas;
	}

	public int getEvacuados() {
		return evacuados;
	}

	public void setEvacuados(int evacuados) {
		this.evacuados = evacuados;
	}

	public int getDamnificados() {
		return damnificados;
	}

	public void setDamnificados(int damnificados) {
		this.damnificados = damnificados;
	}

	public int getVivDestruida() {
		return vivDestruida;
	}

	public void setVivDestruida(int vivDestruida) {
		this.vivDestruida = vivDestruida;
	}

	public Boolean getTransporte() {
		return transporte;
	}

	public void setTransporte(Boolean transporte) {
		this.transporte = transporte;
	}

	public Boolean getComunicaciones() {
		return comunicaciones;
	}

	public void setComunicaciones(Boolean comunicaciones) {
		this.comunicaciones = comunicaciones;
	}

	public Boolean getInstalacionesSocorro() {
		return instalacionesSocorro;
	}

	public void setInstalacionesSocorro(Boolean instalacionesSocorro) {
		this.instalacionesSocorro = instalacionesSocorro;
	}

	public Boolean getAgropecuario() {
		return agropecuario;
	}

	public void setAgropecuario(Boolean agropecuario) {
		this.agropecuario = agropecuario;
	}

	public Boolean getAcueducto() {
		return acueducto;
	}

	public void setAcueducto(Boolean acueducto) {
		this.acueducto = acueducto;
	}

	public Boolean getAlcantarillado() {
		return alcantarillado;
	}

	public void setAlcantarillado(Boolean alcantarillado) {
		this.alcantarillado = alcantarillado;
	}

	public Boolean getEnergia() {
		return energia;
	}

	public void setEnergia(Boolean energia) {
		this.energia = energia;
	}

	public Boolean getIndustria() {
		return industria;
	}

	public void setIndustria(Boolean industria) {
		this.industria = industria;
	}

	public Boolean getSalud() {
		return salud;
	}

	public void setSalud(Boolean salud) {
		this.salud = salud;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public int getDuracionMedida() {
		return duracionMedida;
	}

	public void setDuracionMedida(int duracionMedida) {
		this.duracionMedida = duracionMedida;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public EventoDTO getDTO(){
		EventoDTO dto= new EventoDTO();
		dto.setId(id);
		dto.setFechaInicio(fechaInicio);
		dto.setTipoEvento(tipoEvento.getDTO());
		dto.setCiudad(ciudad.getDTO());
		dto.setDepartamento(getDepartamento().getDTO());
		dto.setLatitudlongitud(latitudlongitud);
		dto.setLatitud(latitud);
		dto.setLongitud(longitud);
		dto.setFuente(fuente);
		dto.setMuertos(muertos);
		dto.setPerdidasPesos(perdidasPesos);
		dto.setPerdidasDolares(perdidasDolares);
		dto.setViasAfectadas(viasAfectadas);
		dto.setOtrasPerdidas(otrasPerdidas);
		dto.setDesaparecidos(desaparecidos);
		dto.setCultivosBosques(cultivosBosques);
		dto.setHeridosEnfermos(heridosEnfermos);
		dto.setGanado(ganado);
		dto.setCentrosEducacion(centrosEducacion);
		dto.setReubicados(reubicados);
		dto.setCentrosHospitalarios(centrosHospitalarios);
		dto.setVivAfectadas(vivAfectadas);
		dto.setEvacuados(evacuados);
		dto.setDamnificados(damnificados);
		dto.setVivDestruida(vivDestruida);
		dto.setTransporte(transporte);
		dto.setComunicaciones(comunicaciones);
		dto.setInstalacionesSocorro(instalacionesSocorro);
		dto.setAgropecuario(agropecuario);
		dto.setAcueducto(acueducto);
		dto.setAlcantarillado(alcantarillado);
		dto.setEnergia(energia);
		dto.setIndustria(industria);
		dto.setSalud(salud);
		dto.setOtros(otros);
		dto.setDuracion(duracion);
		dto.setDuracionMedida(duracionMedida);
		dto.setObservaciones(observaciones);
		dto.setAfectados(afectados);
		
		return dto;
	}

	private Departamento getDepartamento() {
		return departamento;
	}

	private void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
