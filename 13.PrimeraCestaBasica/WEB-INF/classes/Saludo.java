import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Saludo extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
	
		String articulosPapeleria = getServletConfig().getInitParameter("articulos");
		
		String[] articulosArray = articulosPapeleria.split(";");
		
		// Esto podría saltarse
		List<String> listaArticulosAuxiliar = Arrays.asList(articulosArray);
		
		// ArrayList<String> listaNumeros = new ArrayList<String>(Arrays.asList(numerosSplit));
		ArrayList<String> listaArticulos = new ArrayList<String>(listaArticulosAuxiliar);
		
		getServletContext().setAttribute("articulos", listaArticulos);
		
	}
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head>");
		out.println("<body>");
		HttpSession miSesion;
		
		String nombreUsuario = (String)request.getParameter("nombre");
		
		if (nombreUsuario == null){
			miSesion = request.getSession();
			nombreUsuario = (String) miSesion.getAttribute("nombreUsuario");
		}
			
		ArrayList<String> listaArticulos = (ArrayList<String>) getServletContext().getAttribute("articulos");
		
		out.println("<h2>Bienvenido "+nombreUsuario+"</h2>");
		
		
		out.println("<form action = 'Articulos'>");
		
		for (String articulo : listaArticulos) {
		
			String articuloNormalizado = "";
			articuloNormalizado = articulo.toLowerCase();
			articuloNormalizado = Normalizer.normalize(articuloNormalizado, Normalizer.Form.NFD);
			
			out.println("<input type='checkbox' name='articulos' value='"+articuloNormalizado+"' id=''>");
			out.println("<label>"+articulo+"</label>");
			
		
		}
		
		out.println("<input type='hidden' value='"+nombreUsuario+"' name='nombre'>");
		out.println("<input type='submit' value='Comprar'>");
		out.println("</form>");
		
		rd = getServletContext().getRequestDispatcher("/pie.html");
		rd.include(request, response);
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