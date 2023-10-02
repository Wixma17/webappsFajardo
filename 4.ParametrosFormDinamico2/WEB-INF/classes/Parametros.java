import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Parametros extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		Enumeration<String> parametros = null;
		String nombreParam = null;
		String[] valoresParam = null;
		String valorParam = null;
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Datos recogidos por Formulario Dinamico</h1>");
		
		parametros = request.getParameterNames();
		
		while (parametros.hasMoreElements()) {
			nombreParam = parametros.nextElement();
			
			// Obtener los valores asociados a este nombre de parámetro
			valorParam = request.getParameter(nombreParam);
			out.println( "<p>"+nombreParam+" : "+valorParam+"</p>");
			
		} 
		
		out.println("<h2>Listado de parametros y valores utilizando getParameterNames / getParameterValues</h2>");
		
		parametros = request.getParameterNames();
		
		while (parametros.hasMoreElements()) {
			nombreParam = parametros.nextElement();
			
			// Obtener los valores asociados a este nombre de parámetro
			valoresParam = request.getParameterValues(nombreParam);
			
			for (int i = 0; i < valoresParam.length; i++) {
				valorParam = valoresParam[i];
				out.println( "<p>"+nombreParam+" : "+valorParam+"</p>");
			}
			
		}
		
		out.println("<h2>Listado de parametros y valores utilizando getParameterMap / getParameter</h2>");
		
		Map<String, String[]> mapaParametros = request.getParameterMap();
		String atributos = "";
		for (Map.Entry<String, String[]> entrada : mapaParametros.entrySet()) {
			for (int i = 0; i < entrada.getValue().length; i++){
				atributos += entrada.getValue()[i]+" ";
			}
			out.println(entrada.getKey() + " : "+atributos +"<br>");
			atributos = "";
		}
		
		out.println("</body>");
		out.println("</html>");
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