import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class CalculoTabla extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		double capitalInicial = Double.parseDouble(request.getParameter("capital"));
		
		// Interés de la deuda = 0.02 anual, 0.02/2 semestral, 0.02/4 trimestral,0.02/12 mensual
		double interes = Double.parseDouble(request.getParameter("interes"));
		
		int years = Integer.parseInt(request.getParameter("years"));
		int periodicidad = Integer.parseInt(request.getParameter("periodicidad"));
		
		// Interés sobre la fórmula de la cuota
		double i = (interes/100) / periodicidad;
		
		// Plazos
		int n = years * periodicidad;

		double[][] datosTabla = new double[n][3];
		
		//Cuota =CI* (i*Math.pow ((1+i),n))/(Math.pow((1+i),n)-1)
		double cuota = capitalInicial * (i * Math.pow((i+1),n))/(Math.pow((1+i),n)-1);
		
		/*
		InterésRecibo = capitalpendiente*i
		capitalAmortizado=cuota- InterésRecibo
		capitalPendiente=capitalPendiente-capitalAmortizado
		*/

		double interesRecibo;
		
		double capitalAmortizado;

		double capitalPendiente = capitalInicial;
		
		for (int in = 0; in < datosTabla.length; in++) {
			
			interesRecibo = capitalPendiente * i;
			capitalAmortizado = cuota - interesRecibo;
			capitalPendiente = capitalPendiente - capitalAmortizado;
			
			datosTabla[in][0] = Math.abs(interesRecibo);
			datosTabla[in][1] = Math.abs(capitalAmortizado);
			datosTabla[in][2] = Math.abs(capitalPendiente);
		}
		
		request.setAttribute("tablaAmortizaciones", datosTabla);
		request.setAttribute("cuota", cuota);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response);
	}
	
	
}