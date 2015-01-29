import java.io.IOException;

import org.basex.query.value.item.Str;
import org.basex.server.ClientSession;

/**
 * @author erika_000
 * 
 * Clase principal que usa un client sesion que utilizaremos
 * para crear la conexón
 * 
 * Importamos la libreria externa que se encuentra dentro de archivos de 
 * programa/baseX/baseX.jar
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**Creamos el clientSession*/
		ClientSession session = null;
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
		
			/**la consulta que queremos realizar dentro de un objeto String que llamare cadConsulta
			 * y posteriormente para ejecutar la consulta lo podemos utilizar con un objeto de tipo query
			 * */
			String cadConsulta = "for $c in doc('personas')/personas/persona return $c/nombre";
			
			/**Ejecutamos la consulta*/
			System.out.println("Ejecutamos la consulta: " + cadConsulta);
			System.out.println(session.query(cadConsulta).execute());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//cerramos sesion 
			try {
				if (session != null)
					session.close();
				System.out.println("Sesion cerrada correctamente");
			} catch (Exception e2) {
				 e2.printStackTrace();
				 System.out.println("Error al cerrar la sesion...");
			}
		}
	}

}
