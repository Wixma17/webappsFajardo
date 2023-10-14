import javax.servlet.*;
import javax.servlet.http.*;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class AddUsuario extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		// Inicializamos en este init tanto el HashMap como el contador de usuarios
		getServletContext().setAttribute("mapaUsuarios", new HashMap<String, Usuario>());
		getServletContext().setAttribute("contadorUsuarios", 0);
	}
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		// Obtenemos los datos necesarios para registrar un nuevo usuario
		String nombreUsuario = request.getParameter("usuario");
		GregorianCalendar hora = new GregorianCalendar();
		
		// Obtenemos además, el contexto del Servlet
		ServletContext contexto = getServletContext();
		
		// Esta variable almacenará el tipo de pintado que se debe realizar, indicando el caso acontecido
		// introducir = ha entrado un nuevo usuario; existente = ya existe el usuario
		String mensaje = "";
		
		// Sacamos el mapa del contexto, inicializado siempre al llamar por primera vez a este Servlet
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		// Comprobamos que el usuario ya exista en el mapa (la clave es el nombre (String))
		if (!mapa.keySet().contains(nombreUsuario)) {
		
			// Sacamos el contador desde el contexto y le sumamos 1
			int puestoUsuario = (int)contexto.getAttribute("contadorUsuarios") + 1;
			
			// Creamos un usuario con los datos que hemos recopilado
			Usuario user = new Usuario(nombreUsuario, hora, puestoUsuario);
			
			// Devolvemos el atributo al contexto del Servlet
			contexto.setAttribute("contadorUsuarios", puestoUsuario);
			
			// Introducimos el usuario en el mapa
			mapa.put(user.getNombre(), user);
			
			// El tipo de salida es el de "introducir", que se comprueba en PintarLista
			mensaje = "introducir";
			
		} else {
		
			// El tipo de salida es el de "existente", que se comprueba en PintarLista
			mensaje = "existente";
		}
	
		// Sacamos el RequestDispatcher de PintarLista
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/PintarLista");
		
		// Pasamos como atributo de la cabecera el tipo de salida pertinente
		request.setAttribute("mensaje", mensaje);
		
		// Hacemos el forward hacia PintarLista
		rd.forward(request, response);
		
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