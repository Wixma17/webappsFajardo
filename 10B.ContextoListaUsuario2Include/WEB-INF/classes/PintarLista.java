import javax.servlet.*;
import javax.servlet.http.*;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class PintarLista extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		
		Usuario ultimoUsuario = null;
		
		GregorianCalendar hora = new GregorianCalendar();
		
		ServletContext contexto = getServletContext();
		
		String pulsadoBoton = (String)request.getParameter("boton");
	
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<article>");
		
		
		HashMap<String, Usuario> mapa = null;
		
		// Para que coja la hora exacta a la que se ha conectado el usuario
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		if (pulsadoBoton.equalsIgnoreCase("Registrar")){
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddUsuario");
			
			rd.include(request, response);
			
		} else {
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/DesconectarUsuario");
			
			rd.include(request, response);
			
		}
		
		mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
			
		ultimoUsuario = mapa.get(nombreUsuario);
		
		String tipoSalida = (String)request.getAttribute("mensaje");
		
		if (tipoSalida.equalsIgnoreCase("introducir")) {
		out.println("<h1>Hola "+nombreUsuario+", has conectado a las: "+sdf.format(ultimoUsuario.getHoraConexion().getTime())+"</h1>");
		out.println("<form action='PintarLista'>");
		out.println("<input type='submit' value='Desconectar' name='boton'>");
		out.println("<input type='hidden' value='"+nombreUsuario+"' name='usuario'>");
		out.println("</form>");
		} else if (tipoSalida.equalsIgnoreCase("existente")){
		
		out.println("<h1>El usuario "+nombreUsuario+" ya está registrado</h1>");
		
		} else {
			out.println("<h1>Adios "+nombreUsuario+"... </h1>");
		}		
		
		out.println("<h2>Lista de usuarios conectados</h2>");
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