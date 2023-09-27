import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class DatosCabecera extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Enumeration<Locale> listaIdiomas = request.getLocales();
		String nombreCabecera=null;
		Enumeration nombresCabeceras=null;
		Enumeration valoresCabeceras=null;
		
		try {
			
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='style/estilo2.css'/>");
			out.println("<title>DatosCabecera</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<ol>");
			out.println("<li>Metodo que devuelve la longitud del cuerpo de la peticion: getContentLength() -> "+request.getContentLength()+"</li>");
			out.println("<li>Metodo que devuelve los tipos de datos presentes en el cuerpo de la peticion: getContentType() -> "+request.getContentType()+"</li>");
			out.println("<li>Metodo que devuelve el lenguaje preferido del navegador: getLocale() -> "+request.getLocale()+"</li>");
			out.println("<li>Idiomas aceptados por el navegador: getLocales() -> "+request.getLocales()+"</li>");
			out.println("<li>Listado de idiomas:</li>");
			out.println("</ol>");
			
			
			
			out.println("<table><th>Idioma</th><th>Pais</th><th>Codigo</th></tr>");
			while (listaIdiomas.hasMoreElements()) {
				Locale valores = listaIdiomas.nextElement();
				out.println("<tr><td>"+ valores.getLanguage() +"</td><td>"+ valores.getDisplayCountry() +"</td><td>"+ valores.toString() +"</td></tr>");
			}
			out.println("</table>");

			
			//--------------------------------------------------------
			nombresCabeceras=request.getHeaderNames();
			out.println("<table>");
			
			while (nombresCabeceras.hasMoreElements()){
				nombreCabecera=(String)nombresCabeceras.nextElement();
				out.println("<tr>");
				out.println("<td>" + nombreCabecera + "</td>");
				valoresCabeceras=request.getHeaders(nombreCabecera);
				while (valoresCabeceras.hasMoreElements())
				{
					out.println("<td>" +
					valoresCabeceras.nextElement() + "</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<div><a href='index.html'>Voler</a><a href='salida.html'>Salir</a></div>");
			out.println("</body>");
			out.println("</html>");
			
		}
		
		finally {
			out.close();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}
}