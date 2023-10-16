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
		
		// Sacamos el par�metro usuario que contiene el nombre del mismo
		String nombreUsuario = request.getParameter("usuario");
		
		ServletContext contexto = getServletContext();	
		
		// Para que coja la hora exacta a la que se ha conectado el usuario sacamos el �ltimo usuario, inicializado a null
		Usuario ultimoUsuario = null;
		
		// Sacamos el valor que contenga el bot�n, el cual 
		String pulsadoBoton = (String)request.getParameter("boton");
	
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<article>");
		
		// Inicializamos el mapa que contiene los usuarios a null
		HashMap<String, Usuario> mapa = null;
		
		// Para que coja la hora exacta a la que se ha conectado el usuario
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		// Comprobamos si el usuario ha entrado desde el index o si ha seleccionado el desconectar
		// Esta primera opci�n es en caso de entrar desde el �ndex
		if (pulsadoBoton.equalsIgnoreCase("Registrar")){
		
			// Sacamos el RequestDispatcher del AddUsuario, efectuando un include
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AddUsuario");
			rd.include(request, response);
		} else /*Esta opci�n es en caso de entrar desde la opci�n de desconectar*/ {
		
			// Sacamos el RequestDispatcher del DesconectarUsuario, efectuando un include
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/DesconectarUsuario");
			rd.include(request, response);
		}
		
		 
		//Ahora s�, tras haber inicializado el mapa al haber entrado al menos una vez en el Servlet AddUsuario
		//podemos asignarle al mapa el valor de la variable del contexto
		mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
			
		// Obtenemos el �ltimo usuario del mapa
		ultimoUsuario = mapa.get(nombreUsuario);
		
		// Obtenemos el tipo de salida, que lo hemos sacado de cualquiera de los dos Servlet utilizados
		String tipoSalida = (String)request.getAttribute("mensaje");
		
		/*
		Comprobamos el tipo de salida que debemos imprimir siendo:
			introducir: Hola "nombre", has conectado a las: "hora"
			existente: El usuario "nombre" ya est� registrado
			desconectado: Adios "nombre"...
		*/
		if (tipoSalida.equalsIgnoreCase("introducido")) {
		out.println("<h1>Hola "+nombreUsuario+", has conectado a las: "+sdf.format(ultimoUsuario.getHoraConexion().getTime())+"</h1>");
		
		// Genera la opci�n de desconectar, ya que se ha conectado correctamente
		out.println("<form action='PintarLista'>");
		out.println("<input type='submit' value='Desconectar' name='boton'>");
		out.println("<input type='hidden' value='"+nombreUsuario+"' name='usuario'>");
		out.println("</form>");
		
		} else if (tipoSalida.equalsIgnoreCase("existente") ){
		
		out.println("<h1>El usuario "+nombreUsuario+" ya est� registrado</h1>");
		
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