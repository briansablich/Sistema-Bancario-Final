package Dao;

import java.util.ArrayList;

import Dominio.Telefono;

public interface iTelefonoDao {
	
	int AgregarTelefonos(String telefono1, String telefono2, int id_cliente);
	int ModificacionTelefonos(String telefonoModificado1, String telefonoModificado2, int id_cliente);
	ArrayList<Telefono> Listar_de(int id_cliente);
	int AgregarTelefono(int id_cliente, String telefono);
	public int ModificarTelefono(int id, String telefono);
	
}
