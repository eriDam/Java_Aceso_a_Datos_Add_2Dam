package com.add.Act2aConexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/** @author Eri
 *  Actividad 2a Conexión mySql
 *  Crear la Bd Dam en consola, use de db Dam, el resto en Java 
 *  */

public class Conexion {
	
	public static void main(String[] args) {
		Connection conexion;
	/**La url debe seguir el protocolo jdb (debe empezar x jdbc:mysql://laruta*/
	String url = "jdbc:mysql://localhost/Dam";
	String user = "root";
	String pw = "ecreaweb2";
	
	/** 1º CARGAR EL CONTROLADOR
	 * El método class for Name informa a la clase cual es el método que 
	 * hay que cargar a partir de la sentencia. 
	 * Driver es una clase especial  el que se encarga de establecer la conexión con el SGBD*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error " + e.getMessage());;
			System.out.println("No se encuentra el driver...");
		}
		/**
		 * 2º Establecer la conexión con el driverManager
		 * Para asegurar la inter operabilidad, las app no deben referenciar a clases
		 * concretas de ningún controlador, si no a interfaces estándar, en este caso a la API jdbc, nuestra app
		 * no va a instanciar objetos jdb (con new...)si no que crearemos este tipo de objetos mediante la clase.
		 * 
		 * Instanciamos la interfaz  Connection a partir del metodo getConnection de la claser DriverManager o
		 * a partir del objeto Driver.
		 * El objeto conexion,  lo definimos arriba para utilizarlo aqui, 
		 * La clase DriverManager se encarga de intermediar entre la app y el controlador jdbc
		 * cada vez que el tipo driver se carga en memoria, mediante el classForName, se adapta en el DriverManager 
		 * donde estará el controlador activo hasta hasta que se finalice la petición. 
		 * 
		 * A partir de la url de conexion, la clase DriverManager localiza
		 * entre todos los drivers el controlador adecuado para hacer la conexión con esta url específica.
		 * getConnection, aparte de buscar el driver correspondiente obtiene la conexión del controlador seleccionado y la devuelve activa a la aplicación*/
		
			try{
				conexion = DriverManager.getConnection(url, user, pw);
				System.out.println("Conexión realizada ok, utilizando DriverManager");
				
				/**Para realizar peticiones a la Bd, la variable instruccion es un statement que recuperamos desde conexion. 
				 * executeQuery es para cuando queremos retornar datos, (hacer lo que es la consulta) 
				 * pero para cuando queremos  insertar,modificar, eliminar se usa executeUpdate  */
				Statement instruccion = (Statement) conexion.createStatement();
				
				/** Un mismo statement me servirá para crear la tabla y hacer peticiones
				 * En primer lugar Borro la tabla siempre antes, para poedr crearla, If not exists no me funciona*/
				instruccion.executeUpdate("DROP TABLE entregas_asignaturas");
				System.out.println("La tabla ha sido borrada de la Bd correctamente");
				
				/**Creo la tabla*/
				instruccion.executeUpdate("CREATE TABLE entregas_asignaturas (ISBN INT NOT NULL, PRIMARY KEY(ISBN), Asignatura VARCHAR(20)NOT NULL, Nombre VARCHAR(20)NOT NULL, Inicio DATE NOT NULL,  Fin DATE NOT NULL, Entregada BOOLEAN DEFAULT FALSE)");
				System.out.println("La tabla entregas_asignaturas se ha creado correctamente en la base de datos Dam");
				
				/**Agregamos el contenido a nuestra Bd Dam mediante los inserts correspondientes*/
				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (1, 'AAD', '1a', '2014-09-12', '2014-10-12', TRUE)");
				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (2, 'AAD', '1d', '2014-09-22', '2014-10-12', FALSE)");
				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (3, 'DDI', '2a', '2014-10-06', '2014-11-02', FALSE)");
				instruccion.executeUpdate("INSERT INTO entregas_asignaturas (ISBN,Asignatura,Nombre,Inicio,Fin,Entregada) VALUES (4, 'PMM', '2a', '2014-10-06', '2014-11-02', FALSE)");
				
				
				
				/**  instruccion.execu.. nos devuelve conjunto de resultados de tipo ResulSet (mientras se escribe java nos indica lo que retorna)*/
				ResultSet conjuntoResultados = instruccion.executeQuery("SELECT * FROM entregas_asignaturas");
				
				/**para ver el contenido del resultSet, hay que realizar un condicional ya que imprimiendo no ser veria nada
				 * resultSet tiene un metodo (next) que nos permitirá ir recorriendolo, mientras haya un next impriremos los objetos que le pasamos*/
				System.out.println("\nInserts realizados ok - Mostrando datos de la BD DAM");
				while (conjuntoResultados.next())
					
					System.out.println("\nISBN: "+ conjuntoResultados.getObject("ISBN") + " | Asignatura: "
							+ conjuntoResultados.getObject("Asignatura") + " | Nombre: " + conjuntoResultados.getObject("Nombre") + 
							" | Inicio: "+ conjuntoResultados.getObject("Inicio") + " | Fin" + conjuntoResultados.getObject("Fin") +
							" | Entregada: " + conjuntoResultados.getObject("Entregada"));
					
					
				
				
				
				/**Cerramos la conexion para que no se mantenga en memoria cuando no es necesario*/
			   conexion.close();		
			}catch (SQLException e) {
				System.err.println("Error " + e.getMessage());
			}
//			/** 
//			 * Otra forma de crear una conexion
//			 * obtenemos el driver del controlador desde el DriverManager*/
//			try {
//				/**DriverManager nos devuelve un objeto de tipo driver, gracias a la url que le pasamos como parámetro*/
//				Driver driver = DriverManager.getDriver(url);
//				Properties properties = new Properties();
//				properties.setProperty("user", user);
//				properties.setProperty("password", pw);
//				conexion = driver.connect(url, properties);
//				System.out.println("\nConexión realizada ok utilizando Driver");
//				conexion.close();
//
//			} catch (SQLException ex) {
//				System.err.println("Error " + ex.getMessage());
//			}
//
	}

	}

