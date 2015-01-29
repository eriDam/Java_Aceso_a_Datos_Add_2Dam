import java.io.IOException;
import java.util.List;

import org.basex.server.ClientSession;


public class GestionBaseX {
		/**Creamos el clientSession*/
	    ClientSession session = null;
		public GestionBaseX(){
			
		}
		
		/**
		 * Método para CONECTAR a la BD
		 * */
		public void conectar(){
			
			/**Es necesario ejecutar dentro de un try catch para capturar posibles excepciones*/
			try {
				/**Los parameros de creación de este objeto ClientSession son:
				 * @parameter localhost desde donde nos vamos a conectar
				 * @parameter 1984 el puerto  por que nos 
				 * vamos a conectar desde el código, en la consola sale 8984 que es el http...
				 * @parameter admin usuario
				 * @parameter admin passwd
				 * */
				session = new ClientSession("localhost",1984, "admin","admin");
				System.out.println("Has conectado a la Base de datos, gestionala de forma responsable");
				System.out.println("*****************************");
			} catch (IOException e) {
				System.out.println("Problema al conectar con la Base de datos");
				e.printStackTrace();
			}
		}
		
		/**
		 * Método donde CERRAR sesion en la bd
		 * */ 
		public void cerrarSesion(){
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
		public void recuperarPersonasAll(){ 
			
			try {
				/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
//				 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
//				 * */
				String cadConsultaAll = "doc ('personas')/personas/persona";
				/**Ejecutamos la consulta*/
				System.out.println("Ejecutamos la consulta : \n" + cadConsultaAll);
				
				System.out.println(session.query(cadConsultaAll).execute());
				System.out.println("*****************************");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	 
		
		
		/**
		 * Método donde RECUPERAR Persona pasandole un parametro nombre en la clase principal
		 * */ 
			public void recuperarPersonaPorNombre(String nombre){ 
				
				try {
					/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
//					 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
//					 * */
					String cadConsultaN = "doc ('personas')/personas/persona[nombre='"+nombre+"']";
					/**Ejecutamos la consulta*/
					System.out.println("Ejecutamos la consulta: " + cadConsultaN);
					
					System.out.println(session.query(cadConsultaN).execute());
					System.out.println("*****************************");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			
		}
//			public List<Persona> recuperarPersonaPorNombre(String nombre){
			//
//						/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
//						 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
//						 * */
//						List<Persona> listaPers = new ArrayList();
//						//String cadConsulta = "for $c in doc('personas')/personas/persona return $c/nombre";
//						String cadConsulta = "doc ('personas')/personas/persona[nombre='"+nombre+ "']";
//						listaPers.add(cadConsulta);
//						return null;
			
			
		/**Método que devuelve la persona que coincide con el dni pasado por parámetro.
		 * @return dni parametro que le pasamos en la clase principal */
		public void recuperarPersonaPorDni(String dni){
			
			try {
				/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
			 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
			 * */
			 String  cadConsulta = "doc('personas')/personas/persona[dni='"+dni+"']";
			 
			/**Ejecutamos la consulta*/
			System.out.println("Ejecutamos la consulta: " + cadConsulta);
				System.out.println(session.query(cadConsulta).execute());
				System.out.println("*****************************");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**Método para insertar personas
		Añade la persona con los datos pasados por parámetro. Para ello se
		realizará una instrucción Xquery utilizando “insert node” y utilizando las
		etiquetas adecuadas para cada elemento.*/
		public void insertarPersona(String dni, String nombre,int edad ){
        // Guardo la consulta en una cadena
		String cadInsert = "insert node <persona><dni>" + dni
		+ "</dni><nombre>" + nombre + "</nombre><edad>" + edad
		+ "</edad></persona> into /personas";
		System.out.println("Se ha insertado correctamente: "+cadInsert);
		System.out.println("*****************************");
		}
		
		/**. Borra el nodo correspondiente
			a la persona que coincided con el dni pasado por parámetro. Para ello se
			realizará una instrucción Xquery utilizando “delete node*/
		public void borrarPersona(String dni){
			String cadBorrado = "delete node <persona><dni>"+dni+ "into /personas";
					// doc("personas")//personas/persona/dni"
			System.out.println("Se ha borrado correctamente: "+cadBorrado);
			System.out.println("*****************************");
		}
}
