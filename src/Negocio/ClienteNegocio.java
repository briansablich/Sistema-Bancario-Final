package Negocio;

import java.util.ArrayList;
import java.util.List;

import Dao.ClienteDao;
import Dao.iClienteDao;
import Dominio.Cliente;

public class ClienteNegocio implements iClienteNegocio {
	
	private iClienteDao clienteDao;
	
	public ClienteNegocio() {
		this.clienteDao = new ClienteDao();
	}

	@Override
	public Cliente buscar_con_id(int id) {
		return clienteDao.buscar_con_id(id);
	}

	@Override
	public List<Cliente> Listar() {
		return clienteDao.Listar();
	}

	@Override
	public ArrayList<Cliente> ListarConEstadoFalse() {
		return clienteDao.ListarConEstadoFalse();
	}

	@Override
	public ArrayList<Cliente> ListarConEstadoTrue() {
		return clienteDao.ListarConEstadoTrue();
	}
	
	@Override
	public int eliminarCliente(int id_cliente_borrar) {
		return clienteDao.eliminarCliente(id_cliente_borrar);
	}

	@Override
	public int agregarCliente(Cliente clienteNuevo) {
		return clienteDao.agregarCliente(clienteNuevo);
	}

	@Override
	public int BajaLogicaCliente(int idClienteBaja) {
		return clienteDao.BajaLogicaCliente(idClienteBaja);
	}

	@Override
	public int ModificacionCliente(Cliente clienteModificar) {
		return clienteDao.ModificacionCliente(clienteModificar);
	}

	@Override
	public int AltaLogicaCliente(int idClienteAlta) {
		return clienteDao.AltaLogicaCliente(idClienteAlta);
	}

	@Override
	public ArrayList<Integer> listarIdClientes() {
		return clienteDao.listarIdClientes();
	}

	@Override
	public boolean existeDni(String dni) {
		return clienteDao.existeDni(dni);
	}


}
