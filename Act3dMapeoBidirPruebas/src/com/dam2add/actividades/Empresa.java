package com.dam2add.actividades;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Empresa implements Serializable {
			//utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
			@Id
			private String nombre;
			
			//OneToMany es una de las anotaciones mas habituales a nivel de JPA y se encarga de generar
			//una relación de uno a muchos 
			 
			@OneToMany
			private Persona persona;
			
			//Constructor incluyendo persona
			public Empresa( String n)  {
				nombre=n;
				//persona=empl;
				
			}
				//Getters and Setters
				
				public String getNombre() {
					return nombre;
				}
				public void setNombre(String nombre) {
					this.nombre = nombre;
				}
				public Persona getPersona() {
					return persona;
				}
				public void setPersona(Persona p) {
					this.persona = p;
				}

				public void print(){
					System.out.println( " Nombre: "+nombre+"Persona id:"+persona);
					
				}
}
