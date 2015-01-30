public class Persona {
	//Cremos atributos
	private String dni;
	private String nombre = null;
	private int edad;

	public Persona() {
	}
	//Constructor
	public Persona(String dni, String nom, int edad) {
		
		this.dni = dni;
		this.nombre = nom;
		this.edad = edad;
	}
	//Getter y setters
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
	
	//Metodo print para imprimir
	public void print(){
		System.out.println("Persona con Dni: "+dni+", Nombre: "+nombre+" y edad "+edad);
	}
	
	
	
	
	
}

