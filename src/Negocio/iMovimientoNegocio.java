package Negocio;

import java.util.ArrayList;

import Dominio.Movimiento;
import Dominio.Prestamo;

public interface iMovimientoNegocio {
	
	int agregarPrestamoAMovimiento(Prestamo prestamoAprobado);
	ArrayList<Movimiento> ListarMovimientosPorCuenta(int id_cuenta);
	ArrayList<Movimiento> Listar();
}
