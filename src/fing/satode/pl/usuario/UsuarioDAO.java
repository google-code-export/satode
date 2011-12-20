package fing.satode.pl.usuario;

import java.util.ArrayList;
import java.util.List;

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
		List list=sess().createQuery("from Usuario").list();
		ArrayList<Usuario> res= new ArrayList<Usuario>(list);
		
		return res;
	}
	
}
