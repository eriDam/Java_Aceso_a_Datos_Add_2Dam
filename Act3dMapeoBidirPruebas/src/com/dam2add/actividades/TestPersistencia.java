package com.dam2add.actividades;


 

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
 

public class TestPersistencia {
	/**
	 * Método principal para probar la persistencia mediante JPA 
	 */
	public static void main(String[] args) throws RollbackException, Exception {

 
 
		// Creamos un objeto para la persistencia mediante JPA
		GestionPersistencia gp = new GestionPersistencia();
			
		// Crear unas empresas
				System.out.println("**********  Crear empresas **********");	
			
				 
				Empresa empresa1 = new Empresa("Empresa 1");
				Empresa empresa2 = new Empresa("Empresa 2");
				
				// Y las insertamos		
				gp.insertarEmpresa(empresa1);
				gp.insertarEmpresa(empresa2);
				System.out.println("***Empresas creadas ok***");
		
				// Crear unas personas
				System.out.println("**********  Crear personas **********");
				System.out.println("\nCreando Personas...");
				Persona p1 =new Persona("19444000M", 23, "Anais" );
				Persona p2 =new Persona("29444000M", 29, "Mario" );
				Persona p3 =new Persona("28555550M", 33, "Pau" );
				System.out.println("***Personas creadas ok***");
		 
				// Les añado a la empresa (contratamos)
				p1.setNombreE(empresa1);
				p2.setNombreE(empresa1);
				p3.setNombreE(empresa2);
				System.out.println("***Personas añadidas a empresa ok***");
				
				// Y las insertamos en la empresa
				gp.insertarPersona(p1);
				gp.insertarPersona(p2);
				gp.insertarPersona(p3);
			
				// Crear unas nóminas
				System.out.println("Creando nóminas...");
				
				// Creamos unas nóminas
				Nomina nominap1 = new Nomina(120, p1);
				Nomina nominap2 = new Nomina(150, p2);
				Nomina nominap3 = new Nomina(300, p3);
				
				// Y las insertamos		
				gp.insertarNomina(nominap1);
				gp.insertarNomina(nominap2);
				gp.insertarNomina(nominap3);
				System.out.println("***Nominas añadidas a persona ok***");
				
				// Avisamos que vamos a actualizar las nóminas  
				System.out.println("Actualizando las nóminas de las personas....");
				
				// Actualizamos las nóminas
				p1.setNomina(nominap1);
				p2.setNomina(nominap2);
				p3.setNomina(nominap3);
				System.out.println("***Actualizadas ok***");
				
				// Actualizamos en la BD		
				gp.modificarPersona(p1);
				gp.modificarPersona(p2);
				gp.modificarPersona(p3);
				System.out.println("***Bd actualizada, personas modificadas***");
			
			
				// Indicamos que vamos a recuperar todos los registros
				System.out.println("Recuperando todos los registros....");
				
				// Creamos una lista de personas, y las recuperamos todas
				List<Persona> personas = gp.recuperarPersonas();
				
				// Controlamos la posibilidad de un error
				if(personas == null) {
					System.err.println("ERROR al recuperar todas las personas");
				}
				
				// Imprimimos todas las personas de la lista
				for(Persona p: personas) {
					p.print();
				}
		 
			
			
				// Indicamos que vamos a eliminar a mediante dni a  Pau  mediante su DNI.
				// Esto también eliminará la nómina que tiene asociada.
				System.out.println(" Eliminando a "+p3.getNombre()+" (y su nómina) mediante su DNI"+p3.getDni());

				// Eliminarlo
				gp.eliminarPersona("28555550M");
				//gp.eliminarPersona(p3.getDni());también se podría así
				
				// Indicamos que vamos a recuperar todos los registros
				System.out.println(" Recuperando todas las personas para comprobar si p3 se ha borrado");
				
				// Cremos una lista de personas, y las recuperamos todas
				personas = gp.recuperarPersonas();
				
				// Controlamos la posibilidad de un error
				if(personas == null) {
					System.err.println("ERROR al  recuperar todas las personas");
				}
				
				// Imprimimos todas las personas de la lista
				for(Persona p: personas) {
					p.print();
				}
			}
		}


