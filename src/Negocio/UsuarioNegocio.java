package Negocio;

import Dominio.Usuario;
import Dao.UsuarioDao;
import Dao.iUsuarioDao;

public class UsuarioNegocio implements iUsuarioNegocio {
	
	@Override
	public boolean validarLogin(String usuario, String contrasenia) {
		
		iUsuarioDao uDao = new UsuarioDao();
		return uDao.validarLogin(usuario, contrasenia);
		
	}
	
	@Override
	public Usuario obtenerUsuario(String usuario, String contrasenia) {
		
		iUsuarioDao uDao = new UsuarioDao();
		return uDao.obtenerUsuario(usuario, contrasenia);
		
	}

	@Override
	public int agregarUsuario(Usuario usuarioNuevo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cambiarClave(String usuario, String contrasenia) {
		iUsuarioDao uDao = new UsuarioDao();
		return uDao.cambiarClave(usuario, contrasenia);
	}

}
