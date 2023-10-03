import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class enviar extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		String varCampos = "campos";
		String [] varClave = {"clave1", "clave2", "clave3"};
		String [] campos =null;
		String claves =null;
		String valores = null;
		String [] varValores ={"valores1", "valores2", "valores3"};
		String [] splitValores;
		
		out.println("<html> <head> <link rel='stylesheet' href='estilo.css' type='text/css' /> </head>");
		out.println("<body>");
		out.println("<header><h1>formulario dinamico</h1></header>");
		
		campos= request.getParameterValues(varCampos);
		out.println("<form action='enviar2'>");
		if(campos != null){
		
			for (int i = 0; i<campos.length; i++){
				out.println("<label for="+campos[i]+">"+campos[i]+"</label>");
				out.println("<input type='text' name='"+campos[i]+"'> <br>");
			}
				
		}	
		
		//request clave y valores 1,2 y 3 if not null
		
		for(int i = 0; i<varValores.length; i++){
			claves= request.getParameter(varClave[i]);
			valores= request.getParameter(varValores[i]);
			splitValores =valores.split(";");
			if(claves != ""){
				out.println("<label for="+claves+">"+claves+"</label><br>");
				out.println("<select name="+claves+" multiple>");
				for(int f = 0; f<splitValores.length; f++){
				out.println("<option for="+splitValores[f]+">"+splitValores[f]+"</option>");
				}
				out.println("</select><br>");
				claves = null;
				valores= null;
			}
		}
		
		out.println("");
		out.println("<input type='submit' value='enviar'>");
		out.println("</form>");
		
		
		out.println("");
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