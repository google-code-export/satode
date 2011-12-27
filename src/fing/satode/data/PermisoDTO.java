package fing.satode.data;

import java.io.Serializable;



public class PermisoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	private String permiso;

}
