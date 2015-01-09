public class Persona {

	private String dni;
	private String nombre = null;
	private int edad;

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
		System.out.println("Dni: "+dni+" Nombre: "+nombre+" y edad "+edad);
	}
	
	
	
	
	
}

