package fing.satode.dominio;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fing.satode.data.PlanSuministroDTO;
import fing.satode.data.SolicitudEnvioDTO;

@Entity
@Table(name="planessuministros")
public class PlanSuministro {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="solicitudsuministro_id")
	private SolicitudSuministro solicitudsuministro;
	
	  @ManyToMany(
			  targetEntity=fing.satode.dominio.SolicitudEnvio.class,
		        cascade={CascadeType.PERSIST, CascadeType.ALL}
	    )
	  
	    @JoinTable(
	        name="PlanSuministro_SolicitudEnvio",
	        joinColumns=@JoinColumn(name="planSuministro_Id"),
	        inverseJoinColumns=@JoinColumn(name="solicitudEnvio_Id")
	    )
	  @org.hibernate.annotations.Cascade(
				value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
				)
	private Set<SolicitudEnvio> solicitudesEnvios;

	public PlanSuministro(){}
	
	public PlanSuministro(PlanSuministroDTO dto) {
		// TODO Auto-generated constructor stub
		id=dto.getId();
		solicitudsuministro= new SolicitudSuministro(dto.getSolicitudsuministro());
		solicitudesEnvios= new HashSet<SolicitudEnvio>();
		for(SolicitudEnvioDTO edto:dto.getSolicitudesEnvios()){
			solicitudesEnvios.add(new SolicitudEnvio(edto));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolicitudSuministro getSolicitudsuministro() {
		return solicitudsuministro;
	}

	public void setSolicitudsuministro(SolicitudSuministro solicitudsuministro) {
		this.solicitudsuministro = solicitudsuministro;
	}

	public Set<SolicitudEnvio> getSolicitudesEnvios() {
		return solicitudesEnvios;
	}

	public void setSolicitudesEnvios(Set<SolicitudEnvio> solicitudesEnvios) {
		this.solicitudesEnvios = solicitudesEnvios;
	}

	public PlanSuministroDTO getDTO() {
		PlanSuministroDTO dto= new PlanSuministroDTO();
		dto.setId(id);
		dto.setSolicitudsuministro(solicitudsuministro.getDTO());
		
		
		HashSet<SolicitudEnvioDTO> solicitudesEnviosDTO= new HashSet<SolicitudEnvioDTO>();
		for(SolicitudEnvio s:solicitudesEnvios){
			solicitudesEnviosDTO.add(s.getDTO());
		}
		dto.setSolicitudesEnvios(solicitudesEnviosDTO);
		
		return dto;
	}
	
	
}
