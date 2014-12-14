package com.dam2add.actividades;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Nomina implements Serializable{
	//utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
	@Id
	private int retribucion;
	
	//una relación de uno a uno 
	@OneToOne
	private Persona persona;
	
	public Nomina(){
		
	}
	
	//constructor incluyendo persona
	public Nomina( int r, Persona nominas) {
		 
		retribucion = r;
		persona = nominas;
		
	}
	//Getters and Setters

	public int getRetribucion() {
		return retribucion;
	}

	public void setRetribucion(int retribucion) {
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
