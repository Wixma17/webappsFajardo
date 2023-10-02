import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Formulario extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		Enumeration<String> parametros = null;
		String nombreParam = null;
		String[] valoresParam = null;
		String valorParam = null;
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Formulario Dinamico</h1>");
		out.println("<form action='ProcesoFormulario'>");
		parametros = request.getParameterNames();
		
		while (parametros.hasMoreElements()) {
			nombreParam = parametros.nextElement();
			
			// Obtener los valores asociados a este nombre de parámetro
			valoresParam = request.getParameterValues(nombreParam);
			
			for (int i = 0; i < valoresParam.length; i++) {
				valorParam = valoresParam[i];
				out.println( "<label>"+valorParam+":</label><input type='text' name='" + valorParam + "'/>");
			}
			
		} 
		
		out.println("<input type='submit' value='Procesamiento'/>");
		out.println("</form>");
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