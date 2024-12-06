package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Cuenta;

public interface iCuentaNegocio {
	int modificarMontoACuenta(float montoNuevo,  int id_cuenta);
	ArrayList<Cuenta> getListaCuentasPorCliente(int id_cliente);
	ArrayList <Cuenta> Listar();
	Cuenta buscar_con_id(int id);
	ArrayList<Cuenta> ListarConEstadoFalse();
	ArrayList<Cuenta> ListarConEstadoTrue();
	int agregarCuenta(Cuenta cuentaNueva);
	int BajaLogicaCuenta(int idCuentaBaja);
	int AltaLogicaCuenta(int idCuentaAlta);
	long ModificarCuenta(Cuenta cuentaModificar);
	long generarCbu();
	int cantidadRegistros();
	long generarNumeroCuenta();
	void realizarTransferencia(String cbuOrigen, String cbuDestino, double monto) throws SQLException;
	int cuentasDelCliente(int idCuenta);
}
