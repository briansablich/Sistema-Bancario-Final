package Dao;

import java.util.List;

import Dominio.Pago;
import Dominio.Prestamo;

public interface iPagoDao {
	
	int agregarPagoABase(Prestamo prestamoNuevo);
	List<Pago> ListarPorIdPrestamo(int id_prestamo);
}
