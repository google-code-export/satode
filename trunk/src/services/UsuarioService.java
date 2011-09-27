package services;
import data.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UsuarioService extends ServiceBase {
	
	// So Spring can inject the session factory
	
	
	private Usuario usuario;
	
	public Usuario getUsuarioCache(){
		return usuario;
	}
	
	public void setUsuarioCache(Usuario u){
		usuario=u;
	}
	

	// Shortcut for sessionFactory.getCurrentSession()
	public Session sess() {
		return sessionFactory.getCurrentSession();
	}
	
	
	
	public Usuario getUsuarioById(Long id){
		return(Usuario) sess().load(Usuario.class,id);
	}

	public void modificar(Usuario usuario) {
		sess().update(usuario);
	}

	public void nuevo(Usuario usuario) {
		usuario.setId(0l);
		sess().save(usuario);
	}

	public Usuario buscarUsuario(String usuario2) {
		return (Usuario) sess().createQuery("from Usuario Where usuario ='"+usuario2+"'").uniqueResult();
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return sess().getSessionFactory().openSession().connection();
	}
	
}
