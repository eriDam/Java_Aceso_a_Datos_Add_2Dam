package com.add.Act2bConexionconsulta;
//package com.add.Act2bConexionconsulta;
//
///** @author Eri
// *  Actividad 2b Conexión mySql
// *  Crear la Bd Dam en consola, use db Dam, el resto en Java 
// *  Pasos realizados:
// * 					1º Instanciar variables.
// * 					2º Cargar el controlador con class.ForName.
// * 					3º Establecer la conexión con Driver.
// * 					4º Realizar peticiones a la Bd.
// * 					5º Capturar excepciones.
// * 					6º Utilizar el código SQL STATE e informar al usuario.
// * 					7º Utilizar Logs
// * 					8º Seguir el orden de cierre de cada objeto que hemos utilizado.
// *  */
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.swing.JOptionPane;
//
//
//public class Conexion {
//	
//	public static void main(String[] args) throws SQLException {
//		
//	/**1º Instanciamos las variables, iniciamos en null, url será donde debe conectarse
//	 * La url debe seguir el protocolo jdb (debe empezar x jdbc:mysql://laruta*/
//		Connection conexion=null;
//		Statement instruccion=null;
//		ResultSet conjuntoResultados=null;
//		String url = "jdbc:mysql://localhost/Dam";
//		String user = "root";
//		String pw = "ecreaweb2";
//	
//	/** 2º CARGAR EL CONTROLADOR
//	 * El método class for Name informa a la clase cual es el método que 
//	 * hay que cargar a partir de la sentencia. 
//	 * Driver es una clase especial  el que se encarga de establecer la conexión con el SGBD*/
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.err.println("Error " + e.getMessage());;
//			System.out.println("No se encuentra el driver...");
//		}//Fin catch
//		
//		/**
//		 * 3º Establecer la conexión con el driverManager	
//		 * Instanciamos la interfaz  Connection a partir del metodo getConnection de la claser DriverManager o
//		 * a partir del objeto Driver.*/
//			try{
//				conexion = DriverManager.getConnection(url, user, pw);
//				System.out.println("Conexión realizada ok, utilizando DriverManager");
//				
//				/**4º Statement es Para ejecutar sentencias SQL y enviarlas a las BBDD 
//			 * Antes de poder ejecutar una sentencia SQL, es necesario obtener un objeto de tipo 
//				 Statement. Una vez creado dicho objeto, podrá ser utilizado para ejecutar cualquier operación contra la base de datos.
//				 * Para realizar peticiones a la Bd, la variable instruccion es un statement que recuperamos desde conexion. 
//			     */
//				  instruccion = (Statement) conexion.createStatement();
//				
//				/** Un mismo statement me servirá para crear la tabla y hacer peticiones
//				 * En primer lugar Borro la tabla siempre antes, para poedr crearla, If not exists no me funciona*/
//				instruccion.executeUpdate("DROP TABLE entregas_asignaturas");
//				System.out.println("La tabla ha sido borrada de la Bd correctamente");
//				
//				/**Creo la tabla*/
//				instruccion.executeUpdate("CREATE TABLE entregas_asignaturas (ISBN INT NOT NULL, PRIMARY KEY(ISBN), Asignatura VARCHAR(20)NOT NULL, Nombre VARCHAR(20)NOT NULL, Inicio DATE NOT NULL,  Fin DATE NOT NULL, Entregada BOOLEAN DEFAULT FALSE)");
//				System.out.println("La tabla entregas_asignaturas se ha creado correctamente en la base de datos Dam");
//				
//				/**Agregamos el contenido a nuestra Bd Dam mediante los inserts correspondientes*/
//				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (1, 'AAD', '1a', '2014-09-12', '2014-10-12', TRUE)");
//				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (2, 'AAD', '1d', '2014-09-22', '2014-10-12', FALSE)");
//				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (3, 'DDI', '2a', '2014-10-06', '2014-11-02', FALSE)");
//				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (4, 'PMM', '2a', '2014-10-06', '2014-11-02', FALSE)");
//				
//				
//				
//				/**  instruccion.executeQuery nos devuelve conjunto de resultados de tipo ResulSet (mientras se escribe java nos 
//				 * indica lo que retorna)
//				 * executeQuery es para cuando queremos retornar datos, (hacer lo que es la consulta) 
//				 * pero para cuando queremos  insertar,modificar, eliminar se usa executeUpdate 
//				 * */
//				 conjuntoResultados = instruccion.executeQuery("SELECT * FROM entregas_asignaturas");
//				
//				/**para ver el contenido del resultSet, hay que realizar un condicional ya que imprimiendo no ser veria nada
//				 * resultSet tiene un metodo (next) que nos permitirá ir recorriendolo, mientras haya un next impriremos los objetos que le pasamos*/
//				System.out.println("\nInserts realizados ok - Mostrando datos de la BD DAM");
//				while (conjuntoResultados.next()) {
//					
//					System.out.println("\nISBN: "+ conjuntoResultados.getObject("ISBN") + " | Asignatura: "
//							+ conjuntoResultados.getObject("Asignatura") + " | Nombre: " + conjuntoResultados.getObject("Nombre") + 
//							" | Inicio: "+ conjuntoResultados.getObject("Inicio") + " | Fin" + conjuntoResultados.getObject("Fin") +
//							" | Entregada: " + conjuntoResultados.getObject("Entregada"));
//							
//				}//fin while
//				/**LLamo al metodo consultaActividades() */
//				consultaActividades();
//				
//				}catch (SQLException e) {
//				/** 5º CAPTURAR EXCEPCIONES
//				 * Cuando se trabaja con conexiones JDBC, al ejecutar las sentencias SQL se pueden producir errores.
//				 *Estos errores están definidos en un estandar mediante lo q se conoce como SQL STATE, esta variable identifica
//				 *el estado de una sentencia SQL después de su ejecución. Cuando JDBC detecta que el estado de esta variable, se 
//				 *corresponde con 1 error, se lanza la excepcion de tipo SQLException y a parte del mensaje que contiene, 
//				 *incorpora 1 valor de tipo SQL STATE. Try catch nos permite capturar la excepción y podremos:
//				 *
//				 * 6º UTILIZAR EL CODIGO SQL STATE
//				 * para decidir que tenemos que hacer. En este caso se comprueba si salta este error y en ese caso informamos
//				 *al usuario (no se debe especificar cada error, solo los que consideremos).
//				 *
//				 *Estos errores se pueden consultar en http://dev.mysql.com/doc/connectors/en/connector-j-reference-error-sqlstates.html
//				 **/
//				if(e.getSQLState().equals("28000")) {
//					System.out.println("Se ha producido un Error de autentificación");
//				    JOptionPane.showMessageDialog(null, "Se ha producido un Error de autentificación");
//				}
//				if(e.getSQLState().equals("28000")) {
//					System.out.println("Se ha producido un Error de autentificación");
//				    JOptionPane.showMessageDialog(null, "Se ha producido un Error de autentificación");
//				}else { 
//					/**Propagamos la excepción*/
//					throw e;
//				}//fin else
//				}//Fin catch
//					
//			/**Las operaciones de cierre se realizan dentro del finally(para que se ejecute si o si)
//			 * 
//			 *8º Hay que  SEGUIR ORDEN DE CIERRE:
//			 * Si queremos cerrar un un objeto de tipo statement y 
//			 * un objeto de tipo conecction, 1 se cierra el statemente y luego la conexión, ya  que 
//			 * estariamos intentando cerrar una conexion qeu n o existe si la cerramos primero.
//			 * 
//			 * Cada objeto que se utiliza debe ser cerrado
//			 * Se nececesita capturar las excepciones dentro de cada operacion de cierre, ya que colocarlas 
//			 * seguidas no garantiza su correcto cierre, por eso cada una estará dentro de su bloque try catch*/
//			 finally {
//				try{
//					/**comprobamos que no es null y que no esta cerrada, si cumple ambas condiciones ejecuta código de cierre*/
//					if(conjuntoResultados!=null && !conjuntoResultados.isClosed())
//						conjuntoResultados.close();
//				}catch(SQLException e){
//					/**7º Podemos utilizar Logs para que almacenenen todos estos sucesos, de forma que si se produce
//					 * un error lo almacenaremos en el Log, es una forma de REGISTRAR FALLOS EN EL CIERRE*/
//					Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
//				}
//				try{
//					if(instruccion!=null && !instruccion.isClosed())
//						instruccion.close();
//				}catch(SQLException e){
//					
//				}
//				try{
//					if(conexion!=null && !conexion.isClosed())
//						conexion.close();
//				}catch(SQLException e){
//					System.err.println("Error");
//				}
//			}//fin finally
//		}//Fin main
//		/**Método para consultar Actividades */
//	 
//	
//
//		private static  void consultaActividades() {
//			int numero=0;
//			String url = "jdbc:mysql://localhost/Dam";
//			String user = "root";
//			String pw = "ecreaweb2";
//			Connection conex=null;
//			Statement st=null;
//			ResultSet Rst=null;
//			/**
//			 * 3º Establecer la conexión con el driverManager	
//			 * Instanciamos la interfaz  Connection a partir del metodo getConnection de la claser DriverManager o
//			 * a partir del objeto Driver.*/
//				try{
//					conex = DriverManager.getConnection(url, user, pw);
//					System.out.println("Conexión realizada ok, utilizando DriverManager en consultaActividades");
//					numero= Integer.parseInt(JOptionPane.showInputDialog (null, "Ingrese un número del 1 al 4 ", "showInputDialog",JOptionPane.INFORMATION_MESSAGE));
//				
//				}catch(SQLException e) {
//					System.err.println("Error");
//					e.printStackTrace();
//				}
//				try {  
//				/**Statement Para ejecutar sentencias SQL y enviarlas a las BBDD */
//			 
//			
//			switch (numero) {
//			case 1:
//				  st.executeQuery("SELECT ISBN, Asignatura, Nombre, Inicio, Fin, Entregada FROM entregas_asignaturas WHERE 1");
//				  System.out.println("\nMostrando Consulta de actividades de la BD DAM");
//					while (Rst.next()) {
//						
//						System.out.println("\nISBN: "+ Rst.getObject("ISBN") + " | Asignatura: "
//								+ Rst.getObject("Asignatura") + " | Nombre: " + Rst.getObject("Nombre") + 
//								" | Inicio: "+ Rst.getObject("Inicio") + " | Fin" + Rst.getObject("Fin") +
//								" | Entregada: " + Rst.getObject("Entregada"));
//								
//					}//fin while
//				break;
//			case 2:
//				 st.executeQuery("SELECT ISBN, Asignatura, Nombre, Inicio, Fin, Entregada FROM entregas_asignaturas WHERE 2");
//				break;
//			case 3:
//				 st.executeQuery("SELECT ISBN, Asignatura, Nombre, Inicio, Fin, Entregada FROM entregas_asignaturas WHERE 3");
//				break;
//			case 4:
//				 st.executeQuery("SELECT ISBN, Asignatura, Nombre, Inicio, Fin, Entregada FROM entregas_asignaturas WHERE 4");
//				break;
//
//			default:
//				
//				break;
//			}
//			/**para ver el contenido del resultSet st, hay que realizar un condicional ya que imprimiendo no ser veria nada
//			 * resultSet tiene un metodo (next) que nos permitirá ir recorriendolo, mientras haya un next impriremos los objetos que le pasamos*/
//			
//			}catch(SQLException e) {
//				System.err.println("Error");
//				e.printStackTrace();
//			}finally {
//				try{
//					/**comprobamos que no es null y que no esta cerrada, si cumple ambas condiciones ejecuta código de cierre*/
//					if(Rst!=null && !Rst.isClosed())
//						Rst.close();
//				}catch(SQLException e){
//					/**7º Podemos utilizar Logs para que almacenenen todos estos sucesos, de forma que si se produce
//					 * un error lo almacenaremos en el Log, es una forma de REGISTRAR FALLOS EN EL CIERRE*/
//					Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
//				}
//				try{
//					if(st!=null && !st.isClosed())
//						st.close();
//				}catch(SQLException e){
//					System.err.println("Error");
//				}
//				
//			}//fin finally
//		
//		}//Fin metodo
//		}//Fin class
//
