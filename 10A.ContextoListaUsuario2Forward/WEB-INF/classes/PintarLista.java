import javax.servlet.*;
import javax.servlet.http.*;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class PintarLista extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		// Establecemos el tipo de respuesta a HTML
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		
		// Sacamos el parámetro usuario que contiene el nombre del mismo
		String nombreUsuario = request.getParameter("usuario");

		// Obtenemos el HashMap que contiene sus nombres como clave y los objetos Usuario como valor
		ServletContext contexto = getServletContext();
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		// Recogemos el atributo de la request que contiene el tipo de salida a imprimir
		String tipoSalida = (String)request.getAttribute("mensaje");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<article>");

		// Para que coja la hora exacta a la que se ha conectado el usuario sacamos el último usuario
		Usuario ultimoUsuario = mapa.get(nombreUsuario);	
		
		// Para formatear la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		/*
		Comprobamos el tipo de salida que debemos imprimir siendo:
			introducir: Hola "nombre", has conectado a las: "hora"
			existente: El usuario "nombre" ya está registrado
			desconectado: Adios "nombre"...
		*/
		if (tipoSalida.equalsIgnoreCase("introducir")) {
			out.println("<h1>Hola "+nombreUsuario+", has conectado a las: "+sdf.format(ultimoUsuario.getHoraConexion().getTime())+"</h1>");
			
			// Al tipo de salida, en caso de introducir, añadimos el botón para permitir la desconexión
			out.println("<form action='DesconectarUsuario'>");
			out.println("<input type='submit' value='Desconectar' name='boton'>");
			out.println("<input type='hidden' value='"+nombreUsuario+"' name='usuario'>");
			out.println("</form>");
			
		} else if (tipoSalida.equalsIgnoreCase("existente")){
		
			out.println("<h1>El usuario "+nombreUsuario+" ya está registrado</h1>");
			
		} else {
		
			out.println("<h1>Adios "+nombreUsuario+"... </h1>");
			
		}		
		
		out.println("<h2>Lista de usuarios conectados</h2>");
		
			// Itera el mapa y pinta con el formato:
			//<strong>Numero de acceso: </strong> "puesto" <strong> | Usuario: </strong> "nombre" - "horaFormateada"
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