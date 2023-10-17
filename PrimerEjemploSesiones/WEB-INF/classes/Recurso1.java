import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

public class Recurso1 extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	//RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		//rd.include(request, response);
	
		out.println("Saludo sesion.mp3");
		HttpSession miSesion = request.getSession();
		if (miSesion.isNew()){
			out.println("Soy nuevo");
			miSesion.setAttribute("horaCreacion", new GregorianCalendar());
		}
		
		out.println("Son las "+ new GregorianCalendar().getTime());
		out.println("La sesión se creó a las: "+((GregorianCalendar)miSesion.getAttribute("horaCreacion")).getTime());
	
		// Para Servlets encargados de la salida
		//rd = getServletContext().getRequestDispatcher("/pie.html");
		//rd.include(request, response);
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