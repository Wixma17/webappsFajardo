import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class enviar2 extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		Enumeration param;
		String guardar;
		Map<String, String[]> paramMap;
		String [] valores;
		Iterator<String> it;
		String clave;
		
		out.println("<html> <head> <link rel='stylesheet' href='estilo.css' type='text/css' /> </head>");
		out.println("<body>");
		
		out.println("<h1>get Parameter</h1>");
		
		out.println("<div>");
		param = request.getParameterNames();//getparameternames para obtener los parámetros (devuelve objeto enum)
		
		while (param.hasMoreElements()){
			guardar = (String)param.nextElement();
		    
			//getparameter() para obtener los valores del parametro especificado
			out.println(guardar+": "+ request.getParameter(guardar)+"<br>");
		}
		out.println("<br>");
		
		
		out.println("<h1>Parameter Values</h1>");
		param = request.getParameterNames(); //getparameternames para obtener los parámetros (devuelve objeto enum)
		while (param.hasMoreElements()){
			out.println("<p>");
			guardar = (String)param.nextElement();
			out.println(guardar+": "); //getparameterValues para obtener los parámetros multievaluados (devuelve array)
			//Falta
			for(int i= 0; i<request.getParameterValues(guardar).length; i++){
			out.println(request.getParameterValues(guardar)[i]);}
			out.println("</p>");
		}
		out.println("<br>");
		
		out.println("<h1>Parameter Map</h1>");
		paramMap = request.getParameterMap(); //getParameterMap para obtener el map con todas las claves y valores de los parametros (objeto map).
		it=paramMap.keySet().iterator();
		
		while(it.hasNext()){
			clave=(String)it.next();
			out.println(clave+": ");

			valores= (String[])paramMap.get(clave);
			
			for(String valorP : valores)
				{		
			
					out.println(valorP);			
				
				}
			out.println("<br/>");
			}
		
		
		out.println("</div>");
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