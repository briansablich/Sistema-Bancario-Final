package Negocio;

import java.util.List;

import Dao.PagoDao;
import Dao.iPagoDao;
import Dominio.Pago;
import Dominio.Prestamo;

public class PagoNegocio implements iPagoNegocio {
	
	private iPagoDao iPDao;
	
	public PagoNegocio() {
		this.iPDao = new PagoDao();
	}

	@Override
	public int agregarPagoABase(Prestamo prestamoNuevo) {
		return iPDao.agregarPagoABase(prestamoNuevo);
	}

	@Override
	public List<Pago> ListarPorIdPrestamo(int id_prestamo) {
		return iPDao.ListarPorIdPrestamo(id_prestamo);
	}

}
