import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


public class Empresa implements Serializable {
	    //utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE)
		private long id;
		private String nombre;
		
		//OneToMany es una de las anotaciones mas habituales a nivel de JPA y se encarga de generar
		//una relación de uno a muchos 
		@OneToMany
		//JoinColumn:Esta anotación sirve en JPA para hacer referencia a la columna que es clave externa en la tabla
		//y que se encarga de definir la relación . En este caso la
		//tabla Empresa contendrá una columna persona_dni con el dni de la persona propietaria de la Empresa.
	    //@JoinColumn(name="persona_dni")
	    private Persona persona;
		
		public Empresa( String n, Persona per)  {
		nombre=n;
		persona=per;
		
	}
		//Getters and Setters
		
		public String getNombre() {
			return nombre;
		}
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Persona getPersona() {
			return persona;
		}
		public void setPersona(Persona persona) {
			this.persona = persona;
		}

		public void print(){
			System.out.println("Id: "+id+" Nombre: "+nombre);
			
		}
		

}
