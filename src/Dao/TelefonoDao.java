package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Telefono;

public class TelefonoDao implements iTelefonoDao {
	
    private static final String selectAll = "SELECT `telefonos`.`id_telefono`, `telefonos`.`telefono` FROM `bd_banco`.`telefonos` WHERE `id_cliente` = ?;";
    private static final String insertarTelefono = "INSERT INTO `bd_banco`.`telefonos` ( `telefono`, `id_cliente` ) VALUES ( ?, ? );";
    private static final String modificarTelefono = "UPDATE `bd_banco`.`telefonos` SET `telefono` = ? WHERE `id_telefono` = ?;";
    
    private static final String modificarTelefonos = "UPDATE telefonos SET telefono1 = ?, telefono2 =? WHERE id_cliente =?";
    private static final String insertTelefonos = "INSERT INTO `bd_banco`.`telefonos` (telefono1, telefono2, id_cliente) VALUES (?, ?, ?)";
	
    public int AgregarTelefonos(String telefono1, String telefono2, int id_cliente) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(insertTelefonos);

            statement.setString(1, telefono1);
            statement.setString(2, telefono2);
            statement.setInt(3, id_cliente);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }

    public int ModificacionTelefonos(String telefonoModificado1, String telefonoModificado2, int id_cliente) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(modificarTelefonos);

            statement.setString(1, telefonoModificado1);
            statement.setString(2, telefonoModificado2);
            statement.setInt(3, id_cliente);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }

	
    @Override
	public ArrayList<Telefono> Listar_de(int id_cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Telefono> listadoTelefonos = new ArrayList<Telefono>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			statement.setInt(1, id_cliente);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {			
				listadoTelefonos.add(getTelefono(resultSet));
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
		
		return listadoTelefonos;
	}
    
    public int AgregarTelefono(int id_cliente, String telefono) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(insertarTelefono);
            statement.setString(1, telefono);
            statement.setInt(2, id_cliente);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }
    
    public int ModificarTelefono(int id, String telefono) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(modificarTelefono);
            statement.setString(1, telefono);
            statement.setInt(2, id);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }

    private Telefono getTelefono(ResultSet resultSet) {
		
		Telefono telefono = null;
		
		try {
			telefono = new Telefono();
			telefono.setId(resultSet.getInt("id_telefono"));
			telefono.setTelefono(resultSet.getString("telefono"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return telefono;
	}
	
}
