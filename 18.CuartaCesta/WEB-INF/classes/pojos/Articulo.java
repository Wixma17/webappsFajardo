package pojos;

public class Articulo {
	
	private String nombre;
	private int existencias;
	
	public Articulo (){
	}
	
	public Articulo (String nombre, int existencias){
		this.nombre = nombre;
		this.existencias = existencias;
	}
	
	public String getNombre (){
		return this.nombre;
	}
	
	public int getExistencias (){
		return this.existencias;
	}
	
	public void setNombre (String nombre){
		this.nombre = nombre;
	}
	
	
	public void setExistencias (int existencias){
		this.existencias = existencias;
	}
	
	public boolean restarUnidades (int cantidad){
		if (existencias >= cantidad){
			existencias = existencias - cantidad;
			return true;
		} else return false;
	}
	
}