import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Salida extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='estiloLista.css'>");
		out.println("</head>");
		out.println("<body>");
		
		
		HttpSession miSesion = request.getSession();
		
		String nombreUsuario = (String) miSesion.getAttribute("nombre");
			
		ArrayList<String> articulosUsuario = (ArrayList<String>)miSesion.getAttribute("listaArticulos");
		
		GregorianCalendar tiempoInicio = (GregorianCalendar)miSesion.getAttribute("fechaConexion");
		
		GregorianCalendar tiempoFinal = new GregorianCalendar();
		
		int segundosTotal = (int)( (tiempoFinal.getTime().getTime() - tiempoInicio.getTime().getTime()) / 1000);
		
		out.println("<h1>Gracias por comprar con nosotros pelón "+nombreUsuario+"</h1>");
		
		out.println("<h2>Tiempo transcurrido en la aplicación: "+segundosTotal+" segundos</h2>");
		
		out.println("<table>");
		
		out.println("<th colspan='2' >Artículos en la cesta<th>");
		
		for (String articulo : articulosUsuario) {
			out.println("<tr>");
			out.print("<td class='foto'><img src='imagenes/"+articulo+".png'/></td>");
			out.println("<td>"+articulo+"</td>");
			out.println("</tr>");
		}
		
		out.println("</table>");
		
		miSesion.invalidate();
		
		out.println("<form action='index.html'>");
		out.println("<input type='submit' value = 'Volver a inicio'>");
		out.println("</form>");
		
		rd = getServletContext().getRequestDispatcher("/pie.html");
		rd.include(request, response);
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