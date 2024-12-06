package Dominio;

import Dominio.Cliente.ESTADO;

public class CuotasPrestamo {
	public enum ESTADO { Pendiente, Pagada};
	private int id_cuota;
	private int id_prestamo;
	private int numero_cuota;
	private float monto_cuota;
	private java.util.Date fecha_vencimiento;
	private java.util.Date fecha_pago;
	private ESTADO estado;
	
	public CuotasPrestamo() {
		super();
	}
	public int getId_cuota() {
		return id_cuota;
	}
	public void setId_cuota(int id_cuota) {
		this.id_cuota = id_cuota;
	}
	public int getId_prestamo() {
		return id_prestamo;
	}
	public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	public int getNumero_cuota() {
		return numero_cuota;
	}
	public void setNumero_cuota(int numero_cuota) {
		this.numero_cuota = numero_cuota;
	}
	public float getMonto_cuota() {
		return monto_cuota;
	}
	public void setMonto_cuota(float monto_cuota) {
		this.monto_cuota = monto_cuota;
	}
	public java.util.Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(java.util.Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public java.util.Date getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(java.util.Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public ESTADO getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		
		if (estado.equals("Pagada")) {
			this.estado = ESTADO.Pagada;		
		} else {
			this.estado = ESTADO.Pendiente;
		}
	}

}
