package fing.satode.dominio;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import fing.satode.data.PermisoDTO;


@Entity
@Table(name="permisos")
public class Permiso implements Serializable {
	@Id @GeneratedValue
	private Long id;
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String permiso;
	public Permiso(){};
	public Permiso(PermisoDTO pd) {
		// TODO Auto-generated constructor stub
		setPermiso(pd.getPermiso());
		setId(pd.getId());
	}
	
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	  
	public PermisoDTO getDTO(){
		PermisoDTO dto = new PermisoDTO();
		dto.setId(id);
		dto.setPermiso(permiso);
		return dto;
	}
	  
}
