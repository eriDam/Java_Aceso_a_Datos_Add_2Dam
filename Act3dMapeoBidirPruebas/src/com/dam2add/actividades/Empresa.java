package com.dam2add.actividades;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Definiremos la entidad para que sea persistente
@Entity
public class Empresa implements Serializable {
			//utilizamos el id para decir que será nombre  clave primaria, para marcar el atributo de la clase
			@Id
			@GeneratedValue (strategy=GenerationType.AUTO)
			private int id; //el id será la clave autogenerada
			private String nombre;
			
			//Marcamos la relacion: OneToMany es una de las anotaciones mas habituales a nivel de JPA y se encarga de generar
			//una relación de uno a muchos con la clase persona (una Empresa puede tener muchas personas)
			// CascadeType.ALL = Todas las operaciones (MERGE, PERSIST, REFRESH, REMOVE) 
			// afectan en cascada al resto de personas relacionadas.
			// mappedBy = Indica el atributo que establece la relación en la otra entidad. 
			@OneToMany (cascade = CascadeType.ALL, mappedBy = "empresa")
			private List<Persona> personas;
			
			//Constructor no incluyendo persona 
			public Empresa( String n )  {
				this.nombre=n;
				this.personas = null;
				 
				
			}
			
				
				 //Método para imprimir

				public void printEmpresa(){
					System.out.println( " Nombre: "+nombre+"Persona id:"+personas);
					
				}

				//Getters and Setters

				public int getId() {
					return id;
				}



				public void setId(int id) {
					this.id = id;
				}



				public String getNombre() {
					return nombre;
				}



				public void setNombre(String nombre) {
					this.nombre = nombre;
				}



				public List<Persona> getPersonas() {
					return personas;
				}



				public void setPersonas(List<Persona> personas) {
					this.personas = personas;
				}
}
