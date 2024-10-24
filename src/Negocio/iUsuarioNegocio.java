package Negocio;

import Dominio.Usuario;

public interface iUsuarioNegocio {
	
	boolean validarLogin(String usuario, String contrasenia);
	
	Usuario obtenerUsuario(String usuario, String contrasenia);

	int agregarUsuario(Usuario usuarioNuevo);
	
}
