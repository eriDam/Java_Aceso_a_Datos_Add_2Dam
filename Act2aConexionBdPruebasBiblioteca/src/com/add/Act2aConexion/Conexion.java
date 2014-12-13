package com.add.Act2aConexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/** @author Eri
 *  Actividad 2a Conexión mySql pruebas biblioteca*/

public class Conexion {
	
	public static void main(String[] args) {
		Connection conexion;
	/**La url debe seguir el protocolo jdb (debe empezar x jdbc:mysql://laruta*/
	String url = "jdbc:mysql://localhost/biblioteca";
	String user = "root";
	String pw = "ecreaweb2";
	
	/** El método class for Name informa a la clase cual es el método que 
		 * hay que cargar a partir de la sentencia. 
		 * Driver es el que se encarga de establecer la conexión*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error " + e.getMessage());;
			System.out.println("No se encuentra el driver...");
		}
		/**
		 * Establecer la conexión con el driverManager
		 * */
		
			try{
				conexion = DriverManager.getConnection(url, user, pw);
				System.out.println("Conexión realizada ok, utilizando DriverManager");
				Statement instruccion = (Statement) conexion.createStatement();
				ResultSet conjuntoResultados = instruccion.executeQuery("SELECT * FROM fichalibro");
				while (conjuntoResultados.next())
					System.out.println("\nISBN: "+ conjuntoResultados.getObject("ISBN") + "\nTítulo: "
							+ conjuntoResultados.getObject("Título") + "Autor" + conjuntoResultados.getObject("Autor") + 
							"\nDescripción: "+ conjuntoResultados.getObject("Descripción") + "Editorial" + conjuntoResultados.getObject("Editorial") +
							"Coleccion: " + conjuntoResultados.getObject("Colección")
							+ "Precio: " + conjuntoResultados.getObject("Precio") + "\nFecha publicación: " + conjuntoResultados.getObject("Fecha publicación"));
				conexion.close();		
			}catch (SQLException e) {
				System.err.println("Error " + e.getMessage());
			}
			/** obtenemos el driver del controlador desde el DriverManager*/
			try {
				Driver driver = DriverManager.getDriver(url);
				Properties properties = new Properties();
				properties.setProperty("user", user);
				properties.setProperty("password", pw);
				conexion = driver.connect(url, properties);
				System.out.println("\nConexión realizada ok usando Driver");
				conexion.close();

			} catch (SQLException ex) {
				System.err.println("Error " + ex.getMessage());
			}

		}

	}

