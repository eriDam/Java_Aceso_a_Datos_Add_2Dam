import java.io.IOException;

//import org.basex.query.value.item.Str;
import org.basex.server.ClientSession;

/**
 * @author erika_000
 * 
 * Clase principal que usa un client sesion definido en otra clase que utilizaremos
 * para crear la conexón - consultar - añadir - borrar 
 * 
 * Importamos la libreria externa que se encuentra dentro de archivos de 
 * programa/baseX/baseX.jar
 * 
 * http://xqj.net/javadoc/ (info para siguiente actv.)
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**Creo un objeto de GestionBaseX para utilizar mis métodos creados*/
		GestionBaseX gestorBX = new GestionBaseX();
		
		/**Utilizo el método creado para conectar a la BD*/
		gestorBX.conectar();
		
		/**Comprobamos que hay en la BD utilizando el método creado */
		gestorBX.recuperarPersonasAll();
		
		/**Utilizo los  métodos solicitados en la actividad y creados en GestionBaseX */
		gestorBX.recuperarPersonaPorNombre("Erika");
		gestorBX.recuperarPersonaPorDni("29204528");
		gestorBX.insertarPersona("44235689","Ana", 25 );
		/**comprobamos la inserción consultando todas las personas de nuevo*/
		System.out.println("****************");
		gestorBX.recuperarPersonasAll();
		gestorBX.borrarPersona("29204528");
		System.out.println("*********** Consultando el borrado de Erika");
		gestorBX.recuperarPersonasAll();
		
		
		/**Utilizo el método creado para cerrar la BD*/
		gestorBX.cerrarSesion();
	
	}//Fin main

}//Fin class
