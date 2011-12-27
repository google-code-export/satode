package fing.satode.pl.usuario;

import java.util.ArrayList;
import java.util.List;

import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;
import fing.satode.pl.base.DAOBase;

public class UsuarioDAO extends DAOBase {

	private static UsuarioDAO instance;
	
	private UsuarioDAO(){}
	
	public static UsuarioDAO getInstance(){
		if(instance==null){
			instance = new UsuarioDAO();
		}
		
		return instance;
	}
	
	public Usuario buscarUsuario(String usu){
		return (Usuario) sess().createQuery("from Usuario Where usuario ='"+usu+"'").uniqueResult();
	}

	public ArrayList<Usuario> listaUsuarios() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from Usuario order by usuario").list();
		ArrayList<Usuario> res= new ArrayList<Usuario>(list);
		
		return res;
	}

	public ArrayList<Perfil> listaPerfiles() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from Perfil order by nombre").list();
		ArrayList<Perfil> res= new ArrayList<Perfil>(list);
		
		return res;
	}

	public ArrayList<Permiso> listaPermisos() {
		// TODO Auto-generated method stub
		List list=sess().createQuery("from Permiso order by permiso").list();
		ArrayList<Permiso> res= new ArrayList<Permiso>(list);
		
		return res;
	}

	public void nuevoPerfil(Perfil perfil) {
		// TODO Auto-generated method stub
		sess().save(perfil);
	}

	public void modificarPerfil(Perfil p) {
		// TODO Auto-generated method stub
		sess().update(p);
	}

	public void eliminarPerfil(Perfil p) {
		// TODO Auto-generated method stub
		sess().delete(p);
	}

	public void nuevoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		sess().save(usuario);
	}

	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		sess().update(usuario);
	}

	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		sess().delete(usuario);
	}
	
}
