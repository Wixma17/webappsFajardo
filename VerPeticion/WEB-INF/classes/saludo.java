import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class saludo extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  
	out.println("<html>");
	out.println("<body>");
	//TODO
	out.println("Hola mundo");
	out.println("</body>");
	out.println("</html>");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		PrintWriter out = response.getWriter(); 
		out.println("ILLO QUE LAS DAO AL GET");
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		PrintWriter out = response.getWriter(); 
		out.println("ILLO QUE LAS DAO AL POST");
		processRequest(request, response);
	}
	
	
}