import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Saludo extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head>");
		out.println("<body>");
		
		String nombreUsuario = (String)request.getParameter("nombre");

		Cookie[] galletas = request.getCookies();
		
		out.println("<article>");
		
		if (galletas != null) {
		
			Cookie galleta = null;
			
			for (int i = 0; i < galletas.length; i++) {
				if ((nombreUsuario+"_pas").equalsIgnoreCase(galletas[i].getName()))
					galleta = galletas[i];
			}
			
			if (galleta != null){
				
				
				out.println("<h2>Bienvenido "+nombreUsuario+", tus países seleccionados son</h2>");
		
		
				out.println("<ul>");
			
				String paisesUsuario = galleta.getValue();
				
				String[] paisesUsuarioSplit = paisesUsuario.split("-"); 
				
					for (String pais : paisesUsuarioSplit) {
			
						String paisNormalizado = "";
						paisNormalizado = pais.toLowerCase();
						paisNormalizado = Normalizer.normalize(paisNormalizado, Normalizer.Form.NFD);
						out.println("<li><img src='imagenes/"+paisNormalizado+".png'</li>");
				
			
					}

				out.println("</ul>");
				
			
			} else {
				out.println("<h1>No existe el usuario "+nombreUsuario+"</h1>");
			}
	
		} else {
			out.println("<h1>No existen usuarios registrados</h1>");
		} 
		
		out.println("</article>");
		
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