package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dominio.Provincia;
import Dominio.Telefono;
import Negocio.ClienteNegocio;
import Negocio.ProvinciaNegocio;

/**
 * Servlet implementation class modificaClientesServlet
 */
@WebServlet("/crearModificarClienteServlet")
public class crearModificarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearModificarClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        ProvinciaNegocio provNegocio = new ProvinciaNegocio();
        ArrayList<Provincia> listadoProvincias = (ArrayList<Provincia>) provNegocio.getListaProvincias();
        request.setAttribute("listadoProvincias", listadoProvincias);
        
		if (request.getParameter("btnModificar") != null && request.getParameter("btnModificar").toString().equals("MODIFICAR")) {
        	int idParaModificar = Integer.parseInt(request.getParameter("clienteId"));
        	ClienteNegocio clienteNegocio = new ClienteNegocio();
            Cliente cliente = clienteNegocio.buscar_con_id(idParaModificar);
            System.out.println("Id: " + idParaModificar);
            System.out.println("Cliente: " + cliente.toString());
            request.setAttribute("cliente", cliente);
            RequestDispatcher rd = request.getRequestDispatcher("/adminCrearModificarCliente.jsp");
            rd.forward(request, response);
            
        } else if (request.getParameter("btnCrear") != null && request.getParameter("btnCrear").toString().equals("CREAR")) { 
            Cliente cliente = null;
            request.setAttribute("cliente", cliente);
            RequestDispatcher rd = request.getRequestDispatcher("/adminCrearModificarCliente.jsp");
            rd.forward(request, response);
            
        } else if (request.getParameter("crearModificarCliente").toString().equals("ModificarCliente")) {
        	int idParaModificar = Integer.parseInt(request.getParameter("idModificar"));
        	
            // Validaci�n de campos
            if (validarCamposCliente(request)) {
                // Procesamiento para modificar cliente
                java.util.Date dateNacimiento = null;
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                
                try {
                    dateNacimiento = formatoFecha.parse(request.getParameter("fechaNacimiento"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                ClienteNegocio clienteNegocio = new ClienteNegocio();
                Cliente cliente = clienteNegocio.buscar_con_id(idParaModificar);
                cliente.setDni(request.getParameter("dni"));
                cliente.setCuil(request.getParameter("cuil"));
                cliente.setNombre(request.getParameter("nombre"));
                cliente.setApellido(request.getParameter("apellido"));
                cliente.setSexo(request.getParameter("sexo"));
                cliente.setFechaNacimiento(dateNacimiento);
                cliente.setNacionalidad(request.getParameter("nacionalidad"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setLocalidad(request.getParameter("localidad"));
                
                Provincia provSeleccionada = null;
                for (Provincia provincia : listadoProvincias) {
                    if (provincia.getNombre_provincia().equals(request.getParameter("provincia"))) {
                    	provSeleccionada = provincia;
                        break;
                    }
                }
                cliente.setProvincia(provSeleccionada);
                cliente.setCorreoElectronico(request.getParameter("email"));
                
                Telefono telefonoPrimario = null;
                
                try {
                	telefonoPrimario = (cliente.getTelefonos()).get(0);
                }  catch (IndexOutOfBoundsException e) {
                	telefonoPrimario = new Telefono();
                }
                
                Telefono telefonoSecundario = null;
                try {
                	telefonoSecundario = (cliente.getTelefonos()).get(1);
                }  catch (IndexOutOfBoundsException e) {
                	telefonoSecundario = new Telefono();
                }
                
                telefonoPrimario.setTelefono(request.getParameter("telefonoPrimario"));
                telefonoSecundario.setTelefono(request.getParameter("telefonoSecundario"));
                ArrayList<Telefono> telefonos = new ArrayList<Telefono>();
                telefonos.add(telefonoPrimario);
                telefonos.add(telefonoSecundario);
                cliente.setTelefonos(telefonos);
                
                cliente.setEstado("True");
                cliente.setId(idParaModificar);

                clienteNegocio.ModificacionCliente(cliente);
                RequestDispatcher rd = request.getRequestDispatcher("adminClientesServlet");
                rd.forward(request, response);
                
            } else {

            }

        } else if (request.getParameter("crearModificarCliente").toString().equals("CrearCliente")) {
            int filasAgregadas = 0;
        	System.out.println("Entre a crear!!!");
            // Validaci�n de campos
            if (validarCamposCliente(request)) {
            	// Verificar si el DNI ya existe
            	ClienteNegocio clienteDao = new ClienteNegocio();
            	   String dni = request.getParameter("dni");
            	   if (clienteDao.existeDni(dni)) {
            	       // Manejo de error: DNI duplicado
            	       request.setAttribute("errorMessage", "DNI duplicado");
            	       RequestDispatcher rd = request.getRequestDispatcher("/DniDuplicadoError.jsp");
            	       rd.forward(request, response);
            	   }
            	
            	
                // Procesamiento para agregar cliente
                java.util.Date dateNacimiento = null;
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateNacimiento = formatoFecha.parse(request.getParameter("fechaNacimiento"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Cliente cliente = new Cliente();
                cliente.setDni(request.getParameter("dni"));
                cliente.setCuil(request.getParameter("cuil"));
                cliente.setNombre(request.getParameter("nombre"));
                cliente.setApellido(request.getParameter("apellido"));
                cliente.setSexo(request.getParameter("sexo"));
                cliente.setFechaNacimiento(dateNacimiento);
                cliente.setNacionalidad(request.getParameter("nacionalidad"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setLocalidad(request.getParameter("localidad"));
                
                Provincia provSeleccionada = null;
                int idProv = Integer.parseInt(request.getParameter("provincia"));
                for (Provincia provincia : listadoProvincias) {
                    if (provincia.getId_provincia() == idProv) {
                    	provSeleccionada = provincia;
                        break;
                    }
                }
                cliente.setProvincia(provSeleccionada);
                cliente.setCorreoElectronico(request.getParameter("email"));
                

                Telefono telefonoPrimario = new Telefono();
                Telefono telefonoSecundario = new Telefono();
                telefonoPrimario.setTelefono(request.getParameter("telefonoPrimario"));
                telefonoSecundario.setTelefono(request.getParameter("telefonoSecundario"));
                ArrayList<Telefono> telefonos = new ArrayList<Telefono>();
                telefonos.add(telefonoPrimario);
                telefonos.add(telefonoSecundario);
                cliente.setTelefonos(telefonos);
                
                cliente.setEstado("True");

                ClienteNegocio cd = new ClienteNegocio();
                filasAgregadas = cd.agregarCliente(cliente);
                System.out.println(filasAgregadas);
                RequestDispatcher rd1 = request.getRequestDispatcher("adminClientesServlet");
                rd1.forward(request, response);
            } else {
                // Manejo de error: campos incompletos o inv�lidos
                // Podr�as redirigir a un JSP de error o mostrar un mensaje adecuado
            	System.out.println("Se fue de viaje por que es una cacota1!!!");
            }

        }
        else {
        	System.out.println("Se fue de viaje por que es una cacota!!!");
        }
            
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}
	
    private boolean validarCamposCliente(HttpServletRequest request) {
        return request.getParameter("dni") != null && !request.getParameter("dni").isEmpty() &&
               request.getParameter("cuil") != null && !request.getParameter("cuil").isEmpty() &&
               request.getParameter("nombre") != null && !request.getParameter("nombre").isEmpty() &&
               request.getParameter("apellido") != null && !request.getParameter("apellido").isEmpty() &&
               request.getParameter("sexo") != null && !request.getParameter("sexo").isEmpty() &&
               request.getParameter("nacionalidad") != null && !request.getParameter("nacionalidad").isEmpty() &&
               request.getParameter("direccion") != null && !request.getParameter("direccion").isEmpty() &&
               request.getParameter("localidad") != null && !request.getParameter("localidad").isEmpty() &&
               request.getParameter("provincia") != null && !request.getParameter("provincia").isEmpty() &&
               request.getParameter("email") != null && !request.getParameter("email").isEmpty() &&
               request.getParameter("fechaNacimiento") != null && !request.getParameter("fechaNacimiento").isEmpty();
    }
	


}
