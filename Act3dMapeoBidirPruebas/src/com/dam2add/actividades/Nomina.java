package com.dam2add.actividades;

import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Nomina {
	//utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
	@Id
	private String retribucion;
	
	//una relación de uno a uno 
	@OneToOne
	private Persona persona;
	
	public Nomina(){
		
	}
	
	//constructor incluyendo persona
	public Nomina( String r, Persona nominas) {
		 
		retribucion = r;
		persona = nominas;
		
	}
	//Getters and Setters

	public String getRetribucion() {
		return retribucion;
	}

	public void setRetribucion(String retribucion) {
		this.retribucion = retribucion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	//Método imprimir  
		public void print(){
			System.out.println("Nombre: " +retribucion+"Nominas"+persona);
		}

}
