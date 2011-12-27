package fing.satode.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;

@Entity
@Table(name="perfiles")
public class Perfil implements Serializable {

	@Id @GeneratedValue
	private Long id;
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	  
	  @ManyToMany(
		        targetEntity=fing.satode.dominio.Permiso.class,
		        cascade={CascadeType.PERSIST, CascadeType.MERGE}
	    )
	    @JoinTable(
	        name="Perfiles_Permisos",
	        joinColumns=@JoinColumn(name="Perfil_Id"),
	        inverseJoinColumns=@JoinColumn(name="Permiso_Id")
	    )
	    private Collection<Permiso> permisos;

	  	public Perfil(){};
	  	
		public Perfil(PerfilDTO perfil) {
		// TODO Auto-generated constructor stub
			setId(perfil.getId());
			setNombre(perfil.getNombre());
			permisos= new ArrayList<Permiso>();
			for(PermisoDTO pd: perfil.getPermisos()){
				Permiso permiso= new Permiso(pd);
				permisos.add(permiso);
			}
			
		}	

		public String getNombre() {
			return nombre;
		}
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
		public Collection<Permiso> getPermisos() {
			return permisos;
		}
	
		public void setPermisos(Collection<Permiso> permisos) {
			ArrayList<Permiso> res= new ArrayList<Permiso>(permisos);
			this.permisos = res;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
  
		public PerfilDTO getDTO(){
			PerfilDTO perfil= new PerfilDTO();
			perfil.setId(id);
			perfil.setNombre(nombre);
			ArrayList<PermisoDTO> permisosDTO= new ArrayList<PermisoDTO>();
			for(Permiso p : permisos){
				permisosDTO.add(p.getDTO());
			}
			perfil.setPermisos(permisosDTO);
			
			return perfil;
		}
	  
}
