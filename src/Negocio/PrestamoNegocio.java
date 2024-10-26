package Negocio;

import java.util.List;

import Dao.PrestamoDao;
import Dao.iPrestamoDao;
import Dominio.Prestamo;

public class PrestamoNegocio implements iPrestamoNegocio {
	
	private iPrestamoDao iPDao;

	public PrestamoNegocio() {
		this.iPDao = new PrestamoDao();
	}
	@Override
	public int agregarPrestamo(Prestamo prestamoNuevo) {
		return iPDao.agregarPrestamo(prestamoNuevo);
	}

	@Override
	public List<Prestamo> Listar() {
		return iPDao.Listar();
	}

	@Override
	public List<Prestamo> ListarPorIdCliente(int id_cliente) {
		return iPDao.ListarPorIdCliente(id_cliente);
	}

	@Override
	public int rechazarPrestamo(int id_cliente_rechazar) {
		return iPDao.rechazarPrestamo(id_cliente_rechazar);
	}

	@Override
	public int aprobarPrestamo(int id_prestamo_aprobar, int id_cuenta_destino) {
		return iPDao.aprobarPrestamo(id_prestamo_aprobar, id_cuenta_destino);
	}

	@Override
	public Prestamo buscarPrestamoACuentaDestino(int id_cuenta_destino, int id_prestamo) {
		return iPDao.buscarPrestamoACuentaDestino(id_cuenta_destino, id_prestamo);
	}

	@Override
	public Prestamo buscarPrestamoPorId(int id_prestamo) {
		return iPDao.buscarPrestamoPorId(id_prestamo);
	}

	@Override
	public int finalizarPrestamo() {
		return iPDao.finalizarPrestamo();
	}

}
