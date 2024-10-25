package Negocio;

import java.util.ArrayList;
import java.util.List;

import Dao.ClienteDao;
import Dao.iClienteDao;
import Dominio.Cliente;

public class ClienteNegocio implements iClienteNegocio {

	@Override
	public Cliente buscar_con_id(int id) {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.buscar_con_id(id);
	}

	@Override
	public List<Cliente> Listar() {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.Listar();
	}

	@Override
	public ArrayList<Cliente> ListarConEstadoFalse() {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.ListarConEstadoFalse();
	}

	@Override
	public int eliminarCliente(int id_cliente_borrar) {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.eliminarCliente(id_cliente_borrar);
	}

	@Override
	public int agregarCliente(Cliente clienteNuevo) {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.agregarCliente(clienteNuevo);
	}

	@Override
	public int BajaLogicaCliente(int idClienteBaja) {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.BajaLogicaCliente(idClienteBaja);
	}

	@Override
	public int ModificacionCliente(Cliente clienteModificar) {
		iClienteDao clienteDao = new ClienteDao();
		return clienteDao.ModificacionCliente(clienteModificar);
	}

}
