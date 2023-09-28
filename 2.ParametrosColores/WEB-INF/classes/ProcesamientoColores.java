import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcesamientoColores extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		Enumeration<String> parametros = null;
		String nombreParam = null;
		String[] valoresParam = null;
		String valorParam = null;
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("<link rel='stylesheet' href='style/estilocolores.css'>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<h1>Resultados</h1>");
		
		parametros = request.getParameterNames();
		
        while (parametros.hasMoreElements()) {
            nombreParam = parametros.nextElement();
            
            // Obtener los valores asociados a este nombre de parámetro
            valoresParam = request.getParameterValues(nombreParam);
			
            // Escribir el nombre del parámetro en la respuesta HTML
            out.println("<div>");
            out.println("<h1>"+nombreParam + ": </h1>");
			
            if (valoresParam.length > 1) {
				out.println("<div>");
                for (int i = 0; i < valoresParam.length; i++) {
                    valorParam = valoresParam[i];
					out.println("<h2 id='"+valorParam+"'>"+valorParam+"</h2>");
				}
				out.println("</div>");
                
				} else {
                // Si solo hay un valor para este parámetro, mostrarlo
                out.println("<h2>"+valoresParam[0]+"</h2>");
			}
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