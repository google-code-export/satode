package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;


public class PerfilDTO implements Serializable, IsSerializable{
	private Long id;
	private static final long serialVersionUID = 1L;

	private String nombre;
    private ArrayList<PermisoDTO> permisos;
       
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
	public ArrayList<PermisoDTO> getPermisos() {
		return permisos;
	}
	public void setPermisos(ArrayList<PermisoDTO> permisos) {
		this.permisos = permisos;
	}
    
    
}
