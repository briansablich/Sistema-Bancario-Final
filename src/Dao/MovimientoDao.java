package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Movimiento;
import Dominio.Prestamo;
import Dominio.Usuario;

public class MovimientoDao implements iMovimientoDao {

	private static final String selectAll = "SELECT * FROM cuentas";
	private static final String movimientoPorCuenta = "SELECT * FROM `bd_banco`.`movimientos` WHERE id_cuenta_origen = ? OR id_cuenta_destino = ?";   
	private static final String insertMovimiento = "INSERT INTO `bd_banco`.`movimientos`(fecha, concepto, importe, id_tipo_movimiento, id_cuenta_origen, id_cuenta_destino) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String movimientosEntreFechas = "CALL BuscarEntreFechas( ?, ?, ?);";
	
	public int agregarPrestamoAMovimiento(Prestamo prestamoAprobado) {
		
		CuentaDao cDao = new CuentaDao();
		Cuenta cuentaAfectada = new Cuenta();
		
		cuentaAfectada = cDao.buscar_con_id(prestamoAprobado.getIdCuentaDestino());
		
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		String concepto = "PRESTAMO";
		int idTipoMovimiento = 1;
		float montoNuevo = prestamoAprobado.getImporteSolicitado() + cuentaAfectada.getSaldo();
		
		try
		{
			conexion = conexionDB.getConnection();
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			statement = conexion.prepareStatement(insertMovimiento);
			statement.setDate(1, sqlDate);
			statement.setString(2, concepto);
			statement.setFloat(3, prestamoAprobado.getImporteSolicitado());
			statement.setInt(4, idTipoMovimiento);
			statement.setInt(5, prestamoAprobado.getIdCuentaDestino());
			statement.setInt(6, prestamoAprobado.getIdCuentaDestino());

			filas = statement.executeUpdate();
			
			if(filas > 0)
			{
				System.out.println("PRESTAMO convertido a movimiento bancario correctamente a la cuenta: " + prestamoAprobado.getIdCuentaDestino());
				if(cDao.modificarMontoACuenta(montoNuevo, prestamoAprobado.getIdCuentaDestino()) > 0) {
					System.out.println("PRESTAMO Transferido a la cuenta: " + prestamoAprobado.getIdCuentaDestino());
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return filas;
	}
	
	public ArrayList<Movimiento> ListarMovimientosPorCuenta(int id_cuenta) {

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(movimientoPorCuenta);
			statement.setInt(1, id_cuenta);
			statement.setInt(2, id_cuenta);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
				Movimiento movAux = getMovimiento(resultSet);
				
				//si es transferencia luego pregunta si es egreso o ingreso de las cuentas
				if (movAux.getConcepto().equals("Transferencia")){
					if(movAux.getTipoMovimiento().getDescripcion().equals("egreso") && movAux.getId_cuenta_origen() == id_cuenta) {
						listadoMovimiento.add(movAux);
					} else if(movAux.getTipoMovimiento().getDescripcion().equals("ingreso") && movAux.getId_cuenta_origen() != id_cuenta) {
						listadoMovimiento.add(movAux);
					}
				} else {
				listadoMovimiento.add(movAux);
			}
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoMovimiento;
	}
	
	public ArrayList<Movimiento> Listar() {

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Movimiento> listadoMovimiento = new ArrayList<Movimiento>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
			listadoMovimiento.add(getMovimiento(resultSet));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoMovimiento;
	}
	
	public ArrayList<Movimiento> ListarEntreFechas(int id_cuenta, java.util.Date fecha_inicio, java.util.Date fecha_fin) {
		ArrayList<Movimiento> listadoMovimientos = new ArrayList<Movimiento>();
		Connection conexion = null;
		CallableStatement callableStatement;
		ResultSet resultSet;
		try {
			conexion = conexionDB.getConnection();
			callableStatement = (CallableStatement) conexion.prepareCall(movimientosEntreFechas);
			
			java.sql.Date sqlFechaInicio = new java.sql.Date(fecha_inicio.getTime());
			java.sql.Date sqlFechaFin = new java.sql.Date(fecha_fin.getTime());
			
			callableStatement.setDate(1, sqlFechaInicio);
			callableStatement.setDate(2, sqlFechaFin);
			callableStatement.setInt(3, id_cuenta);
			
			resultSet = callableStatement.executeQuery();
			
			while(resultSet.next()) {
				listadoMovimientos.add(getMovimiento(resultSet));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoMovimientos;
	}
	
	private Movimiento getMovimiento(ResultSet resultSet) {
		
		Movimiento movimiento = null;
		
		try {
			TipoMovimientoDao tmDao = new TipoMovimientoDao();
			movimiento = new Movimiento();
			
			movimiento.setId(resultSet.getInt("id_movimiento"));
			movimiento.setFecha(resultSet.getDate("fecha"));
			movimiento.setConcepto(resultSet.getString("concepto"));;
			movimiento.setImporte(resultSet.getFloat("importe"));
			movimiento.setTipoMovimiento(tmDao.getTipoMovimiento(resultSet.getInt("id_tipo_movimiento")));
			movimiento.setId_cuenta_origen(resultSet.getInt("id_cuenta_origen"));
			movimiento.setId_cuenta_destino(resultSet.getInt("id_cuenta_destino"));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movimiento;
	}
	
	public void agregarAltaCuentaAMovimiento(Cuenta cuentaAgregada) {		
		
		Connection conexion = null;
		PreparedStatement statement;
		int idTipoMovimiento = 1;
		
		try
		{
			conexion = conexionDB.getConnection();
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			statement = conexion.prepareStatement(insertMovimiento);
			statement.setDate(1, sqlDate);
			statement.setString(2, "Alta Cuenta");
			statement.setFloat(3, 10000);
			statement.setInt(4, idTipoMovimiento);
			statement.setInt(5, cuentaAgregada.getIdCuenta());
			statement.setInt(6, cuentaAgregada.getIdCuenta());

			statement.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
}
