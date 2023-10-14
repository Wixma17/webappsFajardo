import javax.servlet.*;
import javax.servlet.http.*;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class DesconectarUsuario extends HttpServlet{
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		// Cogemos el nombre del usuario a desconectar
		String nombreUsuario = request.getParameter("usuario");
		
		// Además, sacamos del contexto el HashMap que contiene los usuarios
		ServletContext contexto = getServletContext();
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		// Borramos el usuario del mapa del contexto
		mapa.remove(nombreUsuario);
		
		// Sacamos el RequestDispatcher de PintarLista
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/PintarLista");
		
		// El tipo de salida es el de "desconectado", que se comprueba en PintarLista
		String mensaje = "desconectado";
		
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