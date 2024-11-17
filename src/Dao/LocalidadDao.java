package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.Localidad;

public class LocalidadDao implements iLocalidadDao {
	
	private static final String selectLocalidad = "SELECT * FROM localidades WHERE id_localidad = ?";
	private static final String selectLocalidadPorProvincia = "SELECT * FROM localidades WHERE id_provincia = ?";
	private static final String selectTodasLasLocalidades = "SELECT * FROM localidades";

	@Override
	public Localidad getLocalidadConId(int id_localidad) {
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Localidad localidad = new Localidad();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectLocalidad);
			statement.setInt(1,id_localidad);
			resultSet = statement.executeQuery();
			resultSet.next();
			localidad = getLocalidad(resultSet);
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
		
		return localidad;
	}

	@Override
	public List<Localidad> getLocalidadesPorProvincia(int id_provincia) {
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> listadoLocalidades = new ArrayList<Localidad>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectLocalidadPorProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoLocalidades.add(getLocalidad(resultSet));
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
		
		return listadoLocalidades;
	}
	
	@Override
	public List<Localidad> getTodasLasLocalidades() {
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Localidad> listadoLocalidades = new ArrayList<Localidad>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectTodasLasLocalidades);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoLocalidades.add(getLocalidad(resultSet));
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
		
		return listadoLocalidades;
	}
	
	private Localidad getLocalidad(ResultSet resultSet) {
		
		Localidad localidad = null;
		
		try {
			ProvinciaDao provDao = new ProvinciaDao();
			localidad = new Localidad();
			localidad.setId_localidad(resultSet.getInt("id_localidad"));
			localidad.setNombreLocalidad(resultSet.getString("localidad"));
			localidad.setProvincia(provDao.getProvinciaConId(resultSet.getInt("id_provincia")));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return localidad;
	}


}
