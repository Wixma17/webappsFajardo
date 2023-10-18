import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Articulos extends HttpServlet{
	/*
	@Override
	public void init() throws ServletException {

		// ArrayList<String> listaNumeros = new ArrayList<String>(Arrays.asList(numerosSplit));
		HashMap<String, HttpSession> mapaSesiones = new HashMap<String, HttpSession>();
		
		getServletContext().setAttribute("sesiones", mapaSesiones);
		
	}
	*/
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head>");
		out.println("<body>");
		
		String nombreUsuario;
		
		//String idSesion;
		//HashMap<String, HttpSession> mapaSesiones = getServletContext().getAttribute("sesiones");
		
		ArrayList<String> listaArticulosUsuario;
		
		String[] articulosSeleccionados = request.getParameterValues("articulos");
		
		HttpSession miSesion = request.getSession();

		if (miSesion.isNew()){
			nombreUsuario = (String)request.getParameter("nombre");
			miSesion.setAttribute("listaArticulos", new ArrayList<String>());
			miSesion.setAttribute("nombreUsuario", nombreUsuario);
			
			/*
			idSesion = miSesion.getId();
			mapaSesiones.put(idSesion, miSesion);
			*/
			
		}		
		
		nombreUsuario = (String) miSesion.getAttribute("nombreUsuario");
		
		listaArticulosUsuario = (ArrayList<String>)miSesion.getAttribute("listaArticulos");
		
		
		
		out.println("<h2>Bienvenido "+nombreUsuario+"</h2>");
		
		
		
		if (articulosSeleccionados != null){
		out.println("<ul>");
		
			for (int i = 0; i < articulosSeleccionados.length; i++) {
				out.println("<li>"+articulosSeleccionados[i]+"</li>");
				listaArticulosUsuario.add(articulosSeleccionados[i]);
			}
		out.println("</ul>");
		}
		

		
		out.println("<p>");
		for (int i = 0; i < listaArticulosUsuario.size() - 1; i++){
			out.println(listaArticulosUsuario.get(i)+" - ");
		}
		out.println(listaArticulosUsuario.get(listaArticulosUsuario.size() - 1));
		out.println("</p>");
		
		out.println("<form action='Saludo'>");
		out.println("<input type='submit' value='Volver a articulos'>");
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