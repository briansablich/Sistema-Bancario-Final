package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.ClienteSaldo;
import Dominio.Movimiento;
import Dominio.Provincia;
import Negocio.ClienteSaldoNegocio;
import Negocio.MovimientoNegocio;
import Negocio.ProvinciaNegocio;

/**
 * Servlet implementation class adminInformesServlet
 */
@WebServlet("/adminInformesServlet")
public class adminInformesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminInformesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("btnInforme1")!= null) {
			BuscarEntreFechas(request, response);
		}
		
		
	}

	private void BuscarEntreFechas (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MovimientoNegocio movNeg = new MovimientoNegocio();
		try {
			String fechaInicioParam = request.getParameter("fecha_inicio");
			String fechaFinParam = request.getParameter("fecha_fin");
			
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fecha_inicio = formatoFecha.parse(fechaInicioParam);
			java.util.Date fecha_fin = formatoFecha.parse(fechaFinParam);
			int id_cuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
			
			ArrayList<Movimiento> listadoMovimientos = movNeg.ListarEntreFechas(id_cuenta, fecha_inicio, fecha_fin);
			request.setAttribute("listadoMovimientos", listadoMovimientos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminInformes.jsp");   
			requestDispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
