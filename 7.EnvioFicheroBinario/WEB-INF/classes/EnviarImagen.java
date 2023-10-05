import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class EnviarImagen extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("image/jpg");
		
		OutputStream out = response.getOutputStream();
		
		String param = request.getParameter("boton");
		
		if (param.contains("Directa")){
			
			RandomAccessFile raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/mondongo.jpg")), "r" );
			
			response.setContentLength( (int) raf.length() );
			
			out = response.getOutputStream();
			
			byte [] datas = new byte [ (int) raf.length() ];
			
			while ( (raf.read( datas )) > 0 ){
				out.write( datas );
			}
			out.flush();
				
			
		} else if (param.contains("casho")) {
		// Abre el archivo en modo de solo lectura
			RandomAccessFile raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/mondongo.jpg")), "r");
			response.setBufferSize(10);
            response.setContentLength( (int) raf.length() );
            out = response.getOutputStream();
            int b;
            while ( (b=raf.read( )) !=-1 ){
                out.write( (byte)b );
                response.flushBuffer();
            }
		
		} else {
		
		response.sendRedirect("multimedia/mondongo.jpg");
		
		}
		
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