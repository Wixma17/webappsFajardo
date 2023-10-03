import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redireccionamiento extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h1>Formulario Dinamico</h1>");
		out.println("<form action='Parametros'>");
		
		String parametro = request.getParameter("boton");
		
		if (parametro.equalsIgnoreCase("Google1")){
			response.sendRedirect("https://www.google.com/");
		} else {
			if (parametro.equalsIgnoreCase("Google2")) {
				response.setStatus(HttpServletResponse.SC_FOUND);
				response.setHeader("Location", "https://www.google.com/");
			} else {
				response.sendError(423, "Pelón pelorrico");
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