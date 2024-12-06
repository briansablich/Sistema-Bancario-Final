package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import Dominio.CuotasPrestamo;
import Dominio.Prestamo;


public class CuotasPrestamoDao implements iCuotasPrestamoDao {
	
	
	private static final String getCuotasPorPrestamo = "SELECT * FROM cuotas_prestamos WHERE id_prestamo = ?";
	private static final String getCuota = "SELECT * FROM cuotas_prestamos WHERE id_cuota = ?";
	private static final String SPPagarCuota = "CALL PagarCuota(?,?,?)"; //cbu origen, id cuota a pagar, monto de la cuota
	private static final String GenerarCuotas = "INSERT INTO `bd_banco`.`cuotas_prestamos`(id_prestamo, numero_cuota, monto_cuota, fecha_vencimiento, estado) VALUES(?, ?, ?, ?, ?)";
	
	public CuotasPrestamoDao() {
		super();
	}
	
	public CuotasPrestamo buscarCuotaPorId (int id_cuota) {

		CuotasPrestamo cuota = new CuotasPrestamo();
		Connection conexion = null;
		ResultSet resultSet;
		PreparedStatement statement;
		
		try
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(getCuota);
			statement.setInt(1, id_cuota);
			resultSet = statement.executeQuery();
			
			resultSet.next();
			cuota = (getCuota(resultSet));

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
		return cuota;
	}

	@Override
	public boolean pagarCuota(int id_cuota, String cbu_origen) {
		Connection conexion = null;
		CallableStatement callableStatement;
		try {
			CuotasPrestamo cuota = buscarCuotaPorId(id_cuota);
			conexion = conexionDB.getConnection();
			callableStatement = (CallableStatement) conexion.prepareCall(SPPagarCuota);
			
			
			callableStatement.setString(1, cbu_origen);
			callableStatement.setInt(2, cuota.getId_cuota());
			callableStatement.setFloat(3, cuota.getMonto_cuota());
			
			callableStatement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
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
		return true;
	}

	@Override
	public boolean generarCuotas(int id_prestamo, int cantidad_cuotas, float importe_total) {
		
		Connection conexion = null;
		PreparedStatement statement;		
		try
		{
			conexion = conexionDB.getConnection();
			float monto_cuota = importe_total/cantidad_cuotas;
			
			//guarda de a 1 todas las cuotas
			for (int x = 0; x < cantidad_cuotas; x++) {
				
				//seteo fecha de vencimiento
				java.util.Date utilDate = new java.util.Date();
				LocalDate localDate = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
				LocalDate fecha_vencimiento = localDate.plusMonths(x+1);
				java.sql.Date sqlDate = java.sql.Date.valueOf(fecha_vencimiento);
				
				statement = conexion.prepareStatement(GenerarCuotas);
				statement.setInt(1, id_prestamo);
				statement.setInt(2, x+1);
				statement.setFloat(3, monto_cuota);
				statement.setDate(4, sqlDate);
				statement.setString(5, "Pendiente");
				statement.executeUpdate();
				
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
			return false;
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
		return true;
	}

	private CuotasPrestamo getCuota(ResultSet resultSet) {
		CuotasPrestamo cuota = null;
		
		try {
			cuota = new CuotasPrestamo();
			cuota.setId_cuota(resultSet.getInt("id_cuota"));
			cuota.setId_prestamo(resultSet.getInt("id_prestamo"));
			cuota.setNumero_cuota(resultSet.getInt("numero_cuota"));
			cuota.setMonto_cuota(resultSet.getFloat("monto_cuota"));
			cuota.setFecha_vencimiento(resultSet.getDate("fecha_vencimiento"));
			cuota.setFecha_pago(resultSet.getDate("fecha_pago"));
			cuota.setEstado(resultSet.getString("estado"));
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return cuota;
	}

	@Override
	public ArrayList<CuotasPrestamo> listarCuotas(int id_prestamo) {
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CuotasPrestamo> listadoCuotasPorPrestamo = new ArrayList<CuotasPrestamo>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(getCuotasPorPrestamo);
			statement.setInt(1, id_prestamo);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoCuotasPorPrestamo.add(getCuota(resultSet));
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
		return listadoCuotasPorPrestamo;
	}
}
