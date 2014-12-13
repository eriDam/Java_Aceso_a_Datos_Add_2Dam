package com.add.Act2bConexionconsulta;
/** @author Eri
 *  Actividad 2b Conexión mySql
 *  Crear la Bd Dam en consola, use db Dam, el resto en Java 
 *  Pasos realizados:
 * 					1º Instanciar variables.
 * 					2º Cargar el controlador con class.ForName.
 * 					3º Establecer la conexión con Driver.
 * 					4º Realizar peticiones a la Bd.
 * 					5º Capturar excepciones.
 * 					6º Utilizar el código SQL STATE e informar al usuario.
 * 					7º Utilizar Logs
 * 					8º Seguir el orden de cierre de cada objeto que hemos utilizado.
 **/

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConexionBd {
	/**1º Instanciamos las variables, iniciamos en null, url será donde debe conectarse
	 * La url debe seguir el protocolo jdb (debe empezar x jdbc:mysql://laruta*/
	private Connection conexion =null;//maneja la conexión
	private Statement instruccion = null;//instrucción de consulta
	private ResultSet res  = null;// Maneja los resultados de SelectAll
	private ResultSet resSelectAll = null;// Maneja los resultados de SelectAll
	private ResultSet resConsultaAct = null;//Maneja los resultados de ConsultaAct
	private String url = "jdbc:mysql://localhost/Dam";
	private String user = "root";
	private String pw = "ecreaweb2";
	 
	
	/**
	 * Método/función para conectar a la bd
	 * @throws SQLException 
	 * */
	
	public ConexionBd() throws SQLException {
		/** 2º CARGAR EL CONTROLADOR
		 * El método class for Name informa a la clase cual es el método que 
		 * hay que cargar a partir de la sentencia. 
		 * Driver es una clase especial  el que se encarga de establecer la conexión con el SGBD*/
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Error " + e.getMessage());;
				System.out.println("No se encuentra el driver...");
			}//Fin catch
			
			/**3º Establecer la conexión con el driverManager	
			 * Instanciamos la interfaz  Connection a partir del metodo getConnection de la claser DriverManager o
			 * a partir del objeto Driver.*/
				try{
					conexion = DriverManager.getConnection(url, user, pw);
					System.out.println("Conexión realizada ok, utilizando DriverManager");	
				
				/** 5º CAPTURAR EXCEPCIONES */
				}catch (SQLException e) {
					/** Cuando se trabaja con conexiones JDBC, al ejecutar las sentencias SQL se pueden producir errores.
					 *  Estos errores están definidos en un estandar mediante lo q se conoce como SQL STATE, esta variable identifica
					 *	el estado de una sentencia SQL después de su ejecución. Cuando JDBC detecta que el estado de esta variable, se 
					 *	corresponde con 1 error, se lanza la excepcion de tipo SQLException y a parte del mensaje que contiene, 
					 *	incorpora 1 valor de tipo SQL STATE. Try catch nos permite capturar la excepción y podremos:
					 *
					 *  6º UTILIZAR EL CODIGO SQL STATE
					 *  para decidir que tenemos que hacer. En este caso se comprueba si salta este error y en ese caso informamos
					 *	al usuario (no se debe especificar cada error, solo los que consideremos).
					 *
					 *Estos errores se pueden consultar en http://dev.mysql.com/doc/connectors/en/connector-j-reference-error-sqlstates.html
					 **/
					if(e.getSQLState().equals("28000")) {
						System.out.println("Se ha producido un Error de autentificación");
					    JOptionPane.showMessageDialog(null, "Se ha producido un Error de autentificación");
					}
					if(e.getSQLState().equals("08S01")) {
						System.out.println("Se ha producido un Error en el host");
					    JOptionPane.showMessageDialog(null, "Se ha producido un Error en el host");
					}else { 
						/**Propagamos la excepción*/
						throw e;
					}//fin else
					}//Fin catch
				
	}//Fin metodo
	
	
	/**Metodo para crear tabla (La bd ha sido creada en la consola)  
	 **4º Statement es Para ejecutar sentencias SQL y enviarlas a las BBDD 
	 * Para realizar peticiones a la Bd, la variable instruccion es un statement que recuperamos desde conexion. 
	 * @return 
	 * @throws SQLException 
     */
	public  void crearTabla() throws SQLException {
		try {
			/** Antes de poder ejecutar una sentencia SQL, es necesario obtener un objeto de tipo 
			 *Statement. Una vez creado dicho objeto, podrá ser utilizado para ejecutar cualquier 
			 *operación contra la base de datos.
			 * Preparo el statement la instruccion */
			instruccion = (Statement) conexion.createStatement();
		/** Un mismo statement me servirá para crear la tabla y hacer peticiones
		 * En primer lugar Borro la tabla siempre antes, para poedr crearla, If not exists no me funciona*/
		instruccion.executeUpdate("DROP TABLE entregas_asignaturas");
		System.out.println("La tabla ha sido borrada de la Bd correctamente");
		
		/**Creo la tabla*/
		instruccion.executeUpdate("CREATE TABLE entregas_asignaturas (Id INT NOT NULL, PRIMARY KEY(Id), Asignatura VARCHAR(20)NOT NULL, Nombre VARCHAR(20)NOT NULL, Inicio DATE NOT NULL,  Fin DATE NOT NULL, Entregada BOOLEAN DEFAULT FALSE)");
		System.out.println("La tabla entregas_asignaturas se ha creado correctamente en la base de datos Dam");
		instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (1, 'AAD', '1a', '2014-09-12', '2014-10-12', TRUE)");
		instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (2, 'AAD', '1d', '2014-09-22', '2014-10-12', FALSE)");
		instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (3, 'DDI', '2a', '2014-10-06', '2014-11-02', FALSE)");
		instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (4, 'PMM', '2a', '2014-10-06', '2014-11-02', FALSE)");
		System.out.println("inserts ok");
		} catch (SQLException e) {
			if(e.getSQLState().equals("42S01")) {
				System.out.println("Se ha producido un ERROR la tabla ya existe, no se ha debido borrar");
			    JOptionPane.showMessageDialog(null, "Se ha producido un ERROR la tabla ya existe, no se ha debido borrar");
			}else { 
				/**Propagamos la excepción*/
				throw e;
			}//fin else
			}//Fin catch
		}
		
		/**
		 * Método o función para agregar datos no da error pero no se ejecuta, los introduzco dentro de crearTabla y va
		 **/
//	public  void insertDatos() throws SQLException {
//		 /**Agregamos el contenido a nuestra Bd Dam mediante los inserts correspondientes*/
//			try{ 
//				/** Preparo el statement la instruccion */
//			instruccion = (Statement) conexion.createStatement();
//			instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (1, 'AAD', '1a', '2014-09-12', '2014-10-12', TRUE)");
//			instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (2, 'AAD', '1d', '2014-09-22', '2014-10-12', FALSE)");
//			instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (3, 'DDI', '2a', '2014-10-06', '2014-11-02', FALSE)");
//			instruccion.executeUpdate("INSERT INTO entregas_asignaturas (Id,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (4, 'PMM', '2a', '2014-10-06', '2014-11-02', FALSE)");
//			
//			}catch(SQLException e) {
//				System.err.println("Error");
//				e.printStackTrace();
//				System.out.println("No se pueden realizar los inserts");
//			}finally {
//				if(instruccion!=null && !instruccion.isClosed())
//				instruccion.close();
//		}
//	 }
		
	 
 
	 public  void leerBd() throws SQLException { 	
	 /**  
	  * Método o función para leer de la BD
	  * instruccion.executeQuery nos devuelve conjunto de resultados de tipo ResulSet (mientras se escribe java nos 
	  * indica lo que retorna)
	  * executeQuery es para cuando queremos retornar datos, (hacer lo que es la consulta) 
	  * pero para cuando queremos  insertar,modificar, eliminar se usa executeUpdate 
	  * */
		 try {
			 res = instruccion.executeQuery("SELECT * FROM entregas_asignaturas");
		
			
			/**para ver el contenido del resultSet, hay que realizar un condicional ya que imprimiendo no ser veria nada
			 * resultSet tiene un metodo (next) que nos permitirá ir recorriendolo, mientras haya un next impriremos los
			 *  objetos que le pasamos. La interfaz java.sql.ResultSet ofrece varios métodos para recuperar los datos que 
			 *  se obtienen de realizar una consulta:
				getBoolean()
				getInt()
				getShort()
				getByte()
				getDate()
				getDouble()
				getfloat()
				estos métodos requieren el nombre de la columna (tipo String) o el índice de la columna (tipo int) como 
				argumento. La sintaxis para las dos variantes del método getString() es la siguiente:
							public String getString(int columnIndex) throws SQLException
							public String getString(String columnName) throws SQLException*/
			 
			System.out.println("\nInserts realizados ok - Mostrando datos de la BD DAM");
			while (res.next()) {
				
				System.out.println("\nId: "+ res.getInt("Id") + " | Asignatura: "
						+ res.getString("Asignatura") + " | Nombre: " + res.getString("Nombre") + 
						" | Inicio: "+ res.getDate("Inicio") + " | Fin" + res.getDate("Fin") +
						" | Entregada: " + res.getBoolean("Entregada"));
						
			}//fin while	
		 }catch (SQLException e) {
				/** 5º CAPTURAR EXCEPCIONES
				 * Cuando se trabaja con conexiones JDBC, al ejecutar las sentencias SQL se pueden producir errores.
				 *Estos errores están definidos en un estandar mediante lo q se conoce como SQL STATE, esta variable identifica
				 *el estado de una sentencia SQL después de su ejecución. Cuando JDBC detecta que el estado de esta variable, se 
				 *corresponde con 1 error, se lanza la excepcion de tipo SQLException y a parte del mensaje que contiene, 
				 *incorpora 1 valor de tipo SQL STATE. Try catch nos permite capturar la excepción y podremos:
				 *
				 * 6º UTILIZAR EL CODIGO SQL STATE
				 * para decidir que tenemos que hacer. En este caso se comprueba si salta este error y en ese caso informamos
				 *al usuario (no se debe especificar cada error, solo los que consideremos).
				 *
				 *Estos errores se pueden consultar en http://dev.mysql.com/doc/connectors/en/connector-j-reference-error-sqlstates.html
				 **/
				if(e.getSQLState().equals("28000")) {
					System.out.println("Se ha producido un Error de autentificación");
				    JOptionPane.showMessageDialog(null, "Se ha producido un Error de autentificación");
				    
			 
				}else { 
					/**Propagamos la excepción*/
					throw e;
					
				}//fin else
				}//Fin catch
		 /**Las operaciones de cierre se realizan dentro del finally(para que se ejecute si o si)
			 * 
			 *8º Hay que  SEGUIR ORDEN DE CIERRE:
			 * Si queremos cerrar un un objeto de tipo statement y 
			 * un objeto de tipo conecction, 1 se cierra el statemente y luego la conexión, ya  que 
			 * estariamos intentando cerrar una conexion qeu n o existe si la cerramos primero.
			 * 
			 * Cada objeto que se utiliza debe ser cerrado
			 * Se nececesita capturar las excepciones dentro de cada operacion de cierre, ya que colocarlas 
			 * seguidas no garantiza su correcto cierre, por eso cada una estará dentro de su bloque try catch*/
			 finally {
				try{
					/**comprobamos que no es null y que no esta cerrada, si cumple ambas condiciones ejecuta código de cierre*/
					if(res!=null && !res.isClosed())
						res.close();
				}catch(SQLException e){
					/**7º Podemos utilizar Logs para que almacenenen todos estos sucesos, de forma que si se produce
					 * un error lo almacenaremos en el Log, es una forma de REGISTRAR FALLOS EN EL CIERRE*/
					Logger.getLogger(ConexionBd.class.getName()).log(Level.SEVERE, null, e);
					System.err.println("SQLException: " + e.getMessage());
				}
				try{
					if(instruccion!=null && !instruccion.isClosed())
						instruccion.close();
				}catch(SQLException e){
					System.err.println("SQLException: " + e.getMessage());
				}  
			}//fin finally
	  }//Fin método
	 /**  Método para consultar actividades */
	 public void consultaActividades() {
		  try {
			// Preparamos la consulta
			  instruccion = conexion.createStatement();
			 
			  resConsultaAct= instruccion.executeQuery("SELECT Id, Asignatura, Nombre, Inicio, Fin, Entregada FROM entregas_asignaturas");

			  ResultSetMetaData rsMetaData = resConsultaAct.getMetaData();
			  /**Puede usarse o bien el nombre de la columna o el número de columna para referirse a esta, cualquiera
			   *de los dos ejemplos siguientes nos devolverá el valor almacenado en la columna.
			   *String s = rs.getString("title");
			   *String s = rs.getString(2);*/
			System.out.println("\nMostrar datos de la consulta de actividades");
			  // Con el bucle while recorro el resultado de la consulta
				while (resConsultaAct.next()) {
					// Creo unas variables para formatear la salida
					int id = resConsultaAct.getInt(rsMetaData
							.getColumnName(1));
					String asignatura = resConsultaAct.getString(rsMetaData
							.getColumnName(2));
					String nombre = resConsultaAct.getString(rsMetaData
							.getColumnName(3));
					DateFormat fecha = new SimpleDateFormat(
							"dd 'de' MMMM 'de' yyyy");
					Date inicio = (Date) resConsultaAct.getObject(rsMetaData
							.getColumnName(4));
					String dateInicio = fecha.format(inicio);
					Date fin = (Date) resConsultaAct.getObject(rsMetaData
							.getColumnName(5));
					String dateFin = fecha.format(fin);
					boolean entregado = false;

					if (resConsultaAct.getInt(rsMetaData.getColumnName(6)) == 1) {
						entregado = true;
					}
					;
					/** Imprimo el resultSet de consultaActividades */
					String salida = "Id: " + id + " | Asignatura: "
							+ asignatura + " | Nombre: " + nombre + " | Inicio: "
							+ dateInicio + " | Fin" + dateFin
							+ " | Entregado: " + entregado;
					System.out.println(salida);
				}
			 
		  }catch(SQLException e){
			  System.err.println("SQLException: " + e.getMessage());
		  }
	 }
	
	 
	 /** Prueba Método para consultar actividades */
	// public void consultaActividades() {
//			int numero=0;
//			
//		 	numero= Integer.parseInt(JOptionPane.showInputDialog (null, "Ingrese un número del 1 al 4 ", 
	 //"showInputDialog",JOptionPane.INFORMATION_MESSAGE));
//			try {  
//				if (numero==1) {
//				/**Statement Para ejecutar sentencias SQL y enviarlas a las BBDD */
//					resConsultaAct  = instruccion.executeQuery("SELECT * FROM entregas_asignaturas WHERE 1");
//				while (resConsultaAct.next()) {
//					System.out.println("\nId: "+ res.getObject("Id") + " | Asignatura: "
//							+ res.getObject("Asignatura") + " | Nombre: " + res.getObject("Nombre") + 
//							" | Inicio: "+ res.getObject("Inicio") + " | Fin" + res.getObject("Fin") +
//							" | Entregada: " + res.getObject("Entregada"));
//							
//				}//fin while	
//				}
//				if(numero==2) {
//					resConsultaAct  = instruccion.executeQuery("SELECT * FROM entregas_asignaturas WHERE 2");
//				}
//				if (numero==3) {
//					resConsultaAct  = instruccion.executeQuery("SELECT * FROM entregas_asignaturas WHERE 3");
//				}else {
//					resConsultaAct  = instruccion.executeQuery("SELECT * FROM entregas_asignaturas WHERE 4");
//				}
		 	
	 /**El siguiente método imprime la estructura del bloque de resultados:
	  * obtiene el número de columnas del bloque de resultados e imprime el nombre y el tipo de cada columna.
	  * En este caso, los nombres de columna son: Id,Asignatura,Nombre,Inicio,Fin,Entregada  . Observar que
	  * los tipos de columna son devueltos como números enteros. Por ejemplo, todas las columnas de
	  * tipo VARCHAR retornarán el entero 12, las del tipo DATE, 91, tipo BOOLEAN 7. Estos tipos son constantes 
	  * definidas en la interfaz java.sql.Types. */
		 		public void getMetaData() throws SQLException {
		 			System.out.println("\nMostrar datos de la consulta getMetaData");
		 		    String sqlString = ("SELECT * FROM entregas_asignaturas");
		 		    Statement statement = conexion.createStatement();
		 		    ResultSet rs = statement.executeQuery(sqlString); 
		 		    ResultSetMetaData metaData = rs.getMetaData();
		 		    int noColumns = metaData.getColumnCount();
		 		    for (int i=1; i<noColumns+1; i++) {
		 		        System.out.println(metaData.getColumnName(i) 
		 		                           + " " +
		 		                           metaData.getColumnType(i));
		 		    }

		 		}
				
//			}catch(SQLException e) {
//				
//			}
//			}
	 /** Metodo para Cierre de la conexión en el main*/
	 public  void cerrarConexion() throws SQLException { 
		try{
			/**comprobamos que no es null y que no esta cerrada, si cumple ambas condiciones ejecuta código de cierre*/
			if(conexion!=null && !conexion.isClosed())
				conexion.close();
			System.out.println("*** Conexion cerrada correctamente ***");
		}catch(SQLException e){
			/**7º Podemos utilizar Logs para que almacenenen todos estos sucesos, de forma que si se produce
			 * un error lo almacenaremos en el Log, es una forma de REGISTRAR FALLOS EN EL CIERRE*/
			Logger.getLogger(ConexionBd.class.getName()).log(Level.SEVERE, null, e);
		}	
	 }
}//Fin class
 
 
