package actions.usuarios;

import actions.base.BaseAction;
import data.Usuario;

public class Login extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String usuario;
	private boolean isPostBack;
	
	public String execute() {
		System.out.print("ENTRA A LOGIN");
		Usuario usu=null;
		if(isPostBack){
			if(usuario!= null && usuario.trim().length()>0){
				usu=servusuarios.buscarUsuario(usuario);
			}
			
			if(usu==null){
				return redirect("Login.action?msg=Usuario/Password invalido/s&tit=LOGIN ERROR");
			}
			
			if(password!=null && password.trim().toUpperCase().equals(usu.getPassword().toUpperCase()) && password.trim().length()>0){
				//Logueo
				if(usu.getEstado()== 2){
					return redirect("Login.action?msg=Usuario incativo, solicite activacion&tit=LOGIN ERROR");
				}
				
				
				return redirect("Inicial.action?msg=Bienvenido!!&tit=SATODE.");
				
				
			}else{
				return redirect("Login.action?msg=Usuario/Password invalido/s&tit=LOGIN ERROR");
			}
			
		}
		
		return SUCCESS;
	}	
	
	public void setPassword(String password) {
		this.password = password;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	public void setIsPostBack(boolean isPostBack) {
		this.isPostBack = isPostBack;
	}
	
}
