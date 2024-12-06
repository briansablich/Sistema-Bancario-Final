package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.CuentaDao;
import Dao.iCuentaDao;
import Dominio.Cuenta;

public class CuentaNegocio implements iCuentaNegocio {
	private iCuentaDao cDao;
	
	public CuentaNegocio() {
		this.cDao = new CuentaDao();
	}

	@Override
	public int modificarMontoACuenta(float montoNuevo, int id_cuenta) {
		return cDao.modificarMontoACuenta(montoNuevo, id_cuenta);
	}

	@Override
	public ArrayList<Cuenta> getListaCuentasPorCliente(int id_cliente) {
		return cDao.getListaCuentasPorCliente(id_cliente);
	}

	@Override
	public ArrayList<Cuenta> Listar() {
		return cDao.Listar();
	}

	@Override
	public Cuenta buscar_con_id(int id) {
		return cDao.buscar_con_id(id);
	}

	@Override
	public ArrayList<Cuenta> ListarConEstadoFalse() {
		return cDao.ListarConEstadoFalse();
	}

	@Override
	public ArrayList<Cuenta> ListarConEstadoTrue() {
		return cDao.ListarConEstadoTrue();
	}

	@Override
	public int agregarCuenta(Cuenta cuentaNueva) {
		return cDao.agregarCuenta(cuentaNueva);
	}

	@Override
	public int BajaLogicaCuenta(int idCuentaBaja) {
		return cDao.BajaLogicaCuenta(idCuentaBaja);
	}

	@Override
	public int AltaLogicaCuenta(int idCuentaAlta) {
		return cDao.AltaLogicaCuenta(idCuentaAlta);
	}

	@Override
	public long ModificarCuenta(Cuenta cuentaModificar) {
		return cDao.ModificarCuenta(cuentaModificar);
	}

	@Override
	public long generarCbu() {
		return cDao.generarCbu();
	}

	@Override
	public int cantidadRegistros() {
		return cDao.cantidadRegistros();
	}

	@Override
	public long generarNumeroCuenta() {
		return cDao.generarNumeroCuenta();
	}

	@Override
	public void realizarTransferencia(String cbuOrigen, String cbuDestino, double monto) throws SQLException {
		cDao.realizarTransferencia(cbuOrigen, cbuDestino, monto);
	}

	@Override
	public int cuentasDelCliente(int idCuenta) {
		return cDao.cuentasDelCliente(idCuenta);
	}

}
