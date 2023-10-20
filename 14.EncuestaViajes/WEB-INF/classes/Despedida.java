import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Despedida extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head>");
		out.println("<body>");
		
		String nombreUsuario;
		
		String[] paisesSeleccionados = request.getParameterValues("paises");
		
		nombreUsuario = (String) request.getParameter("nombre");
		
		out.println("<h2>Bienvenido "+nombreUsuario+"</h2>");
		
		if (paisesSeleccionados != null){
		
		out.println("<h2>Enhorabuena, los destines seleccionedes son les siguientes: </h2>");
		
		String paisesTotal = "";
		
		out.println("<ul>");
		
			for (int i = 0; i < paisesSeleccionados.length - 1; i++) {
				
				out.println("<li>"+paisesSeleccionados[i]+"</li>");
				paisesTotal += (paisesSeleccionados[i] + "-");
			
			}
			
			out.println("<li>"+paisesSeleccionados[paisesSeleccionados.length - 1]+"</li>");
			paisesTotal += (paisesSeleccionados[paisesSeleccionados.length - 1]);
			
		out.println("</ul>");
		
		out.println("<p>"+paisesTotal+"</p>");
		
		nombreUsuario += "_pas";
		
		Cookie galleta = new Cookie(nombreUsuario, paisesTotal);
		galleta.setPath("/");
		response.addCookie(galleta);
		
		} else {out.println("<h2>No ha seleccionado ningún país, jolines</h2>");   }
		
		out.println("<a href='index.html'>Volver al Índice</a>");
		
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