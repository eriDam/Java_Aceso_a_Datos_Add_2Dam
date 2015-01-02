package com.eridam.add;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;

public class Principal {
	public static void main(String[] args) throws Db4oException {
		/**
		 * Es necesario realizar los 2 imports anteriores. Tenemos que llamar al
		 * metodo openFile pasandole el nombre del fichero donde queremos
		 * guardar la bd, en los recursos del proyecto, se le asigna a 1 objeto
		 * de tipo ObjectContainer De esta forma se abre la conexión y permanece
		 * abiertohasta q se cierre con el metodo cerrar conexion creado
		 * llamando a close, lo realizaremos dentro d un finally para
		 * asegurarnos que se cierra
		 * */
		//
		ObjectContainer baseDatos = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), "personas.db4o");
		// creamos una persona
//		Persona pers1 = new Persona("Pablo", 21, "25698887M");
//		Persona pers2 = new Persona("Mar", 25, "21233365G");
//		Persona pers3 = new Persona("Pedro", 25, "22263669M");
		try {
			// Inserto la persona
//			GestionPersistencia.insertarPersona(baseDatos, pers1);
//			GestionPersistencia.insertarPersona(baseDatos, pers2);
//			GestionPersistencia.insertarPersona(baseDatos, pers3);
//			
			//Imprimo las personas
//			System.out.println("Imprimimos las personas");
//			pers1.print();
//			pers2.print();
//			pers3.print();
			System.out.println("**************************************");
			
			//Consulta para recuperar todas las personas igual que anterior pero con método
			System.out.println("Recupero todas las personas");
			GestionPersistencia.consultaSODATodasPersonas(baseDatos);
			System.out.println("**************************************");
			
			//Recupero persona por nombre
			System.out.println("Recupero persona por nombre");
			GestionPersistencia.recuperarPersonaPorNombre(baseDatos,"Mar");
			System.out.println("**************************************");
			
			//Recupero persona por Dni con consulta Query by Example QBE
			System.out.println("Recupero persona por dni con QBE Query by Example");
			GestionPersistencia.consultarQBEPersonasDni(baseDatos, "25698887M");
			System.out.println("**************************************");
			
			//Recupero persona por Dni con consulta SODA
			System.out.println("Recupero persona por dni con SODA");
			GestionPersistencia.recuperarPersonaPorDni(baseDatos, "22263669M");
			System.out.println("**************************************");
			
			//Recupero persona por Inicial con consulta SODA
			System.out.println("Recupero persona por la inicial");
			GestionPersistencia.recuperarPersonaPorInicial(baseDatos, "P");
			System.out.println("**************************************");
			
			//Recupero personas por edad entre SODA
			System.out.println("Recupero persona por la edad entre 21 y 26 años");
			//Como la edad mas alta que tengo es 25 debo poner 26 para que me lo incluya
			GestionPersistencia.consultaSODAPersonaEdadEntre(baseDatos, 21, 26);
			System.out.println("**************************************");
			//Borrar
			//GestionPersistencia.borrar(pers1);
		
		} finally {
			//Cerramos conexión a la BD
			System.out.println("Cerrando conexión...");
			GestionPersistencia.cerrarConexion(baseDatos);
			 
		}
	}

}
