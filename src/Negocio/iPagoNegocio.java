package Negocio;

import java.util.List;

import Dominio.Pago;
import Dominio.Prestamo;

public interface iPagoNegocio {

	int agregarPagoABase(Prestamo prestamoNuevo);
	List<Pago> ListarPorIdPrestamo(int id_prestamo);
}
