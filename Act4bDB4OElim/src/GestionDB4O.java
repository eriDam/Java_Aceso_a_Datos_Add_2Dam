import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
 


public class GestionDB4O {
	//static ObjectContainer bbdd;
	
	
//	public void inicializar(){
//		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
//		conf.common().objectClass(Persona.class).cascadeOnDelete(true);	
//		bbdd = Db4oEmbedded.openFile(conf,"prueba.db4o");
//	}
	
	/**Método para cerrar 
	 * 
	 * @param baseDatos la base de datos desde la que se va a operar
     */
	public static void cerrarConexion(ObjectContainer baseDatos){
        try{
            baseDatos.close();
        }catch(Exception e){System.out.println("error al cerrar la conexion");}
    }
	/**Método para eliminar el objeto pasado por parámetro
	 *  @param o el objeto que se va a recuperar
     */
	
//	public void delete(Object o){
//		try{
//			bbdd.delete(o);
//		}catch(Db4oIOException ex){
//			bbdd.rollback();
//		}
//		bbdd.commit();
//	}
		
	/**Método para insertar persona*/
	public static void insertarPersona(ObjectContainer baseDatos, Persona persona) {
		try {
			baseDatos.store(persona);
			/**
			 * Se inserta igualmente aunque no se realice el commit, pero para
			 * forzar el flujo de datos del contenedor se realiza
			 */
			baseDatos.commit();

			// Informa de que se ha almacenado ok o saltará la excepcion
			System.out.println("Se ha almacenado correctamente la persona: "+persona);
		} catch (Exception e) {
			System.out.println("Se ha producido un error en la insercion");
		}
	}
	
	/**
	 * Método para insertar vehículo
	 * */
	
	public static void insertarVehiculo(ObjectContainer baseDatos , Vehiculo vehiculo) {
		try {
			baseDatos.store(vehiculo);
			/**
			 * Se inserta igualmente aunque no se realice el commit, pero para
			 * forzar el flujo de datos del contenedor se realiza
			 */
			baseDatos.commit();

			// Informa de que se ha almacenado ok o saltará la excepcion
			System.out.println("Se ha almacenado correctamente el vehiculo: "+vehiculo);
		} catch (Exception e) {
			System.out.println("Se ha producido un error en la insercion");
		}
	}
	
	/**
     * Imprime por pantalla el resultado de una consulta, da igual la consulta que sea
     *
     * @param resultado el objeto en el que se ha almacenado el resultado de la
     * consulta
     */
    public static void imprimirResultadoConsulta(ObjectSet resultado) {
        System.out.println("Recuperados " + resultado.size() + " Objetos");
        while (resultado.hasNext()) {
            System.out.println(resultado.next());
        }

    }
    
    /**
     * Permite realizar una consulta SODA el resultado seran todas las personas
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODApersonas(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Persona.class);//declara las restricciones
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    
    /**
     * Permite realizar una consulta SODA el resultado seran todas los vehículos
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAVehiculos(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Vehiculo.class);//declara las restricciones
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    /**
     * Consulta SODA recupera ordenados por matricula todos los
     * vehículos
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAVehicOrdenadosMatricula(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Vehiculo.class);
        query.descend("matricula").orderDescending();
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    
    /**
     * Permite actualizar el modelo de vehiculo, que
     * seleccionaremos por su nmatricula 
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param vmatricula matricula del vehiculo cuyo modelo queremos modificar
     * @param nuevoModelo el nuevo modelo que va a tener el vehiculo.
     */
    public static void actualizarModeloVehiculo(ObjectContainer baseDatos, String vmatricula, String nuevoModelo) {
        Query query = baseDatos.query();
        query.constrain(Vehiculo.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es matricula y la restricion es el parametro vmatricula
        Constraint constraint = query.descend("matricula").constrain(vmatricula);
        ObjectSet resultado = query.execute();
        Vehiculo v = (Vehiculo) resultado.get(0);
        v.setModelo(nuevoModelo);
        baseDatos.store(v);

    }
    /** ActualizarVehiculoPersona(String dni, Vehiculo v).
	*	Modifica el vehículo que tiene asociada la persona cuyo dni coincide con el
	*   pasado por parámetro. Para ello habrá que realizar las operaciones similares al
	*   caso anterior
	*   @param baseDatos
	*   @param dni el dni de la persona a la q pertenece el vehiculo
	*   @param vMarca el vehiculo que modificamos*/
    public static void actualizarVehiculoPersona(ObjectContainer baseDatos, String dni, Vehiculo vMarca){
    	Query query = baseDatos.query();
        query.constrain(Vehiculo.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es dni y la restricion es el parametro dni
        Constraint constraint = query.descend("dni").constrain(dni);
        ObjectSet resultado = query.execute();
        Vehiculo v = (Vehiculo) resultado.get(0);
        v.setMarca("BMW");
        baseDatos.store(v);
    }
    /**
     * Permite borrar un vehículo de la base de datos
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param matriculaV matricula del vehículo que se va a borrar
     */
    public static void borrarVehiculoPorMatricula(ObjectContainer baseDatos, String matriculaV) {
        Query query = baseDatos.query();
        query.constrain(Vehiculo.class);
        query.descend("matricula").constrain(matriculaV);
        ObjectSet resul = query.execute();
        while (resul.hasNext()) {
            Vehiculo vehiculo= (Vehiculo) resul.next();
            System.out.println("Eliminando: " + vehiculo);
            baseDatos.delete(vehiculo);
        }
    }
    
    /**
     * Permite borrar una persona de la base de datos
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el nombre del ponente que se va a borrar
     */
    public static void borrarPersPorDni(ObjectContainer baseDatos, String dni) {
        Query query = baseDatos.query();
        query.constrain(Persona.class);
        query.descend("dni").constrain(dni);
        ObjectSet resul = query.execute();
        while (resul.hasNext()) {
            Persona pers = (Persona) resul.next();
            System.out.println("Eliminando: " + pers);
            baseDatos.delete(pers);
        }
    }
}
