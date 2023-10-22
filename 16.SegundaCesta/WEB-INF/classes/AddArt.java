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
		
		ArrayList<String> articulosUsuario = (ArrayList<String>)miSesion.getAttribute("listaArticulos");
		
		String[] articulosSeleccionados = request.getParameterValues("articuloSelec");

		Articulo art;
		
		
		
		for (int i = 0; i < articulosSeleccionados.length; i++) {
		
			art = mapaArticulos.get(articulosSeleccionados[i]);
			art.setExistencias(art.getExistencias() - 1);
			
			articulosUsuario.add(art.getNombre());
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