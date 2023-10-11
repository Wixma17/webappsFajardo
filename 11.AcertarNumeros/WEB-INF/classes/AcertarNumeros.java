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
		
		// Esto podría saltarse
		List<String> listaNumerosAux = Arrays.asList(numerosSplit);
		
		ArrayList<String> listaNumeros = new ArrayList<String>(listaNumerosAux);
		
		getServletContext().setAttribute("listaNumeros", listaNumeros);
		
		getServletContext().setAttribute("mapaPremios", new HashMap<String, Integer>());
		
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		String intentoNumero = request.getParameter("numeroIntroducido");
		
		ServletContext contexto = getServletContext();
		ArrayList<String> numeros = (ArrayList<String>)contexto.getAttribute("listaNumeros");
		HashMap<String, Integer> mapaGanadores = (HashMap<String, Integer>)contexto.getAttribute("mapaPremios");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		out.println("</head>");
		out.println("<body>");
		out.println("<article>");
		
		if (!numeros.isEmpty()) {
		
			if (numeros.contains(intentoNumero)){
				
				if (mapaGanadores.containsKey(nombreUsuario)) {
					Integer vecesGanadas = mapaGanadores.get(nombreUsuario);
					vecesGanadas++;
					mapaGanadores.put(nombreUsuario, vecesGanadas);
				} else {
					mapaGanadores.put(nombreUsuario, 1);
				}
				
				numeros.remove(intentoNumero);
				
				if (numeros.size() == 0)
					out.println("<h1>Felicidades "+nombreUsuario+", has acertado el último número</h1>");
				else out.println("<h1>Felicidades "+nombreUsuario+", quedan "+numeros.size()+" aún, suerte!</h1>");
				
				
				for (Map.Entry<String, Integer> entrada : mapaGanadores.entrySet()){
					out.println(entrada.getKey() + " | nº victorias: "+ entrada.getValue()+"<br>");
				}
				
			} else {
				out.println("<h1>Lo sentimos "+nombreUsuario+", ha fallado, quedan "+numeros.size()+" aún, suerte!</h1>");
			}
		
		} else {
		
			out.println("<h1>Han terminado los números "+nombreUsuario+", nos vemos</h1>");
			out.println("<h2>Lista de ganadores:</h2>");
			for (Map.Entry<String, Integer> entrada : mapaGanadores.entrySet()){
					out.println(entrada.getKey() + " | nº victorias: "+ entrada.getValue()+"<br>");
			}
		
		}
		
		out.println("<form action='index.html'>");
		out.println("<input type='submit' value='Volver' name='boton'>");
		out.println("</form>");
		
		
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