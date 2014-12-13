package com.add.Act2bConexionconsulta;

import java.sql.SQLException;

public class Principal {
	private static ConexionBd miConexion;
	public static void main(String[] args) throws SQLException {
		/** creo un objeto para utilizar la conexion*/
		miConexion  = new ConexionBd();
		/**Utilizo todos los métodos creados en conexión BD*/
		miConexion.crearTabla();
		//miConexion.insertDatos();
		miConexion.leerBd();
		miConexion.consultaActividades();
		miConexion.getMetaData();
		miConexion.cerrarConexion();
		
	}

}
