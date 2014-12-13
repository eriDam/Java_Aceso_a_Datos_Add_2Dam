package Almacen_Persona;

import java.io.Serializable;

//Las clases que queramos serializar, tienen que implementar la clase serializable
public class Persona implements Serializable {

	//Definimos 2 atributos a Persona
	private String nombre=null;
	private int edad = 0;
	
	public Persona(){
		
	}
	
	public Persona(String n, int e){
		nombre = n;
		edad = e;
	}
	//Generamos los métodos getters and setters para acceder a los atributos. 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void print() {
		//Imoprimimos la información de persona cuando llamemos a este método
		
		System.out.println("El nombre es: "+nombre+" y tiene una edad de: "+edad);
		
	}
	
}
