import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import org.basex.server.ClientSession;

public class GestionBaseX {
	/** Creamos el objeto clientSession y un objeto String para la BD*/
	ClientSession session = null;
	String bdBaseX="personas";

	public GestionBaseX() {

	}

	/**
	 * Método para CONECTAR a la BD
	 * */
	public void conectar() {

		/**
		 * Es necesario ejecutar dentro de un try catch para capturar posibles
		 * excepciones
		 */
		try {
			/**
			 * Los parameros de creación de este objeto ClientSession son:
			 * 
			 * @parameter localhost desde donde nos vamos a conectar
			 * @parameter 1984 el puerto por que nos vamos a conectar desde el
			 *            código, en la consola sale 8984 que es el http...
			 * @parameter admin usuario
			 * @parameter admin passwd
			 * */
			session = new ClientSession("localhost", 1984, "admin", "admin");
			session.execute("open "+bdBaseX);
			System.out
					.println("Has conectado a la Base de datos, gestionala de forma responsable");
			System.out.println("*****************************");
		} catch (IOException e) {
			System.out.println("Problema al conectar con la Base de datos");
			e.printStackTrace();
		}
	}

	/**
	 * Método donde CERRAR sesion en la bd
	 * */
	public void cerrarSesion() {
		try {
			if (session != null)
				session.close();
			System.out.println("*****************************");
			System.out.println("Sesion cerrada correctamente");
		} catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("Error al cerrar la sesion...");
			System.out.println("*****************************");
		}
	}

	/**
	 * Método donde RECUPERAR TODAS las Personas
	 *
	 * */
	public void recuperarPersonasAll() {

		try {
			/**
			 * la consulta que queremos realizar dentro de un objeto String que
			 * llamare cadConsulta // * y posteriormente para ejecutar la
			 * consulta lo podemos utilizar con un objeto de tipo query // *
			 */
			conectar();
			String cadConsultaAll = "doc ('personas')/personas/persona";
			/** Ejecutamos la consulta */
			System.out.println("Ejecutamos la consulta para ver que hay en la bd  : \n" + cadConsultaAll);

			System.out.println(session.query(cadConsultaAll).execute());
			System.out.println("*****************************");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			cerrarSesion();
		}
	}

	/**
	 * Método donde RECUPERAR Persona pasandole un parametro nombre en la clase
	 * principal
	 * */
	public void recuperarPersonaPorNombre(String nombre) {

		try {
			/**
			 * la consulta que queremos realizar dentro de un objeto String que
			 * llamare cadConsulta // * y posteriormente para ejecutar la
			 * consulta lo podemos utilizar con un objeto de tipo query // *
			 */
			conectar();
			String cadConsultaN = "doc ('personas')/personas/persona[nombre='"
					+ nombre + "']";
			/** Ejecutamos la consulta */
			System.out.println("Ejecutamos la consulta: " + cadConsultaN);

			System.out.println(session.query(cadConsultaN).execute());
			System.out.println("*****************************");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			cerrarSesion();
		}

	}

	 public List<Persona> recuperarPersonaPorNombreLista(String nombre){
	
	 /**la consulta que queremos realizar dentro de un objeto String que
	 llamare cadConsulta
	 * y posteriormente para ejecutar la consulta lo podemos utilizar con un
	 objeto de tipo query
	 * */
		 try {
			/**Conectamos a la Bd*/
			conectar();
		
			List<Persona> listaPers = new ArrayList();
			/**Creamos un string para realizar la Consulta*/
			//String cadConsulta ="for $c in doc('personas')/personas/persona return $c/nombre";
		    String cadConsulta = "doc ('personas')/personas/persona[nombre='"+nombre+"']";
		   //
	   
		    /**Ejecutamos consulta*/
		    System.out.println("Ejecutada la consulta: " + cadConsulta);
			System.out.println(session.query(cadConsulta).execute());
	    
		} catch (Exception e) {
			System.out.println("Error en la consulta");
			e.printStackTrace();
		} finally {
			cerrarSesion();
		}
		return null;
	

	 }
     

	/**
	 * Método que devuelve la persona que coincide con el dni pasado por
	 * parámetro.
	 * 
	 * @return dni parametro que le pasamos en la clase principal
	 */
	public void recuperarPersonaPorDni(String dni) {

		try {
			/**
			 * La consulta que queremos realizar dentro de un objeto String que
			 * llamare cadConsulta y posteriormente para ejecutar la consulta lo
			 * podemos utilizar con un objeto de tipo query
			 * */
			/**Conectamos a la Bd*/
			conectar();
			
			/**Creamos un string para realizar la Consulta*/
			String cadConsultaDni = "doc ('personas')/personas/persona[dni='"+dni+"']";

			/** Ejecutamos la consulta */
			System.out.println("Ejecutamos la consulta por dni:  " + cadConsultaDni);
			System.out.println(session.query(cadConsultaDni).execute());
			System.out.println("*****************************");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Se ha producido un error con la consulta por dni");
		}finally{
			cerrarSesion();
		}
	}

	/**
	 * Método para insertar personas Añade la persona con los datos pasados por
	 * parámetro. Para ello se realizará una instrucción Xquery utilizando
	 * “insert node” y utilizando las etiquetas adecuadas para cada elemento.
	 */
	public void insertarPersona(String dni, String nombre, int edad) {
		try {
			/**Conectamos a la Bd*/
			conectar();
			
			/**Creamos un string para realizar la Consulta*/
			String cadInsert = "insert node  <persona><dni>" + dni
				+ "</dni><nombre>" + nombre + "</nombre><edad>" + edad
				+ "</edad></persona> into doc('personas') /personas";
			XQuery queryInsert = new XQuery(cadInsert);
			/** Ejecutamos la XQuery */
			session.execute(queryInsert);
			
			/** Ejecutamos la consulta de insercion */
			System.out.println("Ejecutamos la inserción de: " + cadInsert);
			System.out.println(session.query(cadInsert).execute());
	 
			
		//Context resultado = new Context();
		//System.out.print("El resultado se está actualizando");
		// (queryInsert).execute());
		//resultado.update();
			
		System.out.println("Se ha insertado correctamente");
		} catch (Exception e) {
			System.out.println("Se ha producido un error insertando");
		}finally{
			cerrarSesion();
		}
		System.out.println("*****************************");
	}

	/**
	 * . Borra el nodo correspondiente a la persona que coincide con el dni
	 * pasado por parámetro. Para ello se realizará una instrucción Xquery
	 * utilizando “delete node
	 */
	public void borrarPersona(String dni) throws IOException {
		try {
			/**Conectamos a la Bd*/
			conectar();
			
			/**Creamos un string para realizar el borrado*/
			String cadBorrado = "delete node doc('personas')/personas/persona[dni='"+dni+"']";
			/** Creo el objeto Xquery para borrado en la bd y le paso el String de borrado*/
			XQuery queryDelete = new XQuery(cadBorrado);
			/** Ejecutamos la XQuery de borrado en la bd */
			session.execute(queryDelete);
			
			//Context resBorradoactual = new Context();
			// doc("personas")//personas/persona/dni"
			// session.execute(queryDelete);
			//resBorradoactual.update();
			
			/** Ejecutamos la consulta */
			System.out.println("Ejecutamos el borrado de: " + cadBorrado);
			System.out.println(session.query(cadBorrado).execute());

			System.out.println("Se ha borrado correctamente: " + cadBorrado);
			System.out.println("*****************************");
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				cerrarSesion();
			}
		
		

	}

	/**
	 * . Exporta la base de datos con la que estamos trabajando al fichero
	 * pasado por parámetro. Para ello, se utilizará el método “export” sobre el
	 * objeto ClientSession.
	 */
	public void exportar(String destino) throws IOException {
		try {
			/**Conectamos a la Bd*/
			conectar();
			/**Creamos un string para realizar la exportación*/
			String cadExport="export c://" + destino;
		
			System.out.println("Se ha exportado correctamente");
		    System.out.println(session.execute(cadExport));
		    //cadExport.
		} catch (Exception e) {
		System.out.println("Error, no exportado");
		e.printStackTrace();
		}finally{
			cerrarSesion();
		}
		
	}
}
