import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class saludo extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
<<<<<<< HEAD
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  
	out.println("<html>");
	out.println("<body>");
	//TODO
	out.println("Hola mundo");
	out.println("</body>");
	out.println("</html>");
=======
		
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
>>>>>>> 8a8238df6b8608476489d33a12d508e7eb39ca00
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
<<<<<<< HEAD
		PrintWriter out = response.getWriter(); 
		out.println("ILLO QUE LAS DAO AL GET");
=======
>>>>>>> 8a8238df6b8608476489d33a12d508e7eb39ca00
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
<<<<<<< HEAD
		PrintWriter out = response.getWriter(); 
		out.println("ILLO QUE LAS DAO AL POST");
=======
>>>>>>> 8a8238df6b8608476489d33a12d508e7eb39ca00
		processRequest(request, response);
	}
	
	
}