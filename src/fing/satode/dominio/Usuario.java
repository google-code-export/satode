package fing.satode.dominio;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fing.satode.data.UsuarioDTO;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	@Id @GeneratedValue
	private Long id;
	 
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	  private String password;
	 private String nombreCompleto;
	 
	 @ManyToOne
	 @JoinColumn(name="perfil_id")
	 private Perfil perfil;
	 
      public Usuario(){};
      
      public Usuario(UsuarioDTO dto){
    	  setId(dto.getId());
    	  setUsuario(dto.getUsuario());
    	  setNombreCompleto(dto.getNombreCompleto());
    	  setPassword(dto.getPassword());
    	  setPerfil(new Perfil(dto.getPerfil()));
      }
      
	  public String getPassword() {return password;}
	  public void setPassword(String password) {this.password = password;}
	  
	  public String getUsuario() {return usuario;}
	  public void setUsuario(String usuario) {this.usuario = usuario;}
	
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}


	public UsuarioDTO getDTO(){
		UsuarioDTO dto= new UsuarioDTO();
		dto.setId(id);
		dto.setNombreCompleto(nombreCompleto);
		dto.setPassword(password);
		if(perfil!=null){
			dto.setPerfil(perfil.getDTO());
		}
		dto.setUsuario(usuario);
		
		return dto;
	}
	
	  
}
