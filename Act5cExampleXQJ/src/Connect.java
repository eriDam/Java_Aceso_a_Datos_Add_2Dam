import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
/**
 * Esta Aplicación representa un ejemplo de utilizacion de XQJ
 *
 * Importamos las 3 librerias que nos proporcina el sigiente API.
 * API :  http://xqj.net/javadoc/  
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**1-Instanciamos el objeto de XQconnection*/
		XQConnection conn = null;
				
		try {
			// Abrimos la sesión en la base de datos
			/**2-XQDataSource es una fábrica  de objetos XQConnection.
			 * forma: XQDataSource xqs = new XQDataSource BaseXXQDataSource();
			 * 
			 * Pero no lo instanciaremos así, la forma de proceder PARA PODER CONECTARNOS contra 
			 * OTROS Sistemas gestores de Bd, utilizamos un string que nos haga referencia al 
			 * driver que vamos a utilizar.
			 * 
			 *Despues generar (casteando) un objeto XQDataSource 
			 * haremos un  Class.forName de ese driver que queremos utilizar para BaseX este*/
			XQDataSource xqs = (XQDataSource) Class.forName("net.xqj.basex.BaseXXQDataSource").newInstance();
			
			/**Realizamos un BUCLE for para recorrer las propiedades del vector y que nos las muestre */
			
			/**4- Si no sabemos las propiedades del sistema gestor de bd determinado, para ver como 
			 * OBTENERLAS, con getSupportedPropertyNames, las obtenemos, es un vector de Strings que nos
			 * devuelve las distintas propiedades que tiene el sistema gestor de DB*/
			System.out.println("Obtengo las propiedades");
			for (int i=0;i<xqs.getSupportedPropertyNames().length;i++)
				System.out.println(xqs.getSupportedPropertyNames()[i]);
			
			/**Si queremos indicar las propiedades, maquina, puerto...*/
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "admin");
			
			/**3- Cuando ya tenemos un objeto de tipo XQDataSource, podemos establecer la conexión 
			 * con una serie de parámetros o sin ellos con getConnection, hay 3 métodos en este 
			 * caso utilizo el método sin ellos*/
			conn = xqs.getConnection();

			System.out.println("Connexión correcta con el SGBD BaseX");
			
			/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
			 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
			 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
			 *  
			 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
			 *  de insercion, eliminacion y actualizacion.
			 *  
			 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
			XQExpression xqe = conn.createExpression();
			// Preparamos la instrucción para BaseX, en este caso solicito que me devuelva todas las personas por nombre
			String cad;
			cad = "for $c in doc('personas')/personas/persona return $c/nombre";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluación.
			 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
			 * */
			System.out.println("Ejecutamos consulta: " + cad);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
			XQResultSequence xqrs = xqe.executeQuery(cad);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos nombres de las personas que hay.*/
			System.out.println("\nLos resultados son: ");
			while (xqrs.next())
				System.out.println(xqrs.getItemAsString(null));
		} catch (Exception e) {
			e.printStackTrace();
		} finally { /** Cerramos la sesión*/
			try {
				if (conn != null)
					conn.close();
				System.out.println("Conexión cerrada correctamente");
			} catch (XQException xe) {
				xe.printStackTrace();
				System.out.println("Error!!  no se ha podido cerrar");
			}
		}
	}
}