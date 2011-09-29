package actions.usuarios;



import java.util.List;

import data.Perfil;
import data.Permiso;
import actions.base.BaseAction;

public class PerfilForm extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private Long id;
	private List<Permiso> permisos;
	
	public String execute() {
	
		if(isPostBack){
			
			if("nuevo".equals(a)){
				Perfil perfil = new Perfil();
				perfil.setNombre(nombre);
				perfil.setDescripcion(descripcion);
				servperfil.nuevo(perfil);
				
				return redirect("PerfilList.action");
			}
		}else{
			permisos = servperfil.findAllPermisos();
		}
		
		return SUCCESS;
	}

	

	public List<Permiso> getPermisos() {
		return permisos;
	}



	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	
}
