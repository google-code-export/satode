package actions.base;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import services.PerfilService;
import services.UsuarioService;

import com.opensymphony.xwork2.ActionSupport;


import data.Usuario;

public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String redirectUrl;
	protected UsuarioService servusuarios;
	protected PerfilService servperfil;

	protected SimpleDateFormat fm= new SimpleDateFormat("dd/MM/yyyy");
	protected Usuario usuarioLogin;
	protected boolean isPostBack;
	protected String a;
	
	
	public void setPerfilService(PerfilService servperfil) {
		this.servperfil = servperfil;
	}

	public void setUsuarioService(UsuarioService value) {
		servusuarios=value;
		
	}
	
	// For redirect results

	public String getRedirectUrl() {
		return redirectUrl;
	}
	public String redirect(String to) {
		redirectUrl = to;
		return "redirect";
	}
	

	 public void setServletRequest(HttpServletRequest request){
	    this.request = request;
	  }

	  public HttpServletRequest getServletRequest(){
	    return request;
	  }

	  public void setServletResponse(HttpServletResponse response){
	    this.response = response;
	  }

	  public HttpServletResponse getServletResponse(){
	    return response;
	  }
	  
	  public String seguridad(String login){
		  if( request.getSession().getAttribute(login)!=null){
			  usuarioLogin =(Usuario)request.getSession().getAttribute(login);
			  return null;
		  }
		  
		  return "../usuarios/Login.action?msg=Usuario no logueado&tit=Acceso denegado";
	  }
	  
	 

		public boolean getIsPostBack() {
			return isPostBack;
		}
		public void setIsPostBack(boolean isPostBack) {
			this.isPostBack = isPostBack;
		}

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

}
