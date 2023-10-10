import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojos.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Acceso extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("listaUsuarios", new ArrayList<Usuario>());
		getServletContext().setAttribute("contadorUsuarios", 0);
	}
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		
		String nombreUsuario = request.getParameter("usuario");
		GregorianCalendar hora = new GregorianCalendar();
		ServletContext contexto = getServletContext();
		
		/*
		Object c  = contexto.getAttribute("contadorUsuario");
		
		if (c == null) {
			contexto.setAttribute("contadorUsuario", 0);
			puestoUsuario = 0;
		}
		
		int contador = (int) c;
		
		
		ArrayList lista = contexto.getAttribute("listaUsuarios");
		
		if (lista == null) {
			lista = new ArrayList<Usuario>();
			contexto.setAttribute("contadorUsuario", lista);
			
		}
		
		ArrayList lista = (ArrayList)contexto.getAttribute("listaUsuarios");
		
		*/
		
		// En la linea de abajo sacamos el puesto de usuario y le sumamos uno 
		int puestoUsuario = (int)contexto.getAttribute("contadorUsuarios") + 1;
		
		// Los tipos básicos de Java se han de subir de nuevo al contexto del Servlet
		contexto.setAttribute("contadorUsuarios", puestoUsuario);
		
		
		
		ArrayList<Usuario> lista = (ArrayList<Usuario>)contexto.getAttribute("listaUsuarios");
		
		Usuario user = new Usuario(nombreUsuario, hora, puestoUsuario);
		
		lista.add(user);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styles/style.css'> ");
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<article>");
		out.println("<h1>Hola "+user.getNombre()+", has conectado a las: "+sdf.format(user.getHoraConexion().getTime())+"</h1>");
		out.println("<h2>Lista de usuarios conectados</h2>");
			for (Usuario usu : lista){
				out.println("<p>"+"<strong>Numero de acceso: </strong>"+usu.getPuestoConexion()+
				"<strong> | Usuario: </strong>"+usu.getNombre()+
				" - "+sdf.format(usu.getHoraConexion().getTime())+"</p>");
			}
			out.println("<form action='Acceso'>");
			out.println("<input type='submit' value='Conectar' name='boton'>");
			out.println("<input type='text' name='usuario' id=''>");
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