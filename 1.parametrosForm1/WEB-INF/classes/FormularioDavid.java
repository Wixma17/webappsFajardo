import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class FormularioDavid extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  
	out.println("<html>");
	out.println("<body");
	
	out.println("</body>");
	out.println("</html>");
	out.println("Nombre : "+ request.getParameter("nombre")+"<br>");
	out.println("Apellidos : "+ request.getParameter("apellidos")+"<br>");
	out.println("Sistema operativo : "+ request.getParameter("sistema")+"<br>");
	//out.println("Lenguaje Favorito : "+ request.getParameterValues("lenguaje")+"<br>");
	
	
	String[] favoritos = request.getParameterValues("lenguaje");
	
		
		for(String favorito:favoritos )
			
			out.println(favorito);
		
		
	
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