package Dao;

import Dominio.Usuario;

public interface iUsuarioDao {
	
	Usuario Validar(String usuario, String contrasenia);

	int agregarUsuario(Usuario usuarioNuevo);
	
}
