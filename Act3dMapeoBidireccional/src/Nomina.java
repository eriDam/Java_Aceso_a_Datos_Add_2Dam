import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Nomina implements Serializable{
			//utilizamos el id para decir que será dni clave primaria, para marcar el atributo de la clase
			@Id
			@GeneratedValue(strategy=GenerationType.SEQUENCE)
			private int id;
			private String retribucion;
			
			//ManyToOne es una de las anotaciones mas habituales a nivel de JPA y se encarga de generar
			//una relación de muchos a uno 
			@ManyToOne
			//JoinColumn:Esta anotación sirve en JPA para hacer referencia a la columna que es clave externa en la tabla
			//y que se encarga de definir la relación . En este caso la tabla Nomina contendrá una columna persona_dni 
			//con el dni de la persona propietaria de la Nomina.
		    @JoinColumn(name="persona_dni")
		    private Persona persona;
	
	public Nomina(int i, String r) {
		id=i;
		retribucion = r;
		
	}
	//Getters and Setters

	public String getRetribucion() {
		return retribucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
