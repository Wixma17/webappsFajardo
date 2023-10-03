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
		Map<String, String[]> parametros = null;
		String nombreParam = null;
		String valorSeparado = "";
		String[] splitValores = null;
		String[] valoresParam = null;
		String[] parametrosProcesados = null;
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Formulario Dinamico</h1>");
		out.println("<form action='Parametros'>");
		parametros = request.getParameterMap();
		
		String[] campos= request.getParameterValues("campos");
		//campos= request.getParameterValues("campos");
		
		
		if(campos != null){
			for (int i = 0; i<campos.length; i++){
				out.println("<label for="+campos[i]+">"+campos[i]+"</label>");
				out.println("<input type='text' name='"+campos[i]+"'> <br>");
			}
				
		}	
		
		
		//En campos captura  todos los nombres de los select
		campos = request.getParameterValues("clave");
		
		//En valores guarda los valores de los futuros select separados por ;
		String[] valores = request.getParameterValues("valores");
		
		String[] tipos = request.getParameterValues("tipo");
		
		for (int i = 0; i < campos.length; i++){
				
				if (!valores[i].equalsIgnoreCase("") && !campos[i].equalsIgnoreCase("")) {
					
					out.println("<h2>Selecciona "+campos[i]+"</h2>");
					
					if (tipos[i].contains("select")) {
						
						out.println ("<select name='" +campos[i]);
						
						if (tipos[i].equalsIgnoreCase("select"))
							out.print("'>");
						else out.print("' multiple>");
							
							
						splitValores = valores[i].split(";");
					
						for (int j=0; j < splitValores.length;j++){
							
							valorSeparado = splitValores[j];
							
							out.println("<option value='"+valorSeparado+"'>"+valorSeparado+"</option>");
								
						}
						
						out.println ("</select>");
						
					} else {
					
						splitValores = valores[i].split(";");
					
						for (int j=0; j < splitValores.length;j++){
							
							valorSeparado = splitValores[j];
							
							out.println("<label>"+valorSeparado+"<label><input type='"+tipos[i]+"' name='"+campos[i]+"' value='"+valorSeparado+"'>");
								
						}
						
					
					}

				}
		
		}
		/*
		for (Map.Entry<String, String[]> entradaParams : parametros.entrySet()) {
		
			if (entradaParams.getKey().equalsIgnoreCase("seleccion")){
			
				for (String valorParam : entradaParams.getValue()){
				
					out.println("<label>"+valorParam+":</label>");
					
					out.println("<input type='text' name='" + valorParam + "'/>");
					
				}
			} else {
			
				valoresParam = entradaParams.getValue();
				
				if (!valoresParam[0].equalsIgnoreCase("") && !valoresParam[1].equalsIgnoreCase("")) {
				
					out.println("<label>"+valoresParam[0]+":</label>");
					
					out.println("<select name='"+valoresParam[0]+"' multiple>");
					
					valoresParam = valoresParam[1].split(";");
					
					for (String valorParam : valoresParam){
					
						out.println("<option value='"+valorParam+"'>"+valorParam+"</option>");
						
					}
					
					out.println("</select>");
					
				}
				
			}
			
		}
		*/
		
		//	while (parametros.hasMoreElements()) {
		//		nombreParam = parametros.nextElement();
		
		// Obtener los valores asociados a este nombre de parámetro
		//		valoresParam = request.getParameterValues(nombreParam);
		
		//		for (int i = 0; i < valoresParam.length; i++) {
		
		//			valorParam = valoresParam[i];
		
		//			if (!nombreParam.equalsIgnoreCase("seleccion")){
		
		// Cómo puedo filtrar por el ";" sin tenerlo
		//				if (valorParam.contains(";")){
		
		//					parametrosProcesados = valorParam.split(";");
		
		//					for (int j = 0; j < parametrosProcesados.length; j++){
		//						out.println("<option value='"+parametrosProcesados[j]+"'>"+parametrosProcesados[j]+"</option>");
		//					}
		
		//					out.println("</select>");
		
		//				} else {
		
		//					if (!valoresParam[1].equalsIgnoreCase("") || !valoresParam[0].equalsIgnoreCase("")) {
		//						out.println("<label>"+valorParam+":</label>");
		//						out.println("<select name='"+valorParam+"' multiple>");
		//					}
		
		
		//				} 
		
		//			} else {
		
		//				out.println("<label>"+valorParam+":</label>");
		//				out.println("<input type='text' name='" + valorParam + "'/>");
		
		//			}
		//		}
		
		//	} 
		
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