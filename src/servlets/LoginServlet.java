package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Usuario;
import Exception.failLoginException;
import Negocio.UsuarioNegocio;
import Negocio.iUsuarioNegocio;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		iUsuarioNegocio usuarioNegocio ;
		Usuario usuario = null;

		try
		{
	        String usuarioAux = request.getParameter("username");
	        String contraseniaAux = request.getParameter("password");
			usuarioNegocio = new UsuarioNegocio();
			
			if(usuarioNegocio.validarLogin(usuarioAux, contraseniaAux)) {
				usuario = usuarioNegocio.obtenerUsuario(usuarioAux, contraseniaAux);

		        	if (usuario.getAcceso() == "Administrador") {
						request.getSession().setAttribute("usuario", usuario);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/PortalAdministradores.jsp");   
						requestDispatcher.forward(request, response);	
					} else if (usuario.getAcceso() == "Cliente") {
						request.getSession().setAttribute("usuario", usuario);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/clientePerfilServlet");   
						requestDispatcher.forward(request, response);
					}
		        }
			else {
				throw new failLoginException();
			}
		} 
		catch (failLoginException e) {
	        request.setAttribute("exception", e);
	        request.setAttribute("paginaOrigen", "Login.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
			requestDispatcher.forward(request, response);
		}
		catch (NullPointerException e) { // TO DO: Corregir esta excepcion por algo mas acorde.
	        request.setAttribute("exception", "No se pudo establecer la comunicacion con la base de datos...");
	        request.setAttribute("paginaOrigen", "Login.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
			requestDispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("paginaOrigen", "Login.jsp");
			request.setAttribute("exception", "Uppsss... Algo salio muy mal!!!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ErrorPage.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
