package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;


public class FotoDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private byte[] datos;
	private boolean antes;
	private Long id;
	private String url;
	private String nombre;
			
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getDatos() {
		return datos;
	}
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
	public boolean isAntes() {
		return antes;
	}
	public void setAntes(boolean antes) {
		this.antes = antes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	


	
	
}
