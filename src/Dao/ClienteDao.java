package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dominio.Cliente;
import Dominio.Pais;
import Dominio.Provincia;
import Dominio.Telefono;
import Dominio.Usuario;

public class ClienteDao implements iClienteDao{
	
	private static final String selectAll = "SELECT `clientes`.`id_cliente`, `clientes`.`DNI`, `clientes`.`CUIL`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`sexo`, `clientes`.`nacionalidad`, `clientes`.`fecha_nacimiento`, `clientes`.`direccion`, `clientes`.`localidad`, `clientes`.`provincia`, `clientes`.`correo_electronico`, `clientes`.`estado` FROM `bd_banco`.`clientes`;";
	private static final String buscarConId = "SELECT `clientes`.`id_cliente`, `clientes`.`DNI`, `clientes`.`CUIL`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`sexo`, `clientes`.`nacionalidad`, `clientes`.`fecha_nacimiento`, `clientes`.`direccion`, `clientes`.`localidad`, `clientes`.`provincia`, `clientes`.`correo_electronico`, `clientes`.`estado` FROM `bd_banco`.`clientes` WHERE `clientes`.`id_cliente` = ?;"; 
	private static final String insertCliente = "INSERT INTO `bd_banco`.`clientes`(DNI, CUIL, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo_electronico, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String deleteCliente = "DELETE FROM `bd_banco`.`clientes` WHERE id_cliente = ?;";
    private static final String bajaLogica = "UPDATE clientes SET estado = 'false' WHERE id_cliente = ?;";
    private static final String updateCliente = "UPDATE clientes SET DNI = ?, CUIL = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, localidad = ?, provincia = ?, correo_electronico = ?, estado = ? WHERE id_cliente = ?;";
    private static final String altaLogica = "UPDATE clientes SET estado = 'true' WHERE id_cliente = ?;";
    private static final String listarIdClientes = "SELECT id_cliente FROM bd_banco.clientes";
    private static final String checkDniQuery = "SELECT dni FROM Clientes WHERE dni = ?;";


    public int eliminarCliente(int id_cliente_borrar){

		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		
		try
		{
			conexion = conexionDB.getConnection();


			statement = conexion.prepareStatement(deleteCliente);
			statement.setInt(1, id_cliente_borrar);

			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El Cliente fue ELIMINADO correctamente...");
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

    @Override
	public int agregarCliente(Cliente clienteNuevo) {
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		UsuarioDao usuarioDao = new UsuarioDao();
		ResultSet resultSet = null;
		
		try
		{
			//String nuevoURL = host + dbName;
			//conexion = DriverManager.getConnection(nuevoURL, user, pass);
			conexion = conexionDB.getConnection();
			
			//crea el tipo de fecha compatible y lo asigna. Luego lo manda en el statement
			java.util.Date fechaUtil = clienteNuevo.getFechaNacimiento();
			java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
			
			statement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, clienteNuevo.getDni());
			statement.setString(2, clienteNuevo.getCuil());
			statement.setString(3, clienteNuevo.getNombre());
			statement.setString(4, clienteNuevo.getApellido());
			statement.setString(5, clienteNuevo.getSexo().name());
			statement.setString(6, clienteNuevo.getNacionalidad());
			statement.setDate(7, fechaSql);
			statement.setString(8, clienteNuevo.getDireccion());
			statement.setString(9, clienteNuevo.getLocalidad());
			statement.setInt(10, clienteNuevo.getProvincia().getId_provincia());
			statement.setString(11, clienteNuevo.getCorreoElectronico());
			statement.setString(12, clienteNuevo.getEstado().name());

			
			
			if(statement.executeUpdate() > 0)
			{
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int id_cliente = resultSet.getInt(1);
	                clienteNuevo.setId(id_cliente);
				}
				
	            Telefono telefonoPrimario = (clienteNuevo.getTelefonos()).get(0);
	            Telefono telefonoSecundario = (clienteNuevo.getTelefonos()).get(1);
		        TelefonoDao telefonoDao = new TelefonoDao();
		        telefonoDao.AgregarTelefono(clienteNuevo.getId(), telefonoPrimario.getTelefono());
		        telefonoDao.AgregarTelefono(clienteNuevo.getId(), telefonoSecundario.getTelefono());
							
				Usuario usuarioNuevo = new Usuario();
                usuarioNuevo.setUsuario(clienteNuevo.getCorreoElectronico());
                usuarioNuevo.setContrasena(clienteNuevo.getDni());
                usuarioNuevo.setAcceso("Cliente");
                usuarioNuevo.setCliente(clienteNuevo);
                usuarioNuevo.setEstado("True");
                
                if (usuarioDao.agregarUsuario(usuarioNuevo) == 1) {
                    System.out.println("El USUARIO fue insertado correctamente...");
                }
				filas = 1;
				System.out.println("El registro fue Insertado correctamente...");
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

	public Cliente buscar_con_id(int id) {

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Cliente cliente = null;
		
		try 
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(buscarConId);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			cliente = getCliente(resultSet);
		} 
		catch (SQLException e) 
		{
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
		return cliente;
	}
	
	private Cliente getCliente(ResultSet resultSet) {
		
		Cliente cliente = null;
		TelefonoDao telefonoDao = new TelefonoDao();
		ProvinciaDao provDao = new ProvinciaDao();
		
		try {
			cliente = new Cliente();
			cliente.setId(resultSet.getInt("id_cliente"));
			cliente.setDni(resultSet.getString("dni"));
			cliente.setCuil(resultSet.getString("cuil"));
			cliente.setNombre(resultSet.getString("nombre"));
			cliente.setApellido(resultSet.getString("apellido"));
			cliente.setSexo(resultSet.getString("sexo"));
			cliente.setNacionalidad(resultSet.getString("nacionalidad"));
			cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
			cliente.setDireccion(resultSet.getString("direccion"));
			cliente.setLocalidad(resultSet.getString("localidad"));
			cliente.setProvincia(provDao.getProvinciaConId(resultSet.getInt("provincia")));
			cliente.setCorreoElectronico(resultSet.getString("correo_electronico"));
			cliente.setTelefonos(telefonoDao.Listar_de(cliente.getId()));
			cliente.setEstado(resultSet.getString("estado"));	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public ArrayList<Cliente> Listar() {
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
				
				listadoClientes.add(getCliente(resultSet));
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
		
		return listadoClientes;
	}
	
	@Override
	public ArrayList<Cliente> ListarConEstadoFalse() {

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		ArrayList<Cliente> listadoClientesFalse = new ArrayList<Cliente>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoClientes.add(getCliente(resultSet));
	
			}
			for (int i = 0; i < listadoClientes.size(); i++) {
			    if (listadoClientes.get(i).getEstado() == Cliente.ESTADO.False) {
			        listadoClientesFalse.add(listadoClientes.get(i));
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
		return listadoClientesFalse;
	}
	
	public ArrayList<Cliente> ListarConEstadoTrue() {

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		ArrayList<Cliente> listadoClientesTrue = new ArrayList<Cliente>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoClientes.add(getCliente(resultSet));
	
			}
			for (int i = 0; i < listadoClientes.size(); i++) {
			    if (listadoClientes.get(i).getEstado() == Cliente.ESTADO.True) {
			        listadoClientesTrue.add(listadoClientes.get(i));
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
		return listadoClientesTrue;
	}
	
	@Override
	public int BajaLogicaCliente(int idClienteBaja) {

	
	    Connection conexion = null;
	    PreparedStatement pst = null;
	    int filas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        pst = conexion.prepareStatement(bajaLogica);
	        pst.setInt(1, idClienteBaja);
	
	        filas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    
	        if (pst != null) {
	            try {
	                pst.close();
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
	public int ModificacionCliente(Cliente clienteModificar) {
	
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    int filas = 0;

	    try {
	    	conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(updateCliente);
	        
	        //crea el tipo de fecha compatible y lo asigna. Luego lo manda en el statement
	        java.util.Date fechaUtil = clienteModificar.getFechaNacimiento();
	        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
	        
			statement.setString(1, clienteModificar.getDni());
			statement.setString(2, clienteModificar.getCuil());
			statement.setString(3, clienteModificar.getNombre());
			statement.setString(4, clienteModificar.getApellido());
			statement.setString(5, clienteModificar.getSexo().name());
			statement.setString(6, clienteModificar.getNacionalidad());
			statement.setDate(7, fechaSql);
			statement.setString(8, clienteModificar.getDireccion());
			statement.setString(9, clienteModificar.getLocalidad());
			statement.setInt(10, clienteModificar.getProvincia().getId_provincia());
			statement.setString(11, clienteModificar.getCorreoElectronico());
			statement.setString(12, clienteModificar.getEstado().name());
			statement.setInt(13,clienteModificar.getId());
	        filas = statement.executeUpdate();
	        
            Telefono telefonoPrimario = (clienteModificar.getTelefonos()).get(0);
            Telefono telefonoSecundario = (clienteModificar.getTelefonos()).get(1);
	        TelefonoDao telefonoDao = new TelefonoDao();
	        telefonoDao.ModificarTelefono(telefonoPrimario.getId(), telefonoPrimario.getTelefono());
	        telefonoDao.ModificarTelefono(telefonoSecundario.getId(), telefonoSecundario.getTelefono());
	        
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

	public int AltaLogicaCliente(int idClienteAlta) {
	
	    Connection conexion = null;
	    PreparedStatement pst = null;
	    int filas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        pst = conexion.prepareStatement(altaLogica);
	        pst.setInt(1, idClienteAlta);
	
	        filas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    
	        if (pst != null) {
	            try {
	                pst.close();
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

	public ArrayList<Integer> listarIdClientes() {
	    ArrayList<Integer> listaIdClientes = new ArrayList<>();

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(listarIdClientes); 
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            listaIdClientes.add(resultSet.getInt("id_cliente"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
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
	    return listaIdClientes;
	}

    public boolean existeDni(String dni) {
        boolean exists = false;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = conexionDB.getConnection();
            ps = conexion.prepareStatement(checkDniQuery);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return exists;
	
}
    }
