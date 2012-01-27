package fing.satode.data;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;


public class PROtrosDTO extends PuntoReferenciaDTO implements Serializable, IsSerializable {

	
	private static final long serialVersionUID = 1L;
	
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	


	
	
	
}
