package Negocio;

import java.util.ArrayList;

import Dao.CuotasPrestamoDao;
import Dao.iCuotasPrestamoDao;
import Dominio.CuotasPrestamo;

public class CuotasPrestamoNegocio implements iCuotasPrestamoNegocio {

	private iCuotasPrestamoDao iCP;
	
	public CuotasPrestamoNegocio() {
		this.iCP = new CuotasPrestamoDao();
	}
	
	@Override
	public boolean pagarCuota(int id_cuota, String cbu_origen) {
		return iCP.pagarCuota(id_cuota, cbu_origen);
	}

	@Override
	public boolean generarCuotas(int id_prestamo, int cantidad_cuotas, float importe_total) {
		return iCP.generarCuotas(id_prestamo, cantidad_cuotas, importe_total);
	}

	@Override
	public ArrayList<CuotasPrestamo> listarCuotas(int id_prestamo) {
		return iCP.listarCuotas(id_prestamo);
	}

	@Override
	public CuotasPrestamo buscarCuotaPorId(int id_cuota) {
		return iCP.buscarCuotaPorId(id_cuota);
	}

}
