package fing.satode.bl.usuarios;

import org.springframework.transaction.annotation.Transactional;

import fing.satode.bl.base.ServiceBase;
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
}
