import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;
import pojos.*;

public class AddArt extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

		HttpSession miSesion = request.getSession();
			
		HashMap<String, Articulo> mapaArticulos = (HashMap<String, Articulo>)getServletContext().getAttribute("articulos");
		
		HashMap<String, Integer> articulosUsuario = (HashMap<String, Integer>)miSesion.getAttribute("listaArticulos");
		
		String cantidad = request.getParameter("cantidadSeleccionada");
		
		int cantidadNumerica = Integer.parseInt(cantidad);
		
		int cantidadUsuario;
		
		String nombre = request.getParameter("articuloSelec");

		Articulo art = mapaArticulos.get(nombre);
		
		if (art.restarUnidades(cantidadNumerica)){
			if (articulosUsuario.get(nombre) != null){
				cantidadUsuario = articulosUsuario.get(nombre);
				cantidadUsuario = cantidadUsuario + cantidadNumerica;
				articulosUsuario.put(nombre, cantidadUsuario);
			} else {
				articulosUsuario.put(nombre, cantidadNumerica);
			}
			
			miSesion.removeAttribute("error");
		} else {
			miSesion.setAttribute("error", "invalido");
		}
	
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