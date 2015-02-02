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

	public static void main(String[] args) throws IOException {
		/**Creo un objeto de GestionBaseX para utilizar mis métodos creados*/
		GestionBaseX gestorBX = new GestionBaseX();
		

		
		/**Comprobamos que hay en la BD utilizando el método creado */
		gestorBX.recuperarPersonasAll();
		
		String fichero;
		fichero = "personaSave.txt";
		
		gestorBX.insertarPersona("2920528W", "Erika", 32);
		/**comprobamos la inserción consultando todas las personas de nuevo*/
		System.out.println("****************");
		gestorBX.recuperarPersonasAll();
		gestorBX.recuperarPersonaPorNombreLista("Tara");
		gestorBX.recuperarPersonaPorNombre("Ana");
		
		gestorBX.recuperarPersonaPorDni("2920528W");
		
		gestorBX.borrarPersona("29244555");
		System.out.println("*********** Consultando el borrado de 29244555");
		/**Comprobamos que hay en la BD utilizando el método creado */
		gestorBX.recuperarPersonasAll();
		gestorBX.exportar(fichero);

		//	/**Utilizo el método creado para conectar a la BD*/
//		//gestorBX.conectar();
//		
//		gestorBX.borrarPersona("29204528");
//		System.out.println("*********** Consultando el borrado de Erika");
	
//		gestorBX.exportar("c://Android");
//		//C://
//		//c://
//		//gestorBX.cerrarSesion();
//		
//		
		
	
	}//Fin main

}//Fin class
