import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class Enviar3 extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter(); 
		String varCampos = "campos";
	//	String [] varClave = {"clave1", "clave2", "clave3"};
		String [] campos =null;
		String claves ="";
		String valorSeparado = "";
	//	String [] varValores ={"valores1", "valores2", "valores3"};
		String [] splitValores;
		
		out.println("<html> <head> <meta charset='utf-8' /> <link rel='stylesheet' href='estilo.css' type='text/css' /> </head>");
		out.println("<body>");
		out.println("<header><h1>formulario dinámico</h1></header>");
		
		campos= request.getParameterValues("campos");
		//campos= request.getParameterValues("campos");
		
		out.println("<form action='enviar2'>");
		if(campos != null){
			for (int i = 0; i<campos.length; i++){
				out.println("<label for="+campos[i]+">"+campos[i]+"</label>");
				out.println("<input type='text' name='"+campos[i]+"'> <br>");
			}
				
		}	
		
	//request clave y valores 1,2 y 3 if not null
		 
	/*	for(int i = 0; i<varClave.length; i++){
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
	*/	
		//En campos captura  todos los nombres de los select
		campos = request.getParameterValues("clave");
		
		//En valores guarda los valores de los futuros select separados por ;
		String[] valores = request.getParameterValues("valores");
		
		for (int i = 0; i < campos.length; i++){
				
				if (!valores[i].equalsIgnoreCase("") && !campos[i].equalsIgnoreCase("")) {
				
					out.println ("<select name='" +campos[i]+"' multiple>");
					
					splitValores = valores[i].split(";");
					
					for (int j=0; j < splitValores.length;j++){
						
						valorSeparado = splitValores[j];
						
						out.println("<option value='"+valorSeparado+"'>"+valorSeparado+"</option>");
							
					}
					
					out.println ("</select>");
				}
		
		}
		
		out.println("<input type='submit' value='enviar'>");
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