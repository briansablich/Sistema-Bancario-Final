package Dao;

import java.util.ArrayList;
import Dominio.CuotasPrestamo;

public interface iCuotasPrestamoDao {
	boolean pagarCuota(int id_cuota, String cbu_origen);
	boolean generarCuotas(int id_prestamo, int cantidad_cuotas, float importe_total);
	ArrayList <CuotasPrestamo> listarCuotas(int id_prestamo);
	public CuotasPrestamo buscarCuotaPorId (int id_cuota);
}
