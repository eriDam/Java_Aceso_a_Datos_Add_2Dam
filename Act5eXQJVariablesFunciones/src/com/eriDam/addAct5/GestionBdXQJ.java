package com.eriDam.addAct5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQResultSequence;

/**
 * Actividad 5e XQJ con BaseX Variables ligadas
 * @author erika_000
 * 
 * Programa   que incluya un menú que muestra varias opciones al usuario, 
 * entre las que se incluye recuperar una persona por
 * DNI, insertar y borrar. Dependiendo de la opción seleccionada, el programa
 * mostrará por pantalla un mensaje con los datos de la persona recuperada (en
 * caso de que seleccione una consulta), o bien un mensaje de confirmación (en
 * caso que realice una inserción o un borrado).
 * 
 * * utilizar variables ligadas para realizar
 * instrucciones Xquery con datos que introduce el usuario. Para ello, se partirá
 * de la clase Persona con la que hemos trabajado y de la funcionalidad*desarrollada 
 * en la práctica 5c, y se ampliarán los siguientes conceptos:
 * 
 * */


public class GestionBdXQJ {
	/**Creo el objeto XQConnection*/
	XQConnection conn = null;
	/**@method constructor*/
	public GestionBdXQJ(){
	 }
	
	/**@method conectar  Permite conectar a la base de datos*/
	 public void conectar(){
		 /**1-Instanciamos el objeto de XQconnection*/
		 
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
				 * haremos un  Class.forName de ese driver que queremos utilizar*/
				XQDataSource xqs = (XQDataSource) Class.forName("net.xqj.basex.BaseXXQDataSource").newInstance();
				
				/**Realizamos un BUCLE for para recorrer las propiedades del vector y que nos las muestre */
				
				/**4- Si no sabemos las propiedades del sistema gestor de bd determinado, para ver como 
				 * OBTENERLAS, con getSupportedPropertyNames, las obtenemos, es un vector de Strings que nos
				 * devuelve las distintas propiedades que tiene el sistema gestor de DB*/
//				System.out.println("Obtengo las propiedades");
//				for (int i=0;i<xqs.getSupportedPropertyNames().length;i++)
//					System.out.println(xqs.getSupportedPropertyNames()[i]);
				
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
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Ha fallo la conexión con el SGBD BaseX");
			} 
	 }//Fin conectar
	 
//	 /**Cerrar conexión*/
//	 
//	 finally { /** Cerramos la sesión*/
//			try {
//				if (conn != null)
//					conn.close();
//				System.out.println("Conexión cerrada correctamente");
//			} catch (XQException xe) {
//				xe.printStackTrace();
//				System.out.println("Error!!  no se ha podido cerrar");
//			}
//		}
	 
	 //Método  Actividad 5e Añadir un elemento “fecha_nacimiento” y un atributo “identificador” del
	 //elemento “persona” que sea un entero
//	 public Persona addElementF(String fecha_nacimiento, int id){
//		
//		 
//		 return null;
//		 
//	 }
	 
	 
	 /**Metodo para recuperar todas las personas utilizado en  la opcion 8 del menu*/
	 public Persona recuperarPersonasAll(){
			conectar();
			try{
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
			 
			String cadConsultaAll = "doc ('personas')/personas/persona";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluación.
			 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
			 * */
			System.out.println("Ejecutamos consulta: " + cadConsultaAll);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
			XQResultSequence xqrs = xqe.executeQuery(cadConsultaAll);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos dni de las personas que hay.*/
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
			return null;
	}//Fin metodo recuperarPersonaPorDni
	/**
	 * @method Método public Persona recuperarPersonaPorDni(String dni). Devuelve la
		persona que coincide con el dni pasado por parámetro. Opción 1 del menú de la BD
	 * */
		public Persona recuperarPersonaPorDni(String dni){
			conectar();
			try{
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
			 
			String cadConsultaDni = "doc ('personas')/personas/persona[dni='"+dni+"']";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluación.
			 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
			 * */
			System.out.println("Ejecutamos consulta: " + cadConsultaDni);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
			XQResultSequence xqrs = xqe.executeQuery(cadConsultaDni);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos dni de las personas que hay.*/
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
			return null;
	}//Fin metodo recuperarPersonaPorDni
		
		/**
	 * @method public void insertarPersona(String dni, String nombre, int edad). Modificado para nuevos campos en Act5e
	 *         Añade la persona con los datos pasados por parámetro. Para ello
	 *         se realizará una instrucción Xquery utilizando “insert node” y
	 *         utilizando las etiquetas adecuadas para cada elemento. 
	 *         Opción 2 del menú de la bd
	 * */
		 public void insertarPersona(int id, String dni, String nombre, String fecha_nacimiento,int edad){
			 
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression();
				// Preparamos la instrucción de insercion para BaseX  
				//"insert node  <persona id="+id+"><dni>" + dni, no cosigo insertar el atributo me da error FALTABAN '"
				/**Creamos un string para realizar la Consulta*/
				String cadInsert = "insert node  <persona id='"+id+"'><dni>" + dni
					+ "</dni><nombre>" + nombre + "</nombre><fecha_nacimiento>"+fecha_nacimiento+"</fecha_nacimiento><edad>" + edad
					+ "</edad></persona> into doc('personas') /personas";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluación.
				 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
				 * */
				System.out.println("Ejecutamos consulta: " + cadInsert);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
				XQResultSequence xqrs = xqe.executeQuery(cadInsert);
				
				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
				 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
				 * los distintos dni de las personas que hay.*/
				System.out.println("\nLos resultados son: ");
				while (xqrs.next())
					System.out.println(xqrs.getItemAsString(null));
				System.out.println("Se ha insertado correctamente: "+cadInsert);
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
				return;
		}//Fin metodo insertar
		 
		       /**
			    * @method public void borrarPersona(String dni). Borra el nodo correspondiente
				*a la persona que coincided con el dni pasado por parámetro. Para ello se
				*realizará una instrucción Xquery utilizando “delete node”.
				* Opción 3 del menú de la bd
			    * */
		 public void borrarPersona(String dni){
			 /**Conectamos a la Bd*/
				conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression(); 
			 
				/**Creamos un string para realizar el borrado*/
				String cadBorrado = "delete node doc('personas')/personas/persona[dni='"+dni+"']";
			 
				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluación.
				 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
				 * */
				System.out.println("Ejecutamos consulta: " + cadBorrado);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
				XQResultSequence xqrs = xqe.executeQuery(cadBorrado);
				
				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
				 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos dni de las personas que hay.*/
			System.out.println("\nLos resultados son: ");
				while (xqrs.next())
					System.out.println(xqrs.getItemAsString(null));
				System.out.println("Se ha Borrado correctamente: "+cadBorrado);
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
				return;
		}//Fin metodo
		 
		 /**Metodo para introducir edad en el método buscarPorEdadVariables   ligadas
		  * 
		  * No está en el menú esta dentro de método citado
		  * 
		  * */
		 private static String introducirEdad() {
		 // Creamos objeto para la entrada de datos por consola, almacenando en un buffer
			// lector, usamos envolturas 1 trimestre
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			// Creamos objeto para recoger la opcion del usuario
			String textUs = null;
			
			//Pedimos datos:
			
			try {
				textUs = br.readLine();
			} catch (IOException e) {
				System.out.println("Excepción al leer el dato introducido:");
				System.err.println(e);
			}
			return textUs;
		}
		 
		 
		 
		 /**Método para buscar por  edad determinada con variables ligadas
		  * 
		  * Opción 6 del menú de la bd
		  * */
		 public void buscarPorEdadVariables(String eMinima, String eMax){
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulta: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				
					
//
//						// Pedimos edad al usuario
//					System.out.println("Introduce la edad a buscar: ");
//						String edad = introducirEdad();
//						if ("".equals(edad)) {
//							System.out.println("No se ha introducido ninguna edad.");
//							System.exit(1);
//					}		
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
				
				try{
					xqe.bindAtomicValue(new QName("edad_minima"), eMinima,
							conn.createAtomicType(XQItemType.XQBASETYPE_INTEGER));
					xqe.bindAtomicValue(new QName("edad_max"), eMax,
							conn.createAtomicType(XQItemType.XQBASETYPE_INTEGER));
//					xqe.bindAtomicValue(new QName("edad_buena"),edad,
//					conn.createAtomicType(XQItemType.XQBASETYPE_INT));
//					xqe.bindAtomicValue(new QName("edad_buena2"), ed2,
//							conn.createAtomicType(XQItemType.XQBASETYPE_INT));
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
				String cadEdV;
				cadEdV = "declare variable $edad_minima external;"+ 
						"declare variable $edad_max external;"+
						"for $c in doc('personas')/personas/persona"+
						" where $c/edad/text() >= $edad_minima and " +
						"$c/edad/text() <= $edad_max" +
						" return $c/nombre/text()";
				


				// Ejecutamos
				System.out.println("Ejecutamos la consulta de edad: " + cadEdV);
				XQResultSequence xqrs = xqe.executeQuery(cadEdV);
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
		 
		 
		 /**Método buscar por rango de edad PRUEBA sin variables ligadas
		  * Opción 4 del menú de la bd
		  * */
		 public void buscarPorEdad(int edad1, int edad2){
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression();
				// Preparamos la instrucción para BaseX, en este caso solicito que me devuelva todas las personas por edad
				 
				String cadConsultaEdad=  "doc('personas')/personas/persona[edad="+edad1+" or edad="+edad2+"]";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluación.
				 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
				 * */
				System.out.println("Ejecutamos consulta: " + cadConsultaEdad);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
				XQResultSequence xqrs = xqe.executeQuery(cadConsultaEdad);
				
				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
				 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
				 * los distintos dni de las personas que hay.*/
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
				return;
		 }
		 
		 
		 /**Método buscar por RANGO DE id para Act5e sin variables ligadas
		 *Opción 5 del menú de la bd*/
		 
		 public void buscarPorId(int id1, int id2){
			
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression();
				// Preparamos la instrucción para BaseX, en este caso solicito que me devuelva todas las personas por id
				 
				String cadConsultaId =  "doc('personas')/personas/persona[@id>="+id1+" and @id<="+id2+"]";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluación.
				 * La secuencia resultado está ligada a la XQconnection objeto en el que se evaluó la expresión.
				 * */
				System.out.println("Ejecutamos consulta: " + cadConsultaId);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecución*/
				XQResultSequence xqrs = xqe.executeQuery(cadConsultaId);
				
				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
				 * utilizando el método next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
				 * los distintos dni de las personas que hay.*/
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
				return;
		 }
		 /**Método para buscar por  edad determinada con variables ligadas
		  * 
		  * Opción 6 del menú de la bd
		  * */
		 public void buscarPorRangoIdVariables(String idMin, String idMax){
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecución inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecución se puede 
				 *  hacer usando el executeQuery() o executeCommand() método, que pasa en la expresión XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulta: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				 
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
				 *  que el tipo es entero y si no no habremos introducido una id*/
				
				try{
					xqe.bindAtomicValue(new QName("id_min"), idMin,
							conn.createAtomicType(XQItemType.XQBASETYPE_INTEGER));
					xqe.bindAtomicValue(new QName("id_max"), idMax,
							conn.createAtomicType(XQItemType.XQBASETYPE_INTEGER));
 
				} catch (XQException e) {
					throw new Exception("No se ha introducido una id correcto.");
				}
				
				/**Preparamos la instrucción para BaseX donde pediremos los usuarios
				 *  que tengan una determinada id, definimos la variable externa delante del for
				 *   con declare variable $loquesea external;
				 *   
				 *   Antes teníamos cad= "for $c in doc('personas')/personas/persona where $c/id/text()='"+id+'"
				 *   */
				String cadEdV;
				cadEdV = "declare variable $id_min external;"+ 
						"declare variable $id_max external;"+
						"for $c in  doc('personas')/personas/persona"+
						"[@id>= $id_min and @id<= $id_max]"+
						" return $c/nombre";
				
				 

				// Ejecutamos
				System.out.println("Ejecutamos la consulta de id: " + cadEdV);
				XQResultSequence xqrs = xqe.executeQuery(cadEdV);
				// Mostramos los resultados, uno a uno, convertidos a String
				System.out.println("\nLos resultados son: ");
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
		 
		 
		 /**                   
		  *       *************************  MENUS  ***************************
		  *       *************************************************************
		  *       
		  *       */
		 public void lanzarMenuPrincipal(){
			// Creamos la entrada de datos por consola, almacenando en un buffer
				// lector, usamos envolturas 1 trimestre
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				// Creamos variable para recoger la opcion del usuario
				int userOpcion;
					
				// Opciones: recuperar una persona por DNI, insertar y borrar.
				System.out.println("         ******                                   ******");
				System.out.println("         ******   ¡Bienvenido al menú Principal!   ******\n");
				System.out.println("            	 	 1- Gestionar BD");
				System.out.println("            	           2- Salir ");          	   
				System.out.println("                  ****** ****** ****** ******");
				System.out.println("                         ****** ****** ");
				System.out.println("                            ****** ");
				System.out.print("Escoge una opción y pulsa enter/intro: ");
				try {
					userOpcion = Integer.parseInt(in.readLine());
				
					/**
					 * Creo un swicth, para elección, siendo cada numero un case
					 * case
					 */
					
					switch (userOpcion) {
					case 1:
						System.out.println("Opción escogida: \"" + userOpcion
								+ "\" Gestionar Base de datos\n");
						lanzarMenuBD();
									
						System.out.println("************");
						break;
					
					case 2:
						System.out.println("Opción escogida: \"" + userOpcion
								+ "\" Salir. \n");
					
									
						System.out.println("  ****** Terminado ******");
						System.out.println("****** Vuelve pronto!! ******");
						break;
					
					default:
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Has de introducir un entero!  " + e);

				} catch (NumberFormatException nfe) {
					System.out.println("Error");
					nfe.printStackTrace();
				} catch (IOException ioe) {
					System.out.println("Error");
					ioe.printStackTrace();
				};
				
		 }
		 
		 public void lanzarMenuBD(){
			// Creamos la entrada de datos por consola, almacenando en un buffer
				// lector, usamos envolturas 1 trimestre
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				// Creamos variable para recoger la opcion del usuario
				int userOpcion;
				String userIntroDni;
				String userAddPersona;
				String userDeletePers;
				String dni;
				int userEdad1;
				int userEdad2;
				String edad1;
				String edad2;
				int id1;
				int id2;
				String id_min = null;
				String id_max=null;
				
				
				// Opciones: recuperar una persona por DNI, insertar y borrar.
				System.out.println("        ******                                   ******");
				System.out.println("      ********                                   ********");
				System.out.println("**************   ¡Bienvenido al menú de tu BD!   **************\n");
				
				System.out.println("              1- Buscar persona por DNI");
				System.out.println("              2- Insertar persona");
				System.out.println("              3- Borrar persona");
				System.out.println("              4- \"NOVEDAD\" Buscar por rango de edad");		
				System.out.println("              5- \"NOVEDAD\" Buscar por rango de Id");
				System.out.println("              6- \"NOVEDAD\" Buscar por edad variables");
				System.out.println("              7- \"NOVEDAD\" Buscar por rango de Id variables");
				System.out.println("              8- Ver todo");
				System.out.println("              9- Salir\n");
				
				System.out.println("               ****** ****** ****** ****** ******");
				System.out.println("                      ******  ****** ****** ");
				System.out.println("                           ****** ****** ");
				System.out.print("Escoge una opción y pulsa enter/intro: ");
				
				try {
					userOpcion = Integer.parseInt(in.readLine());
					
					//userIntroDni = in.readLine();
					/**
					 * Creo un swicth, para elección, siendo cada numero un case
					 * case
					 */
					//Creo un objeto de la clase GestionBdXQJ
					GestionBdXQJ gestorXQJ = new GestionBdXQJ();
					switch (userOpcion) {
					case 1:
						System.out.println("\nOpción escogida: \"" + userOpcion
								+ "\"Recuperar persona por DNI");
						System.out.print("Introduce el dni de la persona: ");
						
						gestorXQJ.recuperarPersonaPorDni(userIntroDni = in.readLine());

						System.out.println("ok");
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
					case 2:
						System.out.println("Opción escogida: \"" + userOpcion
								+ "\" Insertar persona");
						System.out.println("Insertando persona...Pulsa enter para completar");
						userAddPersona = in.readLine();
						gestorXQJ.insertarPersona(06,"21001001Q","Tara","17/04/2003",11);			
						System.out.println("************");
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
					case 3:
						System.out.println("Opción escogida :  \"" + userOpcion
								+ "\" Borrar persona");
						System.out.println("Inserta un dni para eliminar a esa persona: ");
						userDeletePers = in.readLine();
						gestorXQJ.borrarPersona(userDeletePers);
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
					case 4:
						System.out.println("\nOpción escogida: \"" + userOpcion
								+ "\"NOVEDAD\" Busca por edad");
						System.out.print("Introduce las edades para buscar las persona: ");
						
						gestorXQJ.buscarPorEdad(userEdad1 = Integer.parseInt(in.readLine()), userEdad2 = Integer.parseInt(in.readLine()));

						System.out.println("ok");
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;	
					case 5:
							System.out.println("\nOpción escogida: \"" + userOpcion
									+ "\"\"NOVEDAD\" Busca por rango de Id");
							System.out.print("Introduce los id para buscar las personas: ");
							
							gestorXQJ.buscarPorId(id1 = Integer.parseInt(in.readLine()), id2 = Integer.parseInt(in.readLine()));

							System.out.println("ok");
							System.out.println("\nVolviendo al menú principal...\n");
							lanzarMenuPrincipal();
							break;	
					case 6:
						System.out.println("\nOpción escogida: \"" + userOpcion
								+ "\"\"NOVEDAD\" Buscar por edad con variables ligadas");
						System.out.print("Introduce las edades para buscar las persona: ");
						edad1=in.readLine();
						edad2=in.readLine();
						gestorXQJ.buscarPorEdadVariables(edad1,edad2);

						System.out.println("ok");
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;		
					case 7:
						String userFindId;
						System.out.println("\nOpción escogida: \"" + userOpcion
								+ "\"\"NOVEDAD\" Busca por rango de Id con Variables ligadas");
						System.out.print("Introduce los id para buscar las personas: ");
						id_min=in.readLine();
						System.out.println("Introduce el otro id: ");
						id_max = in.readLine();
						gestorXQJ.buscarPorRangoIdVariables(id_min, id_max);
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
					case 8:
						String userFind;
						System.out.println("Mostrando todos los resultados... ");
						System.out.println("Pulsa enter para continuar!");
						userFind = in.readLine();
						gestorXQJ.recuperarPersonasAll();
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;	
					case 9:
						String userExit;
						System.out.println("Finalizado \"" + userOpcion
								+ "\"****");
						userExit = in.readLine();
						System.exit(userOpcion);
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
						
					
					 
					default:
						System.out.println("\nVolviendo al menú principal...\n");
						lanzarMenuPrincipal();
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println("Has de introducir un entero!  " + e);

				} catch (NumberFormatException nfe) {
					System.out.println("Error");
					nfe.printStackTrace();
				} catch (IOException ioe) {
					System.out.println("Error");
					ioe.printStackTrace();
				}

				// Podría usar también if
				// if (userOpcion==1)
				// {
				// System.out.println("Escribe el  ");
				//
				// }
				// if (userOpcion==2)
				// {
		 }

		 
		 
		
}
