package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.CuotasPrestamo;
import Dominio.Pago;
import Negocio.CuotasPrestamoNegocio;
import Negocio.PagoNegocio;

@WebServlet("/PortalPagosBancoServlet")
public class PortalPagosBancoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PortalPagosBancoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnPagarPrestamo")!=null) {
			generarListaDePago(request, response);
        } else if (request.getParameter("btnPagarCuota")!=null) {
        	pagarCuota(request, response);
        }
		
	}
	
	protected void pagarCuota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuotasPrestamoNegocio cpn = new CuotasPrestamoNegocio();
		String cbu = request.getParameter("cbuCuenta");
		int id_cuota = Integer.parseInt(request.getParameter("CuotaId"));
		cpn.pagarCuota(id_cuota, cbu);
		
//		ArrayList<CuotasPrestamo> listaCuotasPorPrestamo = new ArrayList<>();
//		int idPrestamo = Integer.parseInt(request.getParameter("prestamoId"));
//		listaCuotasPorPrestamo = (ArrayList<CuotasPrestamo>) cpn.listarCuotas(idPrestamo);
//		request.setAttribute("idPrestamo", idPrestamo);
//		request.setAttribute("listaCuotasPorPrestamo", listaCuotasPorPrestamo);
		RequestDispatcher rd = request.getRequestDispatcher("/clientePagoPrestamos.jsp");
        rd.forward(request, response);
	}

	protected void generarListaDePago(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuotasPrestamoNegocio cpn = new CuotasPrestamoNegocio();
		// aca listar
		ArrayList<CuotasPrestamo> listaCuotasPorPrestamo = new ArrayList<>();
		int idPrestamo = Integer.parseInt(request.getParameter("prestamoId"));
		listaCuotasPorPrestamo = (ArrayList<CuotasPrestamo>) cpn.listarCuotas(idPrestamo);
		
		request.setAttribute("idPrestamo", idPrestamo);
		request.setAttribute("listaCuotasPorPrestamo", listaCuotasPorPrestamo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/PortalDePagosClientes.jsp");
        rd.forward(request, response);
        
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
