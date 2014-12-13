import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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

	public Persona() {
	}

	public Persona(String d, int e,String n ) {
		dni = d;
		edad = e;
		nombre = n;
	}
	public Persona(String n) {
		
		nombre = n;
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
		//System.out.println("Nombre: "+nombre+" y edad "+edad);
	}
	
}

