import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Recurso1 extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		out.println("<body>");
		//TODO
		out.println("Hola acaban de llamarme soy el recurso 1");
		String numero = request.getParameter("numero");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Recurso2");
		rd.include(request, response);
		out.println("Hola de nuevo, se supone que el otro ha hecho bien la raíz cuadrada de "+numero+" y es "+request.getAttribute("raiz"));
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