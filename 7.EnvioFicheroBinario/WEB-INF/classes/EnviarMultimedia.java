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

public class EnviarMultimedia extends HttpServlet{
	
	
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8"); 
		OutputStream out = response.getOutputStream(); 
		RandomAccessFile raf = null;
		String param = request.getParameter("archivo");
		
		if (param.contains(".mp3")){
			
			response.setContentType("audio/mp3");
			
			raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/"+param)), "r" );
		
		} else if (param.contains(".pdf")) {
		
			response.setContentType("application/pdf");
			
			raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/"+param)), "r" );
			
			
		} else if (param.contains(".jpg")){
			
			response.setContentType("image/jpg");
			
			raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/"+param)), "r" );
		
			
		} else {
			
			response.setContentType("video/mp4");
			
			raf = new RandomAccessFile(new File(getServletContext().getRealPath("/multimedia/"+param)), "r" );

		}
		
			response.setContentLength( (int) raf.length() );
			
			out = response.getOutputStream();
			
			byte [] datas = new byte [ (int) raf.length() ];
			
			while ( (raf.read( datas )) > 0 ){
				out.write( datas );
			}
			out.flush();
		
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