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

public class Acceso extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("mapaUsuarios", new HashMap<String, Usuario>());
		getServletContext().setAttribute("contadorUsuarios", 0);
	}
	
	
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
		
		if (!mapa.keySet().contains(nombreUsuario)) {
		
		int puestoUsuario = (int)contexto.getAttribute("contadorUsuarios") + 1;
		
		Usuario user = new Usuario(nombreUsuario, hora, puestoUsuario);
		// Los tipos básicos de Java se han de subir de nuevo al contexto del Servlet
		contexto.setAttribute("contadorUsuarios", puestoUsuario);
		
		mapa.put(user.getNombre(), user);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		
		out.println("<h1>Hola "+user.getNombre()+", has conectado a las: "+sdf.format(user.getHoraConexion().getTime())+"</h1>");
		out.println("<h2>Lista de usuarios conectados</h2>");
			for (Usuario usu : mapa.values()){
				out.println("<p>"+"<strong>Numero de acceso: </strong>"+usu.getPuestoConexion()+
				"<strong> | Usuario: </strong>"+usu.getNombre()+
				" - "+sdf.format(usu.getHoraConexion().getTime())+"</p>");
			}
			out.println("<form action='Despedida'>");
			out.println("<input type='submit' value='Desconectar' name='boton'>");
			out.println("<input type='hidden' value='"+user.getNombre()+"' name='usuario'>");
			out.println("</form>");
			
			out.println("<form action='index.html'>");
			out.println("<input type='submit' value='Volver' name='boton'>");
			out.println("</form>");
			
		} else {
		
		out.println("<h1>El usuario "+nombreUsuario+" ya está registrado</h1>");
		out.println("<form action='index.html'>");
			out.println("<input type='submit' value='Volver' name='boton'>");
		out.println("</form>");
		}
		
		
			
				
				
			
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