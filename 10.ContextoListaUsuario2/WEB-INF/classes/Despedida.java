import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Despedida extends HttpServlet{
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		GregorianCalendar hora = new GregorianCalendar();
		ServletContext contexto = getServletContext();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		
		out.println("</head>");
		out.println("<body>");

		out.println("<article>");	
		
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		mapa.remove(nombreUsuario);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		
		out.println("<h1>Adios "+nombreUsuario+"... </h1>");
		out.println("<h2>Usuarios conectados ahora mismo</h2>");
			for (Usuario usu : mapa.values()){
				out.println("<p>"+"<strong>Numero de acceso: </strong>"+usu.getPuestoConexion()+
				"<strong> | Usuario: </strong>"+usu.getNombre()+
				" - "+sdf.format(usu.getHoraConexion().getTime())+"</p>");
			}
		
			out.println("<form action='index.html'>");
			out.println("<input type='submit' value='Volver' name='boton'>");
			out.println("</form>");
		
		out.println("</article>");
		
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