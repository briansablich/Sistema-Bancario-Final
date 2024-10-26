package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.ClienteNegocio;

/**
 * Servlet implementation class activaClienteServlet
 */
@WebServlet("/activaClienteServlet")
public class activaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activaClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idParaActivar = Integer.parseInt(request.getParameter("clienteId"));

        ClienteNegocio cliDao = new ClienteNegocio();
        int alta = cliDao.AltaLogicaCliente(idParaActivar);

        if (alta == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminClientesServlet");
            rd.forward(request, response);
        }
	}

}
