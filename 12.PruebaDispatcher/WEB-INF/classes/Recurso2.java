import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Recurso2 extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		//TODO
		int n = Integer.parseInt(request.getParameter("numero"));
		out.println("Hola soy el recurso 1 acaba de llamarme el recurso 2 y tengo que calcular la raíz cuadrada de "+n);
		double raiz = Math.sqrt(n);
		request.setAttribute("raiz", raiz);
		
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