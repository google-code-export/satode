package actions.usuarios;

import com.opensymphony.xwork2.Action;

import actions.base.BaseAction;

public class Inicial extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		return redirect("/views/usuarios/Inicial.jsp");
		
	}
}
