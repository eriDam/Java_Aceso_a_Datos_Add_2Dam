

public class Persona {
	/**Atributos de persona*/
	private String dni;
	private String nombre = null;
	private int edad;
	//Vehiculo vehiculo;

	public Persona() {
	}

	public Persona(String n, int e, String d) {
		nombre = n;
		edad = e;
		dni = d;
	}
	
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}
	
	public void setDni(String d) {
		dni = d;
	}

	public void setNombre(String n) {
		nombre = n;
	}

	public void setEdad(int e) {
		edad = e;
	}
	
	public void print(){
		System.out.println("Dni: "+dni+", con nombre: "+nombre+" y edad "+edad);
	}

	
	
	/**
	   * Devuelve todos los atributos(dni, nombre,edad) de la persona en forma de String
	   * @return los atributos de la persona
	   */
	    
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", edad=" + edad
				+ "]";
	}
	
	
}

