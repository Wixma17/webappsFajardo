import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class CrearCookies extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='estiloCreacion.css'>");
		out.println("</head>");
		out.println("<body>");
			/*	<label for="nombre">Nombre de la cookie:</label>
		<input type="text" name="nombre" id="">
		<label for="valor">Valor de la cookie:</label>
		<input type="text" name="valor" id="">
		<label for="tiempoVida">Tiempo de vida (en segundos):</label>
		<input type="number" name="tiempoVida" id="">
		<label for="ruta">Ruta:</label>
		<input type="text" name="ruta" id="">
			*/
		Cookie galleta;
		String mensaje = "";
		if (request.getParameter("nuevoNombre") != null){
			String nuevoNombre = request.getParameter("nuevoNombre");
			String nuevoValor = request.getParameter("nuevoValor");
			galleta = new Cookie(nuevoNombre, nuevoValor);
			mensaje = "Se ha modificado la cookie con éxito";
		} else {
		
			String nombre = request.getParameter("nombre");
			String valor = request.getParameter("valor");
			String tiempoVidaParam = request.getParameter("tiempoVida");
			int tiempoVida = Integer.parseInt(tiempoVidaParam);
			String ruta = request.getParameter("ruta");
			
			galleta = new Cookie(nombre, valor);
			galleta.setPath(ruta);
			galleta.setMaxAge(tiempoVida);
			mensaje = "Se ha creado la cookie con éxito";
		}
			out.println("<article>");
			out.println("<h1>Creacion de Cookies Personalizada</h1>");
			out.println("<p>"+mensaje+"</p>");
			out.println("<form action='index.html'>");
			out.println("<input type='submit' value='Volver'/>");
			out.println("</form>");
			out.println("</article>");
		response.addCookie(galleta);
		
		
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