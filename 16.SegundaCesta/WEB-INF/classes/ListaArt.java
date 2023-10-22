import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;
import pojos.*;

public class ListaArt extends HttpServlet{
	
	@Override
	public void init() throws ServletException {

		String articulosTienda = getServletContext().getInitParameter("articulosTienda");
		
		String[] articulosArray = articulosTienda.split(";");
		
		HashMap<String, Articulo> mapaArticulos = new HashMap<String, Articulo>();
		
		getServletContext().setAttribute("articulos", mapaArticulos);
		
		for (int i = 0; i < articulosArray.length; i++){
		
			String[] entradaMapa = articulosArray[i].split("_");
			Articulo art = new Articulo(entradaMapa[0], Integer.parseInt(entradaMapa[1]));
			mapaArticulos.put(art.getNombre(), art);
		
		}
		
	}
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		out.println("<link rel='stylesheet' href='estiloLista.css'>");
		out.println("</head>");
		out.println("<body>");
		String comprobarAgotado = "";
		HttpSession miSesion = request.getSession();
		
		if (miSesion.isNew()) {
			String nombre = request.getParameter("nombre");
			GregorianCalendar fechaInicio = new GregorianCalendar();
			ArrayList<String> listaArticulos = new ArrayList<String>();
			miSesion.setAttribute("nombre", nombre);
			miSesion.setAttribute("fechaConexion", fechaInicio);
			miSesion.setAttribute("listaArticulos", listaArticulos);
		}
		
		if (request.getParameterValues("articuloSelec") != null) {
			rd = getServletContext().getRequestDispatcher("/AddArt");
			rd.include(request, response);
		}
		
		HashMap<String, Articulo> mapaArticulos = (HashMap<String, Articulo>)getServletContext().getAttribute("articulos");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		out.println("<article>");
		out.println("<h1>Bienvenido/a "+miSesion.getAttribute("nombre")+", has iniciado sesión a las "+sdf.format(((GregorianCalendar)miSesion.getAttribute("fechaConexion")).getTime())+"</h1>");
		out.println("<h2>¿Qué quiere comprar?</h2>");
		out.println("<form action='ListaArt'>");
		out.println("<table>");
		
		for (Articulo art : mapaArticulos.values()){
			comprobarAgotado = "";
			
			if (art.getExistencias() == 0){
				comprobarAgotado = "disabled";
			}
			
			out.println("<tr>");
			out.print("<td class='foto'><img src='imagenes/"+art.getNombre()+".png'/></td>");
			
			out.print("<td><input type='checkbox' name='articuloSelec' value='"+art.getNombre()+"' "+comprobarAgotado+">"+art.getNombre()+"</td>");
			if (art.getExistencias() == 0){
				out.println("<td>No hay artículos disponibles</td>");
			} else out.println("<td>Articulos disponibles: "+art.getExistencias()+"</td>");
			out.println("</tr>");
		}
			
		out.println("</table>");
		out.println("<input type='submit' value = 'Añadir'>");
		out.println("</form>");
		
		out.println("<form action='VerCesta'>");
		out.println("<input type='submit' value = 'Ver Cesta'>");
		out.println("</form>");
		
		out.println("<form action='Salida'>");
		out.println("<input type='submit' value = 'Cerrar Sesión'>");
		out.println("</form>");
		
		out.println("</article>");
		
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