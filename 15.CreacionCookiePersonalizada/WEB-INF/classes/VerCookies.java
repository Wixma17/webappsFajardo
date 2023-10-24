import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class VerCookies extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
	  	RequestDispatcher rd = getServletContext().getRequestDispatcher("/cabecera.html");
		rd.include(request, response);
		
		out.println("<link rel='stylesheet' href='estilorecogida.css'>");
		out.println("</head>");
		out.println("<body>");
		
		Cookie[] galletas = request.getCookies();
		
        out.println("<h2>Modificacion de Cookies personalizada</h2>");
        out.println("<h4>Las cookies hasta el momento son: </h4>");
        out.println("<table>");
        out.println("<tr><td>Nombre</td> <td>Valor</td></tr>");
        for(int i=0;i<galletas.length;i++){
            out.println("<tr><td>"+galletas[i].getName()+"</td><td>"+galletas[i].getValue()+"</td></tr>");
        }
        out.println("</table>");
        out.println("<h2>Modificar Cookies</h2>");
        out.println("<p>Cookie a modificar (Nombre): </p>");

        out.println("<form action='CrearCookies'>");
        out.println("<select name='nuevoNombre'>");
			for(int i=0;i<galletas.length;i++){
				out.println("<option value='"+galletas[i].getName()+"'>"+galletas[i].getName()+"</option>");
			}
        out.println("</select>");
        out.println("<label>Nuevo Valor:</label> <input type='text' name='nuevoValor'/>");
        out.println("<input type='submit' value='Modificar la Cookie'/>");
        out.println("</form>");

        out.println("<form action='index.html'>");
        out.println("<input type='submit' value='Volver'/>");
        out.println("</form>");
		
		rd = getServletContext().getRequestDispatcher("/pie.html");
		rd.include(request, response);
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