package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.scene.paint.GradientUtils.Parser;

import Dao.ClienteDao;

/**
 * Servlet implementation class eliminarClienteServlet
 */
@WebServlet("/eliminarClienteServlet")
public class eliminarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarClienteServlet() {
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
        int idParaBorrar = Integer.parseInt(request.getParameter("clienteId"));
        ClienteDao cliDao = new ClienteDao();
        int baja = cliDao.BajaLogicaCliente(idParaBorrar);

        if (baja == 1) {
            RequestDispatcher rd = request.getRequestDispatcher("adminClientesServlet");
            rd.forward(request, response);
        }
	}

}
