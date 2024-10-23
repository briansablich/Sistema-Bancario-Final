package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Cliente;
import Dao.ClienteDao;

import java.util.ArrayList;

/**
 * Servlet implementation class adminClientesServlet
 */
@WebServlet("/adminClientesServlet")
public class adminClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
        
        try {
            ClienteDao cd = new ClienteDao();
            listadoClientes = cd.Listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        request.setAttribute("listadoClientes", listadoClientes);
        RequestDispatcher rd = request.getRequestDispatcher("/adminClientes.jsp");
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
