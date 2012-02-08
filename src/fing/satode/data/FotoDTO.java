package fing.satode.data;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;


public class FotoDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Byte> datos;
	private boolean antes;
	private Long id;
	
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArrayList<Byte> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<Byte> datos) {
		this.datos = datos;
	}
	public boolean isAntes() {
		return antes;
	}
	public void setAntes(boolean antes) {
		this.antes = antes;
	}
	


	
	
}
