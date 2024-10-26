package Negocio;

import java.util.List;

import Dominio.Prestamo;

public interface iPrestamoNegocio {
	
	int agregarPrestamo(Prestamo prestamoNuevo);
	List<Prestamo> Listar();
	List<Prestamo> ListarPorIdCliente(int id_cliente);
	int rechazarPrestamo(int id_cliente_rechazar);
	int aprobarPrestamo(int id_prestamo_aprobar, int id_cuenta_destino);
	Prestamo buscarPrestamoACuentaDestino (int id_cuenta_destino, int id_prestamo);
	Prestamo buscarPrestamoPorId (int id_prestamo);
	int finalizarPrestamo();

}
