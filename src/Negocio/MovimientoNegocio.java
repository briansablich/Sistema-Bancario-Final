package Negocio;

import java.util.ArrayList;

import Dao.MovimientoDao;
import Dao.iMovimientoDao;
import Dominio.Movimiento;
import Dominio.Prestamo;

public class MovimientoNegocio implements iMovimientoNegocio {
	
	private iMovimientoDao iMDao;
	
	MovimientoNegocio(){
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

}
