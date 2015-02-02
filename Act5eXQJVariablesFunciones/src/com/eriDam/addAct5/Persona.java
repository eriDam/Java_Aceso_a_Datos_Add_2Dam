package com.eriDam.addAct5;
public class Persona {
	//Cremos atributos
	private String dni;
	private String nombre = null;
	private int edad;
	private String fecha_nacimiento;
    int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Persona() {
	}
	//Constructor
	public Persona(int id, String dni, String nom, String fecha_n, int edad) {
		this.id = id;
		this.dni = dni;
		this.nombre = nom;
		this.fecha_nacimiento = fecha_n;
		this.edad = edad;
		
	}
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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

