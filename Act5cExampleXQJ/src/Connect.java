import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
/**
 * Esta Aplicación representa un ejemplo de utilizacion de XQJ
 * API :  http://xqj.net/javadoc/  
 * 
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**Instanciamos el objeto de XQconnection*/
		XQConnection conn = null;
				
		try {
			// Abrimos la sesión
			/**XQDataSource es una fábrica  de XQConnection objetos.
			 * XQDataSource xqs = new XQDataSource BaseXXQDataSource();
			 * Pero no lo instanciaremos así, la forma de proceder para poder conectarnos contra 
			 * otros Sistemas gestores, utilizamos un string que nos haga referencia al 
			 * deriver que vamos a utilizar y despues generar (casteando) un objeto XQDataSource 
			 * haremos un  Class.forName de ese driver que queremos utilizar*/
			XQDataSource xqs = (XQDataSource) Class.forName("net.xqj.basex.BaseXXQDataSource").newInstance();
			
			/**Realizamos un for para recorrer las propiedades del vector y que nos las muestre */
			/**Si no sabemos las propiedades del sistema gestor de bd determinado, para ver como 
			 * obtenerlas, con getSupportedPropertyNames, las obtenemos, es un vector de Strings que nos
			 * devuelve las distintas propiedades que tiene*/
			System.out.println("Obtengo las propiedades");
			for (int i=0;i<xqs.getSupportedPropertyNames().length;i++)
				System.out.println(xqs.getSupportedPropertyNames()[i]);
			
			/**Si quieremos indicar las propiedades, maquina, puerto...*/
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "admin");
			/**Cuando ya tenemos un objeto de tipo XQDataSource, podemos establecer la conexión 
			 * con una serie de parámetros o sin ellos con getConnection, hay 3 métodos en este 
			 * caso utilizo el método sin ellos*/
			conn = xqs.getConnection();

			System.out.println("Connexión correcta con el SGBD BaseX");
			
			/** Creamos XQExpression:
			 * */
			XQExpression xqe = conn.createExpression();
			// Preparamos la instrucción para BaseX
			String cad;
			cad = "for $c in doc('personas')/personas/persona return $c/nombre";

			// Ejecutamos
			System.out.println("Ejecutamos consulta: " + cad);
			/** Las sentencias de consultas pueden devolver un conjunto de resultados, 
			 * en caso de quere obtener resultados,*/
			XQResultSequence xqrs = xqe.executeQuery(cad);
			// Mostramos los resultados, uno a uno, convertidos a String
			System.out.println("\nResultados:");
			while (xqrs.next())
				System.out.println(xqrs.getItemAsString(null));
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // Cerramos la sesión
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