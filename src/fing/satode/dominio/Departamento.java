package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fing.satode.data.CiudadDTO;
import fing.satode.data.DepartamentoDTO;

@Entity
@Table(name="departamentos")
public class Departamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	private String nombre;

	 @OneToMany(
		        targetEntity=fing.satode.dominio.Ciudad.class,
		        cascade={CascadeType.PERSIST, CascadeType.MERGE}
	    )
	    @JoinTable(
	        name="ciudades",
	        joinColumns=@JoinColumn(name="id"),
	        inverseJoinColumns=@JoinColumn(name="departamento_id")
	    )
	private Collection<Ciudad> ciudades;
	 
	public Departamento(DepartamentoDTO dto){
		setId(dto.getId());
		setNombre(dto.getNombre());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Collection<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(Collection<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	public DepartamentoDTO getDTO(){
		DepartamentoDTO dto=new DepartamentoDTO();
		dto.setId(id);
		dto.setNombre(nombre);
		ArrayList<CiudadDTO> ciudadesDTO= new ArrayList<CiudadDTO>();
		for(Ciudad c:ciudades){
			ciudadesDTO.add(c.getDTO());
		}
		dto.setCiudades(ciudadesDTO);
		return dto;
	}

	
	
}
