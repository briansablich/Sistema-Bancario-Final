package Negocio;

import java.util.ArrayList;

import Dao.TelefonoDao;
import Dao.iTelefonoDao;
import Dominio.Telefono;

public class TelefonoNegocio implements iTelefonoNegocio {
	
	private iTelefonoDao iTDao;
	
	TelefonoNegocio(){
		this.iTDao = new TelefonoDao();
	}

	@Override
	public int AgregarTelefonos(String telefono1, String telefono2, int id_cliente) {
		return iTDao.AgregarTelefonos(telefono1, telefono2, id_cliente);
	}

	@Override
	public int ModificacionTelefonos(String telefonoModificado1, String telefonoModificado2, int id_cliente) {
		return iTDao.ModificacionTelefonos(telefonoModificado1, telefonoModificado2, id_cliente);
	}

	@Override
	public ArrayList<Telefono> Listar_de(int id_cliente) {
		return iTDao.Listar_de(id_cliente);
	}

	@Override
	public int AgregarTelefono(int id_cliente, String telefono) {
		return iTDao.AgregarTelefono(id_cliente, telefono);
	}

	@Override
	public int ModificarTelefono(int id, String telefono) {
		return iTDao.ModificarTelefono(id, telefono);
	}

}
