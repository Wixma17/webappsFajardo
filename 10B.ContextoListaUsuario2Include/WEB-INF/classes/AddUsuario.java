import javax.servlet.*;
import javax.servlet.http.*;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class AddUsuario extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("mapaUsuarios", new HashMap<String, Usuario>());
		getServletContext().setAttribute("contadorUsuarios", 0);
	}
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		// Sacamos el nombre de usuario del parametro "usuario" de la request
		String nombreUsuario = request.getParameter("usuario");
		
		// Generamos un GregorianCalendar de la hora actual para introducírselo al nuevo usuario
		GregorianCalendar hora = new GregorianCalendar();
		
		// Inicializamos el mensaje, que pasaremos como atributo de la request a vacío
		String mensaje = "";
		
		// Guardamos la dirección del contexto y cogemos del contexto el HashMap que contiene los usuarios
		ServletContext contexto = getServletContext();		
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		// Comprobamos que el mapa contenga al usuario
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
			mensaje = "introducido";
			
		} else {
		
			// El tipo de salida es el de "existente", que se comprueba en PintarLista
			mensaje = "existente";
			
		}
		
		// Establecemos a la request el atributo "mensaje" como "existente" o "introducir", según la ejecución
		request.setAttribute("mensaje", mensaje);
		
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