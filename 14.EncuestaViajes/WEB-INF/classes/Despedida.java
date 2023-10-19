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
		
		nombreUsuario = (String) miSesion.getAttribute("nombreUsuario");
		
		listaArticulosUsuario = (ArrayList<String>)miSesion.getAttribute("listaArticulos");
		
		
		
		out.println("<h2>Bienvenido "+nombreUsuario+"</h2>");
		
		
		
		if (articulosSeleccionados != null){
		out.println("<ul>");
		
			for (int i = 0; i < articulosSeleccionados.length; i++) {
				out.println("<li>"+articulosSeleccionados[i]+"</li>");
				listaArticulosUsuario.add(articulosSeleccionados[i]);
			}
		out.println("</ul>");
		}
		

		
		out.println("<p>");
		for (int i = 0; i < listaArticulosUsuario.size() - 1; i++){
			out.println(listaArticulosUsuario.get(i)+" - ");
		}
		out.println(listaArticulosUsuario.get(listaArticulosUsuario.size() - 1));
		out.println("</p>");
		
		out.println("<a href='index.html'>Volver al Índice</a>")
		
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