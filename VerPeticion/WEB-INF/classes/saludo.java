import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class saludo extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		String me = request.getMethod();
		out.println("<h1>Illo el cervido lo ha allamao azin "+me+"</h1>");
		out.println("<h2>Nombre del servidor: "+request.getServerName()+"</h2>");
		out.println("<h3>Ruta del contexto: "+request.getContextPath()+"</h3>");
		out.println("<h4>URL de la petición: "+request.getRequestURL()+"</h4>");
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