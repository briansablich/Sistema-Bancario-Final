package Negocio;

import java.util.ArrayList;

import Dao.MovimientoDao;
import Dao.iMovimientoDao;
import Dominio.Movimiento;
import Dominio.Prestamo;

public class MovimientoNegocio implements iMovimientoNegocio {
	
	private iMovimientoDao iMDao;
	
	public MovimientoNegocio(){
		this.iMDao = new MovimientoDao();
	}
	
	@Override
	public int agregarPrestamoAMovimiento(Prestamo prestamoAprobado) {
		return iMDao.agregarPrestamoAMovimiento(prestamoAprobado);
	}

	@Override
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int id_cuenta) {
		return iMDao.ListarMovimientosPorCuenta(id_cuenta);
	}

	@Override
	public ArrayList<Movimiento> Listar() {
		return iMDao.Listar();
	}

	@Override
	public ArrayList<Movimiento> ListarEntreFechas(int id_cuenta, java.util.Date fecha_inicio, java.util.Date fecha_fin) {
		return iMDao.ListarEntreFechas(id_cuenta, fecha_inicio, fecha_fin);
	}

}
