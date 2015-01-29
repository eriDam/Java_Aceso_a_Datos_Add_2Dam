public class Persona {

	private String dni;
	private String nombre = null;
	private int edad;
	private Vehiculo vehiculo = null;

	public Persona() {
	}

	public Persona(String n, int e, String d, Vehiculo v) {
		nombre = n;
		edad = e;
		dni = d;
		vehiculo = v;
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

	public Vehiculo getVehiculo() {
		return vehiculo;
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

	public void setVehiculo(Vehiculo v) {
		vehiculo = v;
	}

	public void print() {
		System.out.println("Dni: " + dni + " Nombre: " + nombre + " y edad "
				+ edad + ". Su vehículo es un: " + vehiculo.getMarca() + " "
				+ vehiculo.getModelo() + " con matrícula "
				+ vehiculo.getMatricula());
	}

}
