import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.*;
import java.util.*;

public class AcertarNumeros extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		String numerosParaTratar = getServletConfig().getInitParameter("numeros");
		String[] numerosSplit = numerosParaTratar.split(";");
		List<String> listaNumerosAux = Arrays.asList(numerosSplit);
		ArrayList<String> listaNumeros = new ArrayList<String>(listaNumerosAux);
		
		getServletContext().setAttribute("listaNumeros", listaNumeros);
		getServletContext().setAttribute("mapaPremios", new HashMap(String, Integer));
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		String intentoNumero = request.getParameter("numeroIntroducido");
		
		
		
		ServletContext contexto = getServletContext();
		ArrayList<String> numeros = (ArrayList<String>)contexto.getAttribute("listaNumeros");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		
		out.println("</head>");
		out.println("<body>");
		for (String numero : numeros)
			out.println(numero + " ");
		out.println("<article>");
				
			
		out.println("</article>");
		
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