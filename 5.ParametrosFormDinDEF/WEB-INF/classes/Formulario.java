import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Formulario extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		String nombreParam = null;
		String valorSeparado = "";
		String[] splitValores = null;
		String[] valoresParam = null;
		String[] parametrosProcesados = null;
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Formulario Dinamico</h1>");
		out.println("<form action='Parametros'>");
		
		String[] campos= request.getParameterValues("campos");
		//campos= request.getParameterValues("campos");
		
		
		// Recorremos los campos estáticos del select múltiple que haya elegido el usuario.
		if(campos != null){
			for (int i = 0; i<campos.length; i++){
				//Iteramos por cada valor del parámetro "campos"
				out.println("<label for="+campos[i]+">"+campos[i]+"</label>");
				
				out.println("<input type='text' name='"+campos[i]+"'> <br>");
			}
				
		}	
		
		//Se reutiliza la variable campos, lo cual no es recomendado por legibilidad
		campos = request.getParameterValues("clave");
		// String [] claves = request.getParameterValues("clave");
		
		//En valores guarda los valores de los futuros select separados por ;
		String[] valores = request.getParameterValues("valores");
		
		String[] tipos = request.getParameterValues("tipo");
		
		for (int i = 0; i < campos.length; i++){
				
				
				// if (valores[i].equalsIgnoreCase("") || campos[i].equalsIgnoreCase("")) {
				if (!valores[i].equalsIgnoreCase("") && !campos[i].equalsIgnoreCase("")) {
					
					out.println("<h2>Selecciona "+campos[i]+"</h2>");
					
					if (tipos[i].contains("select")) {
						
						out.print("<select name='" +campos[i]);
						
						if (tipos[i].equalsIgnoreCase("select"))
							out.println("'>");
						else out.println("' multiple>");
							
							
						splitValores = valores[i].split(";");
					
						for (int j=0; j < splitValores.length;j++){
							
							valorSeparado = splitValores[j];
							
							out.println("<option value='"+valorSeparado+"'>"+valorSeparado+"</option>");
							//out.println("<option value='"+splitValores[j]+"'>"+splitValores[j]+"</option>");
								
						}
						
						out.println ("</select>");	
						
					} else {
					
						splitValores = valores[i].split(";");
					
						for (int j=0; j < splitValores.length;j++){
							
							valorSeparado = splitValores[j];
							
							out.println("<label>"+valorSeparado+"</label><input type='"+tipos[i]+"' name='"+campos[i]+"' value='"+valorSeparado+"'>");
							//out.println("<label>"+splitValores[j]+"</label><input type='"+tipos[i]+"' name='"+campos[i]+"' value='"+splitValores[j]+"'>");
							
						}
						
					
					}

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