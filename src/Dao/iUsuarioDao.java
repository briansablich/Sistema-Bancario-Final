package Dao;

import Dominio.Usuario;

public interface iUsuarioDao {
	
	boolean validarLogin(String usuario, String contrasenia);
	Usuario obtenerUsuario(String usuario, String contrasenia);
	int agregarUsuario(Usuario usuarioNuevo);
	
}
