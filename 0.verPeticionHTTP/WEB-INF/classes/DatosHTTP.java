import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class DatosHTTP extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response, boolean paramURL)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("<link rel='stylesheet' href='estilo.css'/>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<table>");
		
		// Se repite esta estructura tantas veces como filas haya
		
		out.println("<tr>");
		out.println("<th>getServerName()</th><td>Devuelve el nombre del servidor</td><td>"+request.getServerName()+"</td>");
		out.println("</tr>");
		
		//-------------------------------------------------------
		
		out.println("<tr>");
		out.println("<th>getServerPort()</th><td>Devuelve el puerto por el que nos conectamos al servidor</td><td>"+request.getServerPort()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getContextPath()</th><td>Devuelve el contexto de la aplicacion, la cual coincide con el nombre</td><td>"+request.getContextPath()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getServletPath</th><td>Devuelve la ruta del servlet</td><td>"+request.getServletPath()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getMethod()</th><td>Devuelve el tipo de la peticion HTTP</td><td>"+request.getMethod()+"</td>");
		out.println("</tr>");
		
		
		// Lo que conseguimos aquí es eliminar el posible null que libera el programa al procesar una petición HTTP de tipo POST, ya que no recibe la información
		// en la cabecera, sino en el cuerpo de la petición.
		
		if (paramURL){
			out.println("<tr>");
			out.println("<th>getQueryString()</th><td>Devuelve el string que contiene la informacion de los parametros</td><td>"+request.getQueryString()+"</td>");
			out.println("</tr>");
		}
		
		out.println("<tr>");
		out.println("<th>getRequestURL()</th><td>Devuelve la URL de la peticion del recurso solicitado</td><td>"+request.getRequestURL()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getLocalAddr()</th><td>Devuelve la direccion IP de la tarjeta de red del servidor que recibe la peticion</td><td>"+request.getLocalAddr()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getLocalName()</th><td>Devuelve el nombre del servidor que tiene la tarjeta de red que recibe la peticion</td><td>"+request.getLocalName()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getLocalPort()</th><td>Devuelve el numero de puerto TCP al que ha llegado la peticion</td><td>"+request.getLocalPort()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getRemoteAddr()</th><td>Devuelve la IP del cliente que realiza la peticion</td><td>"+request.getRemoteAddr()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getRemoteHost()</th><td>Este metodo devuelve el FQDN del cliente desde donde se ha generado la peticion HTTP</td><td>"+request.getRemoteHost()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getParameter()</th><td>Este metodo devuelve el parametro solicitado entre parentesis (segun el nombre, que es par1 en este caso)</td><td>"+request.getParameter("par1")+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>getParameterNames()</th><td>Este metodo devuelve en un objeto Enumeration (Collection en este caso) los nombres de los parametros</td><td>"+request.getParameterNames()+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response, true);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		processRequest(request, response, false);
	}
	
	
}