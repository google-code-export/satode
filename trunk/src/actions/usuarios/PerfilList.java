package actions.usuarios;

import java.util.List;

import data.Perfil;

import actions.base.BaseAction;

public class PerfilList extends BaseAction {

	private static final long serialVersionUID = 1L;
	private List<Perfil> perfiles;
	private String nombre;
	
	public String execute() {
	
		if(isPostBack){
			if("buscar".equals(a)){
				perfiles = servperfil.findLikeNombre(nombre);
			}
		}else{		
			perfiles = servperfil.findAll();
		}
		
		return SUCCESS;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	
}
