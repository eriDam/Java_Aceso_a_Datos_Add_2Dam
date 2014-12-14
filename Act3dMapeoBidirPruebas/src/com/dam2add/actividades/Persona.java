package com.dam2add.actividades;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/*Toda entidad debe:
*Proporcionar un constructor por defecto (ya sea de forma implícita o explícita)
*Ser una clase de primer nivel (no interna)
*No ser final. Implementar la interface java.io.Serializabe si va a ser accedida remotamente
*
*Utilizando arroba Entity delante, indicamos al proveedor de persistencia que será una entidad, 
*(será un objeto de persistencia con el entity manager)Para ser válida,*/
@Entity
public class Persona implements Serializable {

	//utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
	@Id
	private String dni;
	private int edad;
	private String nombre = null;
	

	//Construiremos la relación del otro lado entre la Persona y la EMPRESA Y NOMINA. 
	 
	//Construiremos la relación del otro lado entre la Persona y la EMPRESA Y NOMINA. 
		//El primer paso será añadir unas variable de tipo List<Empresa> dentro de la clase Persona
		//El siguiente paso será anotar la clase de la forma correcta para que soporte la relación. 
		//En este caso utilizaremos la anotación ManyToOne, ya que muchas personas pueden pertenecer a una empresa
		 
	 
	@ManyToOne 
	private Empresa nombreE;
	//Establecemos la relación con la otra tabla
	//la anotación  OneToOne  tiene un parámetro que se denomina mappedBy y cuyo valor es “persona”. Este parámetro hace 
	//referencia a que la relación ya fue construida por la otra clase “Empresa” a traves de su variable “persona”
	@OneToOne(mappedBy = "persona")
	private Nomina retribucion;
	//private List<Empresa> listaEmpresas;
	
	public Persona() {
		
	}
	//Constructor al que le paso Nomina y List empresas
	public Persona(String d, int e,String n, Empresa nomE) {
		dni = d;
		edad = e;
		nombre = n;
		nombreE = nomE;
		
	}
	
	public Empresa getNombreE() {
		return nombreE;
	}

	public void setNombreE(Empresa nombreE) {
		this.nombreE = nombreE;
	}

	public Nomina getRetribucion() {
		return retribucion;
	}

	public void setRetribucion(Nomina retribucion) {
		this.retribucion = retribucion;
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

