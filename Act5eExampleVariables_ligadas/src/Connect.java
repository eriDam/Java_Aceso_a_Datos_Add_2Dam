import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQResultSequence;

public class Connect {

	private static String introducirEdad() {
		// Creamos objeto para la entrada de datos por consola, almacenando en un buffer
		// lector, usamos envolturas 1 trimestre
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Creamos objeto para recoger la opcion del usuario
		String textUs = null;
		
		//Pedimos datos:
		System.out.println("Introducir edad:");
		try {
			textUs = br.readLine();
		} catch (IOException e) {
			System.out.println("Excepción al leer el dato introducido:");
			System.err.println(e);
		}
		return textUs;
	}

	public static void main(String[] args) {
		XQConnection conn = null;

		try {

			// Pedimos edad al usuario
			String edad = introducirEdad();
			if ("".equals(edad)) {
				System.out.println("No se ha introducido ninguna edad.");
				System.exit(1);
			}

			// Abrimos la sesión
			XQDataSource xqs = (XQDataSource) Class.forName(
					"net.xqj.basex.BaseXXQDataSource").newInstance();

			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "admin");
			conn = xqs.getConnection();

			System.out.println("Connexión correcta con el SGBD BaseX");
			// Creamos XQExpression:
			XQExpression xqe = conn.createExpression();
			
			/**Para evitar acceso a datos confidenciales en la base de datos, XQuery 
			 * nos permite declarar VARIABLE EXTERNAS y de alguna forma ligarlas
			 * a la sentencia XQuery con los valores que corresponda.  Para no introducir
			 * directamente el texto que teclea el usuario
			 * 
			 * Ver info XQDinamicContext, esta Super interfaz nos ofrece una
			 * serie de métodos, por ej bindAtomicValue que permite asignar 
			 * el contenido de una variable string a una variable externa,
			 * el primer parámetro el nombre variable que pondremos en el código XQuery,
			 *  segundo parametro seria el valor que introducimos, XQBASETYPE_INT asegurará 
			 *  que el tipo es entero y si no no habremos introducido una edad*/
			try {
				xqe.bindAtomicValue(new QName("edad_buena"), edad,
						conn.createAtomicType(XQItemType.XQBASETYPE_INT));
			} catch (XQException e) {
				throw new Exception("No se ha introducido una edad correcta.");
			}
			
			/**Preparamos la instrucción para BaseX donde pediremos los usuarios
			 *  que tengan una determinada edad, definimos la variable externa delante del for
			 *   con declare variable $loquesea external;
			 *   
			 *   Antes teníamos cad= "for $c in doc('personas')/personas/persona where $c/edad/text()='"+edad+'"
			 *   Si el usuario probase a introducir 35' or '1'='1 con este codigo muestra cualquier resultado, aqui es donde se cambia
			 *   por variables ligadas externas para limitar el acceso*/
			String cad;
			cad = "declare variable $edad_buena external; "+"for $c in doc('personas')/personas/persona where $c/edad/text()= $edad_buena return $c/nombre";

			// Ejecutamos
			System.out.println("Ejecutamos consulta: " + cad);
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
			} catch (XQException xe) {
				xe.printStackTrace();
			}
		}
	}
}