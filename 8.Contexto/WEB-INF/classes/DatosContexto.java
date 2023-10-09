import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

public class DatosContexto extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("fechaArranque", new Date());
	}
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<article>");
		out.println("<p><strong>Hora de arranque:</strong> "+(Date)getServletContext().getAttribute("fechaArranque")+"</p>");
		out.println("<p><strong>Hora de conexion:</strong> "+new Date()+"</p>");
		out.println("<p><strong>Organizacion:</strong> "+getServletContext().getInitParameter("organizacion")+"</p>");
		out.println("<p><strong>Autores:</strong> "+getServletContext().getInitParameter("autor")+"</p>");
		out.println("<p><strong>Orden del recurso dentro de la aplicacion:</strong> "+getInitParameter("mondongo")+"</p>");
		out.println("<p><strong>Nombre del servlet:</strong> "+getServletName()+"</p>");
		out.println("</article>");
		
		out.println("<table>");
			out.println("<tr>");
				out.println("<td>Metodo</td>");
				out.println("<td>Salida</td>");
				out.println("<td>Descripcion</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getServletContext()</td>");
				out.println("<td>"+getServletContext()+"</td>");
				out.println("<td>Devuelve un objeto ServletContext compartido por todos los servlets de una aplicacion web, que define un conjutno de metodos que el servlet usa para obtener la informacion asociada a dicha aplicacion web</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getServletConfig()</td>");
				out.println("<td>"+getServletConfig()+"</td>");
				out.println("<td>Devuelve un objeto ServletConfig, que contiene los parametros de inicializacion para este servlet. Es el que se pasa como parametro al metodo init</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getServletConfig().getServletContext()</td>");
				out.println("<td>"+getServletConfig().getServletContext()+"</td>");
				out.println("<td>Es otra forma de obtener el objeto ServletContext, como en el primer metodo</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getMimeType(\"/images/tomcat.png\")</td>");
				out.println("<td>"+getServletContext().getMimeType("/multimedia/tonacho.jpeg")+"</td>");
				out.println("<td>Devuelve el tipo MIME del recurso que se indica por parametro en forma de String</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getResource(\"\")</td>");
				out.println("<td>"+getServletContext().getResource("")+"</td>");
				out.println("<td>Devuelve un String que contiene la ruta absoluta en en la que se encuentra la aplicacion</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getResourcePaths(\"/\")</td>");
				out.println("<td>"+getServletContext().getResourcePaths("/")+"</td>");
				out.println("<td>Devuelve como String todas las rutas relativas en las que se pueden encontrar recursos</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getAttribute(String name)<br>Ejemplo (getAtribute(\"fechaArranque\");)</td>");
				out.println("<td>"+getServletContext().getAttribute("fechaArranque")+"</td>");
				out.println("<td>Devuelve el atributo del ServletContext que le solicitemos por parametro</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getServletContext().getInitParameter(\"autor\")</td>");
				out.println("<td>"+getServletContext().getInitParameter("autor")+"</td>");
				out.println("<td>Devuelve el parametro declarado en el web.xml solicitado por parametro</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getMajorVersion()</td>");
				out.println("<td>"+getServletContext().getMajorVersion()+"</td>");
				out.println("<td>Devuelve la mayor version de la API del Servlet que soporta el esta aplicacion</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getMinorVersion()</td>");
				out.println("<td>"+getServletContext().getMinorVersion()+"</td>");
				out.println("<td>Devuelve la menor version de la API del Servlet que soporta el esta aplicacion</td>");
			out.println("</tr>");
			out.println("<tr>");
				out.println("<td>getRequestDispatcher(\"/multimedia/tonacho.jpeg\")</td>");
				out.println("<td>"+getServletContext().getRequestDispatcher("/multimedia/tonacho.jpeg")+"</td>");
				out.println("<td>Devuelve un objeto RequestDispatcher que actua como el wrapper del recurso encontrado en esa ruta</td>");
			out.println("</tr>");
		out.println("</table>");
		
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