package Dao;

import Dominio.Cliente;

import java.util.ArrayList;
import java.util.List;

public interface iClienteDao {
	
	int eliminarCliente(int id_cliente_borrar);
	int agregarCliente(Cliente clienteNuevo);
	Cliente buscar_con_id(int id);
	List<Cliente> Listar();
	ArrayList<Cliente> ListarConEstadoFalse();
	ArrayList<Cliente> ListarConEstadoTrue();
	int BajaLogicaCliente(int idClienteBaja);
	int ModificacionCliente(Cliente clienteModificar);
	int AltaLogicaCliente(int idClienteAlta);
	ArrayList<Integer> listarIdClientes();
	boolean existeDni(String dni);

}
