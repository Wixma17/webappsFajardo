package pojos;

import java.util.GregorianCalendar;

public class Usuario {
	private String nombre;
	private GregorianCalendar horaConexion;
	private int puestoConexion;
	
	public Usuario (){
	}
	
	public Usuario (String nombre, GregorianCalendar horaConexion, int puestoConexion){
		this.nombre = nombre;
		this.horaConexion = horaConexion;
		this.puestoConexion = puestoConexion;
	}
	
	public String getNombre (){
		return this.nombre;
	}
	
	public GregorianCalendar getHoraConexion (){
		return this.horaConexion;
	}
	
	public int getPuestoConexion (){
		return this.puestoConexion;
	}
	
	public void setNombre (String nombre){
		this.nombre = nombre;
	}
	
	public void setHoraConexion (GregorianCalendar horaConexion){
		this.horaConexion = horaConexion;
	}
	
	public void setPuestoConexion (int puestoConexion){
		this.puestoConexion = puestoConexion;
	}
	
}