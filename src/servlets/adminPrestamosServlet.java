package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.Prestamo;
import Negocio.PrestamoNegocio;
/**
 * Servlet implementation class adminPrestamosServlet
 */
@WebServlet("/adminPrestamosServlet")
public class adminPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminPrestamosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnRechazar") != null)	{
	        int idParaRechazar = Integer.parseInt(request.getParameter("prestamoId"));
	        PrestamoNegocio preDao = new PrestamoNegocio();
	        int baja = preDao.rechazarPrestamo(idParaRechazar);
	        if (baja == 1) {
	            RequestDispatcher rd = request.getRequestDispatcher("adminPrestamos.jsp");
	            rd.forward(request, response);
	        }
		}
		if (request.getParameter("btnAprobar") != null)	{
			if(aprobarPrestamo(request,response)) {
				 RequestDispatcher rd = request.getRequestDispatcher("adminPrestamos.jsp");
		            rd.forward(request, response);
		            return;
			} else {
				 RequestDispatcher rd = request.getRequestDispatcher("adminPrestamoRechazado.jsp");
		            rd.forward(request, response);
		            return;
			}
		} else {
			mostrarPrestamos(request,response);			
		}
		//PrestamoDao pDao = new PrestamoDao();
		//pDao.finalizarPrestamo();
		
	}
	
	private boolean aprobarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int idParaAprobar = Integer.parseInt(request.getParameter("prestamoId"));
	        int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
	        PrestamoNegocio preDao = new PrestamoNegocio();
	        if (preDao.aprobarPrestamo(idParaAprobar, idCuentaDestino) != 0) return true;
	        else return false;
			
	}


    private void mostrarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Prestamo> listadoPrestamos = new ArrayList<>();
        try {
        	PrestamoNegocio cd = new PrestamoNegocio();
            listadoPrestamos = (ArrayList<Prestamo>) cd.Listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
		request.setAttribute("listadoPrestamos", listadoPrestamos);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminPrestamos.jsp");   
		requestDispatcher.forward(request, response);	
    }
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}











}
