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
		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		
		GregorianCalendar hora = new GregorianCalendar();
		
		ServletContext contexto = getServletContext();
		
		String mensaje = "";
		
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>)contexto.getAttribute("mapaUsuarios");
		
		if (!mapa.keySet().contains(nombreUsuario)) {
		
			int puestoUsuario = (int)contexto.getAttribute("contadorUsuarios") + 1;
			
			Usuario user = new Usuario(nombreUsuario, hora, puestoUsuario);
			
			contexto.setAttribute("contadorUsuarios", puestoUsuario);
			
			mapa.put(user.getNombre(), user);
			
			mensaje = "introducir";
			
		} else {
		
			mensaje = "existente";
		
		}
	
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