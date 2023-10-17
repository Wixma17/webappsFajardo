import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class PintarTabla extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		double capitalInicial = Double.parseDouble(request.getParameter("capital"));
		
		// Interés de la deuda = 0.02 anual, 0.02/2 semestral, 0.02/4 trimestral,0.02/12 mensual
		double interes = Double.parseDouble(request.getParameter("interes"));
		
		int years = Integer.parseInt(request.getParameter("years"));
		
		int periodicidad = Integer.parseInt(request.getParameter("periodicidad"));
		
		double[][] datosTabla = (double[][])request.getAttribute("tablaAmortizaciones");

		double cuota = (double)request.getAttribute("cuota");
		
		int longitudTabla = datosTabla.length;
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<article>");
			out.println("<h1>Resultados de la tabla de amortizaciones</h1>");
			out.println("<h2>Capital Inicial: "+df.format(capitalInicial)+" Euros</h2>");
			out.println("<h2>Interés: "+df.format(interes)+"%</h2>");
			out.println("<h2>Años: "+years+"</h2>");
			out.println("<h2>Facturas por año: "+periodicidad+"</h2>");
			out.println("<h2>Cuota: "+df.format(cuota)+" Euros </h2>");
		out.println("</article>");
		
		
		
		int contadorYears = 0;
		int contadorPeriodicidad = 0;
		int posicion = 0;
		
		double sumaInteres = 0;
		double sumaCapitalAmortizado = 0;
		
		while (contadorYears < years){
		
			
			out.println("<table>");
			out.println("<caption><h3>Año: "+(contadorYears+1)+"</caption></h3>");
			out.println("<tr>");
			out.println("<th>Número de factura</th>");
			out.println("<th>Interés pagado</th>");
			out.println("<th>Capital amortizado</th>");
			out.println("<th>Capital pendiente</th>");
			out.println("</tr>");
				while (contadorPeriodicidad < periodicidad){
					out.println("<tr>"); 
					out.println("<td>"+(posicion+1)+"</td>");
					for (int k = 0; k < datosTabla[posicion].length; k++){
						out.println("<td>"+df.format(datosTabla[posicion][k])+" Euros </td>");
					}

					out.println("</tr>");
	
					sumaInteres += datosTabla[posicion][0];
					sumaCapitalAmortizado += datosTabla[posicion][1];
	
					contadorPeriodicidad++;
					posicion++;
				
				}
				
			contadorPeriodicidad = 0;
			contadorYears++;
			
			out.println("<tr>");
			out.println("<td colspan='2'>Total interés: "+df.format(sumaInteres)+"</td>");
			out.println("<td colspan='2'>Total capital amortizado: "+df.format(sumaCapitalAmortizado)+"</td>");
			out.println("</tr>");
			
			sumaInteres = 0;
			sumaCapitalAmortizado = 0;
			
			out.println("</table>");
			
			
		}
		
		out.println("</body>");
		out.println("</html>");
		
		
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