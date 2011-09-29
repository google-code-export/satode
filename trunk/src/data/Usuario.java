package data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	  @Id @GeneratedValue
	  private Long id;
	  private String name;
	  private String usuario;
	  private String password;
	  private int estado;
	  
	  @ManyToOne(fetch= FetchType.LAZY)
	  @JoinColumn(name = "perfil_id")
	  private Perfil perfil;

	 
	  public Perfil getPerfil() {
		return perfil;
	  }
	
	  public void setPerfil(Perfil perfil) {
			this.perfil = perfil;
	  }
	
	  public int getEstado() {return estado;}
	  public void setEstado(int estado) {this.estado = estado;}
	
	  public String getPassword() {return password;}
	  public void setPassword(String password) {this.password = password;}
	  
	  public String getUsuario() {return usuario;}
	  public void setUsuario(String usuario) {this.usuario = usuario;}

	  
	  public Long getId() {return id;}
	  public void setId(Long id) {this.id = id;}

	  public String getName() {return name;}
	  public void setName(String name) {this.name = name;}
	  

}
