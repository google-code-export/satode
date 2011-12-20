package fing.satode.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	  @Id @GeneratedValue
	  private Long id;
	  private String usuario;
	  private String password;
	 
	 
	 
	  public String getPassword() {return password;}
	  public void setPassword(String password) {this.password = password;}
	  
	  public String getUsuario() {return usuario;}
	  public void setUsuario(String usuario) {this.usuario = usuario;}

	  public Long getId() {return id;}
	  public void setId(Long id) {this.id = id;}
	  
}
