package Negocio;

import java.util.ArrayList;
import java.util.List;

import Dominio.Cliente;

public interface iClienteNegocio {
	
	int eliminarCliente(int id_cliente_borrar);
	int agregarCliente(Cliente clienteNuevo);
	Cliente buscar_con_id(int id);
	List<Cliente> Listar();
	ArrayList<Cliente> ListarConEstadoFalse();
	int BajaLogicaCliente(int idClienteBaja);
	int ModificacionCliente(Cliente clienteModificar);
	int AltaLogicaCliente(int idClienteAlta);
	ArrayList<Integer> listarIdClientes();
	boolean existeDni(String dni);
}
