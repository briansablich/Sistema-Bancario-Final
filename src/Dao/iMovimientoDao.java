package Dao;

import java.util.ArrayList;

import Dominio.Movimiento;
import Dominio.Prestamo;

public interface iMovimientoDao {
	
	int agregarPrestamoAMovimiento(Prestamo prestamoAprobado);
	ArrayList<Movimiento> ListarMovimientosPorCuenta(int id_cuenta);
	ArrayList<Movimiento> Listar();
	ArrayList<Movimiento> ListarEntreFechas(int id_cuenta, java.util.Date fecha_inicio, java.util.Date fecha_fin);

}
