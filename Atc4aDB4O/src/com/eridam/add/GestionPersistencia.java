package com.eridam.add;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class GestionPersistencia {

	/**
	 * En db4o existen tres formas de realizar consultas, cada una basada en una
	 * tecnología diferente. Estas son: •
	 * S.O.D.A.: Simple Object Database Access / Acceso Simple a Bases de Datos de Objetos.
	 *  Descender y restringir son básicamente las ideas principales con S.O.D.A.. Descender por una
	 * consulta da como resultado otra consulta, a la cual
	 * podemos descender nuevamente y realizar la restricción que nos interese:
	 * 
	 * Q.B.E.: Query By Example / Consulta por Ejemplo o Plantilla. •
	 * N.Q.: Native Queries / Consultas Nativas
	 */

	/**
	 * Método que Permite añadir la persona pasada por parámetro.
	 */

	public static void insertarPersona(ObjectContainer baseDatos,
			Persona persona) {
		try {
			baseDatos.store(persona);
			/**
			 * Se inserta igualmente aunque no se realice el commit, pero para
			 * forzar el flujo de datos del contenedor se realiza
			 */
			baseDatos.commit();

			// Informa de que se ha almacenado ok o saltará la excepcion
			System.out.println("Se ha almacenado correctamente la persona");
		} catch (Exception e) {
			System.out.println("Se ha producido un error en la insercion");
		}
	}
	
	/**
     * Imprime por pantalla el resultado de una consulta da igual el metodo que use
     *  
     *He tenido que crear el método to string en Persona para que me devuelva los datos en forma de string
     * @param resultado el objeto en el que se ha almacenado el resultado de la
     * consulta
     */
    public static void imprimirResultadoConsulta(ObjectSet resultado) {
        System.out.println("Recuperados " + resultado.size() + " Objetos");
        while (resultado.hasNext()) {
            System.out.println(resultado.next());
        }

    }
    /**
     * Permite realizar una consulta SODA cuyo resultado sean todos las personas
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODATodasPersonas(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Persona.class);//declara las restricciones
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    
	/**
	 * Devuelve la lista de personas que coincidan con el nombre pasado por
	 * parámetro. SODA
	 */
	public static List<Persona> recuperarPersonaPorNombre(ObjectContainer baseDatos, String nombre) {
		try {
			//  Instanciamos un objeto query para realizar las consultas
			Query q = baseDatos.query();
			//  Indicamos el objeto contra el cual se realizará la consulta
			q.constrain(Persona.class);
			/**creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la 
			restricion es el parametro nombre*/
			Constraint constraint = q.descend("nombre").constrain(nombre);
			List<Persona> resultado = q.execute();
		
			for (Persona p2 : resultado) {
				System.out.println("Persona recuperada:");
				p2.print();
				
			}

		} catch (Exception e) {
			System.out.println("Se ha producido un error en la consulta");
		}
		return null;
	}

	/**SODA
	 * Devuelve la lista de personas cuyo nombre empiece por la cadena pasada
	 * por parámetro. Para ello podemos utilizar una restricción “startsWith”.
	*/
	public static List<Persona> recuperarPersonaPorInicial(ObjectContainer baseDatos, String inicio) {
		try {
			//  Instanciamos un objeto query para realizar las consultas
			Query qInicial = baseDatos.query();
			//  Indicamos el objeto contra el cual se realizará la consulta
			qInicial.constrain(Persona.class);
			qInicial.descend("nombre").constrain("P").startsWith(true);// the field 'name' starts with  'P', case-insensitive
						List<Persona> resultado = qInicial.execute();

						for (Persona p2 : resultado) {
							System.out.println("Persona recuperada con la inicial:");
							p2.print();
						}

					} catch (Exception e) {
						System.out.println("Se ha producido un error en la consulta");
					}
		return null;

	}

	
	  /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un dni e
     * imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el dni de la persona que se quiere buscar
     *
     */
    public static void consultarQBEPersonasDni(ObjectContainer baseDatos, String dni) {
        Persona persona = new Persona(null,0,dni);
        ObjectSet resultado = baseDatos.queryByExample(persona);
        imprimirResultadoConsulta(resultado);
        
     /**
	 * SODA: Devuelve la persona que coincide con el dni pasado por parámetro.
	 */
    }
	public static Persona recuperarPersonaPorDni(ObjectContainer baseDatos, String dni) {
		try {
			//  Instanciamos un objeto query para realizar las consultas
			Query q1 = baseDatos.query();
			//  Indicamos el objeto contra el cual se realizará la consulta
			q1.constrain(Persona.class);
			/**creamos el constraint diciendo que el campo donde lo tiene que aplicar es dni y la 
			restricion es el parametro dni*/
			Constraint constraint1 = q1.descend("dni").constrain(dni);
			List<Persona> resultado1 = q1.execute();

			for (Persona p2 : resultado1) {
				System.out.println("Persona recuperada:");
				p2.print();
			}

		} catch (Exception e) {
			System.out.println("Se ha producido un error en la consulta");
		}return null;

	}

	/**
	 * . Devuelve la lista de personas cuya edad esté comprendida en el rango de
	 * edades pasadas por parámetro.
	 */
//	public List<Persona> recuperarPersonaPorEdad(int menor, int mayor) {
//		return null;
//
//	}
	/**
     * Permite realizar una consulta SODA que recupera las perosnas cuya edad
     * esta entre los indicados por parametro
     *
     * @param baseDatosla base de datos desde la que se va a operar
     * @param edadMenor la edad que marca limite inferior
     * @param edadMayor la edad que marcha el limite superior
     */
    public static void consultaSODAPersonaEdadEntre(ObjectContainer baseDatos, int edadMenor, int edadMayor) {
        Query qEdad = baseDatos.query();
        qEdad.constrain(Persona.class);
        //creamos el primer constraint diciendole que la edad ha de ser menor del mayor
        Constraint constraint = qEdad.descend("edad").constrain(edadMayor).smaller();
        //se enlazan las dos restricciones a aplicar
        qEdad.descend("edad").constrain(edadMenor).greater().and(constraint);
        ObjectSet resultado = qEdad.execute();
        imprimirResultadoConsulta(resultado);
    }

	
	/**Borrar un objeto*/
	public static void borrar(ObjectContainer baseDatos, Persona pers) throws Db4oException { 
		baseDatos.delete(pers); 
		baseDatos.commit();
		} 

	/**
	 * Permite cerrar la conexion a la base de datos que se esta utilizando
	 * 
	 * @param baseDatos
	 *            el ObjectContainer de la base de datos
	 */
	public static void cerrarConexion(ObjectContainer baseDatos) {
		try {
			baseDatos.close();
			System.out.println("Has cerrado la conexión correctamente");
		} catch (Exception e) {
			System.out.println("Error al cerrar la conexión");
		}
	}

}
