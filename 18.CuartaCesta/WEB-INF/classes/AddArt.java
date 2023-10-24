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
		String mensaje = "";
		
		/*			out.println("<h3>Has seleccionado art√≠culos de m√°s, introduce una cantidad v√°lida</h3>");
				} else out.println("<h3>Se ha a√±adido correctamente</h3>");
				
			} else {
				out.println("<h3>Seleccione una cantidad, por favor (mayor de 0)<h3>");
			}
			
		*/
		if(cantidadNumerica == 0){
			mensaje = "<h3>Seleccione una cantidad, por favor (mayor de 0)<h3>";
		} else {
			// if(art.getExistencias()<= cantidadNumerica)
			if (!art.restarUnidades(cantidadNumerica)){
				mensaje = "<h3>Has seleccionado artÌculos de m·s, introduce una cantidad v·lida";
			} else {
				if (articulosUsuario.containsKey(nombre)){
					cantidadUsuario = articulosUsuario.get(nombre);
					cantidadUsuario = cantidadUsuario + cantidadNumerica;
					articulosUsuario.put(nombre, cantidadUsuario);
				} else {
					articulosUsuario.put(nombre, cantidadNumerica);
				}
				
				//art.restarUnidades(cantidadNumerica)
				mensaje = "<h3>Se han aÒadido correctamente "+cantidadNumerica+" de "+art.getNombre()+"</h3>";
			}
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