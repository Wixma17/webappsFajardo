import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class CalculoTabla extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		
		// Sacamos el capital inicial por parámetros
		double capitalInicial = Double.parseDouble(request.getParameter("capital"));
		
		// Sacamos el interés por parámetros
		double interes = Double.parseDouble(request.getParameter("interes"));
		
		// Sacamos los años por parámetros
		int years = Integer.parseInt(request.getParameter("years"));
		
		// Sacamos la periodicidad por parámetros
		int periodicidad = Integer.parseInt(request.getParameter("periodicidad"));
		
		// Interés sobre la fórmula de la cuota
		double i = (interes/100) / periodicidad;
		
		// Multiplicamos tantas facturas por año
		int n = years * periodicidad;

		// Inicializamos un Array bidimensional de doubles en los que haya tantas filas como
		// facturas en total haya (n en este caso)
		double[][] datosTabla = new double[n][3];
		
		//Cuota =CI* (i*Math.pow ((1+i),n))/(Math.pow((1+i),n)-1)
		double cuota = capitalInicial * (i * Math.pow((i+1),n))/(Math.pow((1+i),n)-1);
		
		// Declaramos las variables
		double interesRecibo;
		double capitalAmortizado;
		
		// Inicializamos el capital pendiente al inicial
		double capitalPendiente = capitalInicial;
		
		for (int in = 0; in < datosTabla.length; in++) {
			
			// Calculamos el interés del recibo, multiplicando i ((interes/100) / periodicidad) por el capital pendiente
			// que se va reduciendo por cada iteración
			interesRecibo = capitalPendiente * i;
			
			// Se calcula el capital amortizado restándole a la cuota el interés que se ha pagado en esta ocasión
			capitalAmortizado = cuota - interesRecibo;
			
			// Reducimos del monto restante la cantidad de capital amortizada
			capitalPendiente = capitalPendiente - capitalAmortizado;
			
			// Introducimos estos datos en nuestra matriz, utilizando el método para
			// pasar los datos a valores absolutos para eliminar el posible -0 que aparece en el formato
			datosTabla[in][0] = Math.abs(interesRecibo);
			datosTabla[in][1] = Math.abs(capitalAmortizado);
			datosTabla[in][2] = Math.abs(capitalPendiente);
		}
		
		// Ponemos dos atributos en la cabecera de la petición, uno con la tabla que hemos creado y otro con la cuota
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