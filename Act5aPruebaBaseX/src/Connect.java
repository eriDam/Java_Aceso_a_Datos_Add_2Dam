import java.io.IOException;

//import org.basex.query.value.item.Str;
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
 * http://xqj.net/javadoc/
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		//Creo un objeto de GestionBaseX
		GestionBaseX gestorBX = new GestionBaseX();
		gestorBX.conectar();
		gestorBX.recuperarPersonaPorNombre("Erika");
		gestorBX.recuperarPersonaPorDni("29204528");
		gestorBX.cerrarSesion();
	}

}
