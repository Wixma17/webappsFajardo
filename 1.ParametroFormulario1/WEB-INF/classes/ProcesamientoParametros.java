import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcesamientoParametros extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		Enumeration<String> parametros = null;
		String nombreParam = null;
		String[] valoresParam = null;
		String valorParam = null;
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='style/estiloParam.css'>");  
		out.println("</head>");
		out.println("<body>");
		
		// Obtener los nombres de los parámetros de la solicitud
        parametros = request.getParameterNames();
		
        while (parametros.hasMoreElements()) {
            nombreParam = parametros.nextElement();
            
            // Obtener los valores asociados a este nombre de parámetro
            valoresParam = request.getParameterValues(nombreParam);
			
            // Escribir el nombre del parámetro en la respuesta HTML
            out.println("<div>");
            out.println("<h1>"+nombreParam + ": </h1>");
			
            // Si hay múltiples valores para este parámetro, mostrarlos separados por comas
			out.println("<p>");
			for (int i = 0; i < valoresParam.length - 1; i++) {
				valorParam = valoresParam[i];
				out.println(valorParam + ", ");
			}
			out.println(valoresParam[valoresParam.length - 1]);
			out.println("</p>");
			out.println("</div>");
			
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