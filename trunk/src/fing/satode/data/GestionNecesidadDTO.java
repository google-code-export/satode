package fing.satode.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;



public class GestionNecesidadDTO implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private NecesidadDTO necesidad;
	
	private UsuarioDTO usuario;
	
	private Set<PlanSuministroDTO> planesSuministros= new HashSet<PlanSuministroDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NecesidadDTO getNecesidad() {
		return necesidad;
	}

	public void setNecesidad(NecesidadDTO necesidad) {
		this.necesidad = necesidad;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Set<PlanSuministroDTO> getPlanesSuministros() {
		return planesSuministros;
	}

	public void setPlanesSuministros(Set<PlanSuministroDTO> planesSuministros) {
		this.planesSuministros = planesSuministros;
	}
	
	
}
