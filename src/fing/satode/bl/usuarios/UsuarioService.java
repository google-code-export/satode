package fing.satode.bl.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
import fing.satode.data.PerfilDTO;
import fing.satode.data.PermisoDTO;
import fing.satode.data.UsuarioDTO;
import fing.satode.dominio.Perfil;
import fing.satode.dominio.Permiso;
import fing.satode.dominio.Usuario;
import fing.satode.pl.usuario.UsuarioDAO;

@Transactional
public class UsuarioService extends ServiceBase{
		
	
	public boolean login(String usu,String password){
		Usuario usuario = UsuarioDAO.getInstance().buscarUsuario(usu);
		
		if(usuario!=null){
			if(password!=null && usuario.getPassword()!=null){
				if(password.equals(usuario.getPassword())){
					return true;
				}
			}
		}
		
		return false;
	}
	
							 
	public ArrayList<UsuarioDTO> listaUsuarios() {
		// TODO Auto-generated method stub
		ArrayList<Usuario> usuarios=UsuarioDAO.getInstance().listaUsuarios();
		ArrayList<UsuarioDTO> dtos= new ArrayList<UsuarioDTO>();
		for(Usuario u: usuarios){
			dtos.add(u.getDTO());
		}
		
		return dtos;
	}


	public ArrayList<PerfilDTO> listaPerfiles() {
		// TODO Auto-generated method stub
		ArrayList<Perfil> perfiles=UsuarioDAO.getInstance().listaPerfiles();
		ArrayList<PerfilDTO> dtos= new ArrayList<PerfilDTO>();
		for(Perfil p : perfiles){
			dtos.add(p.getDTO());
		}
		return dtos;
	}


	public ArrayList<PermisoDTO> listaPermisos() {
		// TODO Auto-generated method stub
		ArrayList<Permiso> permisos=UsuarioDAO.getInstance().listaPermisos();
		ArrayList<PermisoDTO> dtos= new ArrayList<PermisoDTO>();
		for(Permiso p : permisos){
			dtos.add(p.getDTO());
		}
		return dtos;
	}


	public void nuevoPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		Perfil p= new Perfil(perfil);
		UsuarioDAO.getInstance().nuevoPerfil(p);
	}


	public void modificarPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		Perfil p= new Perfil(perfil);
		UsuarioDAO.getInstance().modificarPerfil(p);
	}


	public void eliminarPerfil(PerfilDTO perfil) {
		// TODO Auto-generated method stub
		Perfil p= new Perfil(perfil);
		UsuarioDAO.getInstance().eliminarPerfil(p);
		
	}


	public void nuevoUsuario(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario(dto);
		UsuarioDAO.getInstance().nuevoUsuario(usuario);
	}


	public void modificarUsuario(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario(dto);
		UsuarioDAO.getInstance().modificarUsuario(usuario);
	}


	public void eliminarUsuario(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario(dto);	
		UsuarioDAO.getInstance().eliminarUsuario(usuario);
	}


	public UsuarioDTO buscarUsuario(String usuario) {
		// TODO Auto-generated method stub
		Usuario u=UsuarioDAO.getInstance().buscarUsuario(usuario);
		if(u!=null){
			return u.getDTO();
		}
		return null;
	}
}
