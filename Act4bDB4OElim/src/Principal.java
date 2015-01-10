import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;


/**
*
* @author erika_000
*/


public class Principal {
		
	
	public static void main(String[] args) {
		 /*
        //es necesario realizar los 2 imports anteriores
        ObjectContainer baseDatos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "pruebaP.db4o");
        */
		/**  
		 *Hay que definir (especificar las clases)que queremos que se borren
		 * en cascada, antes de abrir la base de datos
		 *si estas clases tuvieran otros objetos dentro tendriamos que definir un metodo
		 *para esto, con el metodo inicializar por ejemplo 
		 **/
		 //PARA BORRAR EN CASCADA
        //Se indica la nueva configuración de conexión, con borrado en cascada
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(Vehiculo.class).cascadeOnDelete(true);
		//inicializo 
		//Se abre la conexión a la base de objetos pruebaP.db4o
		ObjectContainer baseDatos = Db4oEmbedded.openFile(config, "pruebaP.db4o");
		System.out.println("**** Abierta conexión ****");
		 
		try{	
			
			
			// creamos unas personas
			Persona pers1 = new Persona("Pablo", 21, "25698887M");
			Persona pers2 = new Persona("Mar", 25, "21233365G");
			Persona pers3 = new Persona("Pedro", 25, "22263669M");
			Persona pers4 = new Persona("Carla", 30, "12257711Z");
			Persona pers5 = new Persona("Andrea", 36, "29204528W");
			
			//Creamos unos vehículos
			Vehiculo v1 = new Vehiculo("2564CTN", "Opel","Corsa",pers2,2000);
			Vehiculo v2 = new Vehiculo("4602HBJ", "Seat","Ibiza",pers1,2014);
			Vehiculo v3 = new Vehiculo("6987XFD","BMW","Compack",pers3,2013);
	        v3.setModelo("320cl");
	        Vehiculo v4 = new Vehiculo("6949XFD","BMW","Mini",pers5,2001);
	        Vehiculo v5 = new Vehiculo("6987WDE","Ford","Kuga",pers4,2015);
	        
	       
	              
	        	// Inserto la personas
	        	 System.out.println("Almacenando personas...");
	             GestionDB4O.insertarPersona(baseDatos,pers1);
	             GestionDB4O.insertarPersona(baseDatos,pers2);
	             GestionDB4O.insertarPersona(baseDatos,pers3);
	             GestionDB4O.insertarPersona(baseDatos,pers4);
	             GestionDB4O.insertarPersona(baseDatos,pers5);
	             System.out.println("**************************************");
	             System.out.println("Las personas son:");
	             GestionDB4O.consultaSODApersonas(baseDatos);
	             System.out.println("**************************************");
	             
	             
	             //Almacena todos los vehículos
	             System.out.println("Almacenando vehículos");
	             GestionDB4O.insertarVehiculo(baseDatos,v1);
	             GestionDB4O.insertarVehiculo(baseDatos,v2);
	             GestionDB4O.insertarVehiculo(baseDatos,v3);
	             GestionDB4O.insertarVehiculo(baseDatos,v4);
	             GestionDB4O.insertarVehiculo(baseDatos,v5);
	             
	             //Ejemplo de consulta por matricula
	             System.out.println("**************************************");
	             System.out.println("Los vehículos por matricula son:");
	             GestionDB4O.consultaSODAVehicOrdenadosMatricula(baseDatos);
	            
	             //EJEMPLOS DE ACTUALIZACION
	             System.out.println("**************************************");
	             System.out.println("Actualizar modelo  de un vehiculo por su matricula");
	             GestionDB4O.actualizarModeloVehiculo(baseDatos, "4602HBJ", "Astra");
	             System.out.println("Actualizando matricula  4602HBJ ...");
	             
	             System.out.println("Comprobamos recuperando todos los vehiculos:");
	             GestionDB4O.consultaSODAVehicOrdenadosMatricula(baseDatos);
	             System.out.println("**************************************");
	             
	             
	           
	            //Ejemplo actualizar vehículo Persona
	             //Dejo comentado el uso de este método, pues no funciona y slata la excepcion 
	             //noi dejando actuar al de borrado que va después
//	             
//	             System.out.println("Actualizar vehículo de una persona de esta sesion");
//	             System.out.println("actualizo persona con dni 12257711Z...");
//	             GestionDB4O.actualizarVehiculoPersona(baseDatos, "12257711Z", v2);
//	             System.out.println("compruebo...");
//	             GestionDB4O.consultaSODAVehiculos(baseDatos);
//	             System.out.println("**************************************");
//	            
	             
	      
	             
	              //EJEMPLOS DE BORRADO
	             System.out.println("Borrado de un vehículo por su matricula");
	             GestionDB4O.borrarVehiculoPorMatricula(baseDatos, "6987XFD");
	             System.out.println("Borrando el vehículo con matricula 6987XFD");
	             GestionDB4O.consultaSODAVehiculos(baseDatos);
	             System.out.println("**************************************");
	             
	             System.out.println("Borrado de una persona por su dni");
	             GestionDB4O.borrarVehiculoPorMatricula(baseDatos, "12257711Z");
	             System.out.println("Borrando  persona con dni 12257711Z");
	             GestionDB4O.consultaSODApersonas(baseDatos);
	             System.out.println("**************************************");
	             
	             System.out.println("Comprobamos vehículos y personas");
	             GestionDB4O.consultaSODAVehiculos(baseDatos);
	             GestionDB4O.consultaSODApersonas(baseDatos);
	             
	             
	             //ACTIVIDAD 4C
	             System.out.println("**************************************");
	             System.out.println("Recupero por modelo");
	             GestionDB4O.recuperarVehiculoPorModeloQbE(baseDatos,"Astra");
	             
	             //ACTIVIDAD 4C
	             System.out.println("**************************************");
	             System.out.println("Recupero por matrícula");
	             GestionDB4O.recuperarVehiculoPorMatriculaQbE(baseDatos,"4602HBJ");
	             
	             
	             //ACTIVIDAD 4C Native
	             System.out.println("**************************************");
	             System.out.println("Recupero por AÑO DE MATRICULACIÓN");
	             GestionDB4O.recuperarVehiculosPorAnyoNATIVE(baseDatos, 2002, 2015);
	             
	             //ACTIVIDAD 4C Native
	             System.out.println("**************************************");
	             System.out.println("Recupero por reparacion");
	             //GestionDB4O.recuperarVehiculosAvanzados(baseDatos);
	             
		         }catch(ExceptionInInitializerError e){
	        	 System.out.println("Error:"+e.getStackTrace());
	        	 e.getMessage();
		
	         }finally {
	          //GestionDB4O.close();
	          //Cerramos conexión a la BD
	          System.out.println("Cerrando conexión...");
			  GestionDB4O.cerrarConexion(baseDatos);
			  System.out.println("Cerrada ok, hasta pronto!! :)");
	         }
		}
	 }


	 
