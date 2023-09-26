import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class DatosHTTP extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		
		out.println("<head>");
		out.println("<link rel='stylesheet' href='estilo.css'/>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<table>");
		
		// Se repite esta estructura tantas veces como filas haya

		out.println("<tr>");
		out.println("<th></th><td></td><td></td>");
		out.println("</tr>");

		//-------------------------------------------------------
		
		out.println("<tr>");
		out.println("<th></th><td></td><td></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th></th><td></td><td></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th></th><td></td><td></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th></th><td></td><td></td>");
		out.println("</tr>");

		out.println("</table>");
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