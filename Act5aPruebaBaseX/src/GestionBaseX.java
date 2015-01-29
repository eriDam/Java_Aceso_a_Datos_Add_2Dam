import java.io.IOException;
import java.util.List;

import org.basex.server.ClientSession;


public class GestionBaseX {
		/**Creamos el clientSession*/
	    ClientSession session = null;
		public GestionBaseX(){
			
		}
		
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//cerramos sesion 
		public void cerrarSesion(){
			try {
			if (session != null)
				session.close();
			System.out.println("Sesion cerrada correctamente");
		} catch (Exception e2) {
			 e2.printStackTrace();
			 System.out.println("Error al cerrar la sesion...");
		}
		}
		
			public void recuperarPersonaPorNombre(String nombre){ 
				
				try {
					/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
//					 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
//					 * */
					String cadConsulta = "doc ('personas')/personas/persona[nombre='"+nombre+"']";
					/**Ejecutamos la consulta*/
					System.out.println("Ejecutamos la consulta: " + cadConsulta);
					System.out.println(session.query(cadConsulta).execute());
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
		 * @return */
		public void recuperarPersonaPorDni(String dni){
			
			try {
				/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
			 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
			 * */
			 String  cadConsulta = "for $c in doc('personas')/personas/persona return $c/dni";
			 
			/**Ejecutamos la consulta*/
			System.out.println("Ejecutamos la consulta: " + cadConsulta);
				System.out.println(session.query(cadConsulta).execute());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**Método para insertar personas
		Añade la persona con los datos pasados por parámetro. Para ello se
		realizará una instrucción Xquery utilizando “insert node” y utilizando las
		etiquetas adecuadas para cada elemento.*/
		public void insertarPersona(String nombre, int edad, String dni){
        // Guardo la consulta en una cadena
		String cadInsert = "insert node <persona><nombre>" + nombre
		+ "</nombre><edad>" + edad + "</edad><dni>" + dni
		+ "</dni></persona> into /personas";
		}

}
