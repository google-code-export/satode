package actions.usuarios;



import data.Perfil;
import actions.base.BaseAction;

public class PerfilForm extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private Long id;
	
	public String execute() {
	
		if(isPostBack){
			
			if("nuevo".equals(a)){
				Perfil perfil = new Perfil();
				perfil.setNombre(nombre);
				perfil.setDescripcion(descripcion);
				servperfil.nuevo(perfil);
			}
		}
		
		return SUCCESS;
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
