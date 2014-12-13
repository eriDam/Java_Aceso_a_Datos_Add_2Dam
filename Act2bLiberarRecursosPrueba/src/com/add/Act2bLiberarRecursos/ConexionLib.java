package com.add.Act2bLiberarRecursos;
/**
 * @author Eri
 * Act2b prueba
 * Pasos realizados:
 * 					1º Instanciar variables.
 * 					2º Cargar el controlador con class.ForName.
 * 					3º Establecer la conexión con Driver.
 * 					4º Realizar peticiones a la Bd.
 * 					5º Capturar excepciones.
 * 					6º Utilizar el código SQL STATE e informar al usuario.
 * 					7º Utilizar Logs
 * 					8º Seguir el orden de cierre de cada objeto que hemos utilizado.	
 * */
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionLib {

	public static void main(String[] args) throws SQLException {
		/**1º Instanciamos las variables, iniciamos en null, url será donde debe conectarse */
		Connection conexion=null;
		Statement instruccion=null;
		ResultSet conjuntoResultados=null;
		String url = "jdbc:mysql://localhost/Prueba";
		String user = "root";
		String pw = "ecreaweb2"; 
		
		/**2º CARGAR EL CONTROLADOR El método class.forName informa a la clase cual es el método que 
		 * hay que cargar a partir de la sentencia. 
		 * Driver es el que se encarga de establecer la conexión*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra el driver");
		} //Fin catch

		/**
		 * 3º Establecer la conexión con el driverManager	
		 * Instanciamos la interfaz  Connection a partir del metodo getConnection de la claser DriverManager o
		 * a partir del objeto Driver.*/
				try {
			conexion = DriverManager.getConnection(url, user, pw);
			System.out.println("Conexión realizada usando Drivermanager");
			
			/**4º Para realizar peticiones a la Bd, la variable instruccion es un statement que recuperamos desde conexion. 
		     */
			instruccion = (Statement) conexion.createStatement();
			

			/**  instruccion.executeQuery nos devuelve conjunto de resultados de tipo ResulSet (mientras se escribe java nos 
			 * indica lo que retorna)
			 * executeQuery es para cuando queremos retornar datos, (hacer lo que es la consulta) 
			 * pero para cuando queremos  insertar,modificar, eliminar se usa executeUpdate 
			 * */
			conjuntoResultados = instruccion.executeQuery("SELECT * FROM tabla_prueba");
			
			
			/**para ver el contenido del resultSet, hay que realizar un condicional ya que imprimiendo no ser veria nada
			 * resultSet tiene un metodo (next) que nos permitirá ir recorriendolo, mientras haya un next impriremos 
			 * los objetos que le pasamos*/
			System.out.println("\nInserts realizados ok - Mostrando datos de la BD Prueba");
			while (conjuntoResultados.next())
				System.out.println("nombre: "+ conjuntoResultados.getObject("Nombre") + " edad "
						+ conjuntoResultados.getObject("edad"));
			
		} catch (SQLException e) {
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
			if(e.getSQLState().equals("28000"))
				System.out.println("Error de autentificación");
			else 
				/**Propagamos la excepción*/
				throw e;
			e.printStackTrace();
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
				if(conjuntoResultados!=null && !conjuntoResultados.isClosed())
					conjuntoResultados.close();
			}catch(SQLException e){
				/**7º Podemos utilizar Logs para que almacenenen todos estos sucesos, de forma que si se produce
				 * un error lo almacenaremos en el Log, es una forma de REGISTRAR FALLOS EN EL CIERRE*/
				Logger.getLogger(ConexionLib.class.getName()).log(Level.SEVERE, null, e);
			}
			try{
				if(instruccion!=null && !instruccion.isClosed())
					instruccion.close();
			}catch(SQLException e){
				
			}
			try{
				if(conexion!=null && !conexion.isClosed())
					conexion.close();
			}catch(SQLException e){
				
			}
		}

		

	}

	}

