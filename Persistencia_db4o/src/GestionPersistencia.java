import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class GestionPersistencia {

	static ObjectContainer bbdd;

	// Este método crea o abre la base de datos con la configuración de cascada
	// para la eliminación de objetos estructurados.
	public void inicializar() {
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().objectClass(Persona.class).cascadeOnDelete(true);
		bbdd = Db4oEmbedded.openFile(conf, "prueba.db4o");
	}

	// El método insertarPersona guarda objetos en la base de datos.
	public void insertarPersona(Persona p) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		try {
			// Intento almacenar el objeto
			bbdd.store(p);
			bbdd.commit();
			// Muestro una confirmación al usuario de que todo ha ido bien
			System.out.println("Se ha guardado correctamente");

		} finally {
			// Cerramos la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarPersonaPorNombre devuelve una lista de personas cuyo
	// nombre coincide con el parámetro.
	public List<Persona> recuperarPersonaPorNombre(String nombre) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Persona> res = null;
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por el nombre que recibimos como parámetro
			q.descend("nombre").constrain(nombre);
			// Le asigno a la lista el resultado de la consulta
			res = q.execute();
			// Para cada objeto de la lista hago print (lo muestra por pantalla)
			for (Persona p : res) {
				p.print();
			}
			// Devuelve el objeto
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarPersonaPorInicial devuelve una lista de personas cuyo
	// nombre empieza con la cadena que coincide con el parámetro.
	public List<Persona> recuperarPersonaPorInicial(String inicio) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Persona> res = null;
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por el nombre que recibimos como parámetro, para ello
			// filtro por el nombre que SÍ cumple la condición startsWith
			q.descend("nombre").constrain(inicio).startsWith(true);
			// Le asigno a la lista el resultado de la consulta
			res = q.execute();
			// Para cada objeto de la lista hago print (lo muestra por pantalla)
			for (Persona p : res) {
				p.print();
			}
			// Devuelve el objeto
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarPersonaPorDNI devuelve una persona cuyo dni coincide
	// con el parámetro.
	public Persona recuperarPersonaPorDNI(String dni) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Persona> res = null;
		Persona p = null;
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por el dni que recibimos como parámetro
			q.descend("dni").constrain(dni);
			// Le asigno a la lista el resultado de la consulta
			res = q.execute();
			// Para obtener un objeto persona no sirve castear. Lo que hago es
			// crear un iterator
			Iterator<Persona> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a p un objeto
			// persona
			while (iterator.hasNext()) {
				// Si la BBDD es coherente solo habrá una persona con ese DNI.
				// EEn caso de haber varias personas con el mismo DNI solo
				// quedará constancia de la última
				p = (Persona) iterator.next();
			}
			// Imprimimos la persona recuperada por pantalla
			p.print();
			// Devuelve el objeto
			return p;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarPersonaPorEdad devuelve una lista de personas cuya
	// edad está entre los parámetros menor y mayor.
	public List<Persona> recuperarPersonaPorEdad(int menor, int mayor) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Persona> res = null;
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por edad indicando valores mayores al número menor
			// introducido
			q.descend("edad").constrain(menor).greater();
			// Filtro por edad indicando valores menores al número mayor
			// introducido
			q.descend("edad").constrain(mayor).smaller();
			// Le asigno a la lista el resultado de la consulta
			res = q.execute();
			// Para cada objeto de la lista hago print (lo muestra por pantalla)
			for (Persona p : res) {
				p.print();
			}
			// Devuelve el objeto
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método insertarVehiculo guarda objetos en la base de datos.
	public void insertarVehiculo(Vehiculo v) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		try {
			// Intento almacenar el objeto
			bbdd.store(v);
			bbdd.commit();
			// Muestro una confirmación al usuario de que todo ha ido bien
			System.out.println("Se ha guardado el vehículo correctamente");

		} finally {
			// Cerramos la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarVehiculoPorMatricula devuelve un vehículo cuya
	// matrícula coincide con el parámetro.
	public Vehiculo recuperarVehiculoPorMatricula(String matricula) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Vehiculo> res = null;
		// Creo un objeto vehículo
		Vehiculo v = null;
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Vehiculo.class);
			// Filtro por la matrícula que recibimos como parámetro
			q.descend("matricula").constrain(matricula);
			// Le asigno a la lista el resultado de la consulta
			res = q.execute();
			// Para obtener un objeto vehículo no sirve castear. Lo que hago es
			// crear un iterator
			Iterator<Vehiculo> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a v un objeto
			// vehículo
			while (iterator.hasNext()) {
				// Si la BBDD es coherente solo habrá un vehículo con esa
				// matrícula.
				// En caso de haber varios vehículos con la misma matrícula solo
				// quedará constancia del último
				v = (Vehiculo) iterator.next();
			}
			// Imprimimos el vehículo recuperado por pantalla
			v.print();
			// Devuelve el objeto
			return v;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método actualizarModeloVehiculo modifica un objeto de la base de
	// datos.
	public void actualizarModeloVehiculo(String matricula, String modelo) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();

		try {
			// Creo un objeto vacio
			Vehiculo v = null;
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Vehiculo.class);
			// Filtro por la matrícula que recibimos como parámetro
			q.descend("matricula").constrain(matricula);
			// Le asigno a un ObjectSet el resultado de la consulta
			ObjectSet<Vehiculo> res = q.execute();
			// Para obtener un objeto vehículo no sirve castear. Lo que hago es
			// crear un iterator
			Iterator<Vehiculo> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a v un objeto
			// vehículo
			while (iterator.hasNext()) {
				// Si la BBDD es coherente solo habrá un vehículo con esa
				// matrícula.
				// En caso de haber varios vehículos con la misma matrícula solo
				// quedará constancia del último
				v = (Vehiculo) iterator.next();
				// Una vez tenemos el objeto Vehiculo usamos el método setModelo
				// para asignar el modelo recibido como parámetro
				v.setModelo(modelo);
				// Guardar cambios es igual que insertar un nuevo objeto
				bbdd.store(v);
				bbdd.commit();
			}

		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método actualizarVehiculoPersona modifica un objeto vehículo en base a
	// un DNI.
	public void actualizarVehiculoPersona(String dni, Vehiculo v) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();

		try {
			// Creo un objeto Persona vacio
			Persona p = null;
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por el dni que recibimos como parámetro
			q.descend("dni").constrain(dni);
			// Le asigno a un ObjectSet el resultado de la consulta
			ObjectSet<Persona> res = q.execute();
			// Para obtener un objeto Persona no sirve castear. Lo que hago es
			// crear un iterator
			Iterator<Persona> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a p un objeto
			// persona
			while (iterator.hasNext()) {
				// Si la BBDD es coherente solo habrá una persona con ese DNI.
				// En caso de haber varias personas con el mismo DNI solo
				// quedará constancia del último
				p = (Persona) iterator.next();
				// Una vez tenemos el objeto Persona usamos el método
				// setVehiculo para asignar el vehículo recibido como parámetro
				p.setVehiculo(v);
				// Guardar cambios es igual que insertar un nuevo objeto
				bbdd.store(p);
				bbdd.commit();
			}

		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método borrarVehiculoPorMatricula elimina un objeto vehículo de la
	// Base de datos.
	public void borrarVehiculoPorMatricula(String matricula) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Vehiculo.class);
			// Filtro por la matrícula que recibimos como parámetro
			q.descend("matricula").constrain(matricula);
			// Le asigno a un ObjectSet el resultado de la consulta
			ObjectSet<Vehiculo> res = q.execute();
			// Para cada vehiculo del conjunto de objetos muestro por pantalla
			// el objeto que se va a borrar y lo borro
			for (Vehiculo v : res) {
				System.out.println("Se va a borrar el siguiente vehículo:");
				v.print();
				// Delete es un método definido en esta clase. Lo que hace es
				// intentar eliminar el registro y en caso de error deshacer los
				// cambios
				bbdd.delete(v);
				System.out.println("Vehículo borrado correctamente.");
			}

		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método borrarVehiculoPorMatricula elimina un objeto vehículo de la
	// Base de datos.
	public void borrarPersonaPorDni(String dni) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		try {
			// Creo la consulta
			Query q = bbdd.query();
			// Filtro por tipo de objeto
			q.constrain(Persona.class);
			// Filtro por el dni que recibimos como parámetro
			q.descend("dni").constrain(dni);
			// Le asigno a un ObjectSet el resultado de la consulta
			ObjectSet<Persona> res = q.execute();
			// Para cada persona del conjunto de objetos muestro por pantalla el
			// objeto que se va a borrar y lo borro
			for (Persona p : res) {
				System.out.println("Se va a borrar la siguiente persona:");
				p.print();
				// Delete es un método definido en esta clase. Lo que hace es
				// intentar eliminar el registro y en caso de error deshacer los
				// cambios
				bbdd.delete(p);
				System.out.println("Persona borrada correctamente.");
			}

		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// Este método intenta eliminar un objeto de la Base de datos, en caso de
	// error deja todo como estaba.
	public void delete(Object o) {
		try {
			bbdd.delete(o);
		} catch (Db4oIOException ex) {
			bbdd.rollback();
		}
		bbdd.commit();
	}

	// El método recuperarVehiculoPorModeloQbe devuelve una lista de vehículos
	// cuyo
	// modelo coincide con el parámetro.
	public List<Vehiculo> recuperarVehiculoPorModeloQbe(String modelo) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Vehiculo> res = null;
		// Creo un objeto vehículo
		Vehiculo v = null;
		try {
			// Le asigno a la lista el resultado de la consulta queryByExample
			res = bbdd
					.queryByExample(new Vehiculo(null, null, modelo, 0, null));
			// Creo un iterator para mostrar todos los resultados
			Iterator<Vehiculo> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a v un objeto
			// vehículo
			while (iterator.hasNext()) {
				v = (Vehiculo) iterator.next();
			}
			// Imprimimos el vehículo recuperado por pantalla
			v.print();
			// Devuelve el objeto
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarVehiculoPorMatriculaQbe devuelve un vehículo cuya
	// matrícula coincide con el parámetro.
	public Vehiculo recuperarVehiculoPorMatriculaQbe(String matricula) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Vehiculo> res = null;
		// Creo un objeto vehículo
		Vehiculo v = null;
		try {
			// Le asigno a la lista el resultado de la consulta queryByExample
			res = bbdd.queryByExample(new Vehiculo(matricula, null, null, 0,
					null));
			// Para obtener un objeto vehículo no sirve castear. Lo que hago es
			// crear un iterator
			Iterator<Vehiculo> iterator = res.iterator();
			// Mientras el iterator tenga elementos le asigna a v un objeto
			// vehículo
			while (iterator.hasNext()) {
				// Si la BBDD es coherente solo habrá un vehículo con esa
				// matrícula.
				// En caso de haber varios vehículos con la misma matrícula solo
				// quedará constancia del último
				v = (Vehiculo) iterator.next();
			}
			// Imprimimos el vehículo recuperado por pantalla
			v.print();
			// Devuelve el objeto
			return v;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarVehiculoPorAnyo devuelve una lista de vehículos cuya
	// fecha de matriculación está entre los parámetros menor y mayor.
	public List<Vehiculo> recuperarVehiculosPorAnyo(final int menor,
			final int mayor) {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Vehiculo> res = null;
		try {
			// Creamos la consulta en formato nativo
			res = bbdd.query(new Predicate<Vehiculo>() {
				// Ponemos las condiciones que se deben cumplir
				public boolean match(Vehiculo v) {
					// Uso & porque deben cumplirse las dos
					return v.getAnyo() > menor & v.getAnyo() < mayor;
				}

			});
			// Para cada objeto de la lista hago print (lo muestra por pantalla)
			for (Vehiculo v : res) {
				v.print();
			}
			// Devuelve el objeto lista
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

	// El método recuperarVehiculoPorAnyo devuelve una lista de vehículos cuya
	// fecha de matriculación está entre los parámetros menor y mayor.
	public List<Vehiculo> recuperarVehiculosAvanzado() {
		// Creo el fichero base de datos o lo abro según sea necesario
		inicializar();
		// Creo un objeto de tipo lista
		List<Vehiculo> res = null;
		try {
			// Creamos la consulta en formato nativo
			res = bbdd.query(new Predicate<Vehiculo>() {
				// Ponemos las condiciones que se deben cumplir
				public boolean match(Vehiculo v) {
					// Uso || para que se cumpla una u otra condición
					// Explicación de las condiciones
					// v.getAnyo() > 2011 --> Leemos el año del vehiculo y
					// comparamos para ver si es mayor a 2011
					// 'F' == v.getMatricula().charAt(4) --> Leemos la matrícula
					// del vehículo, nos quedamos con el carater de la posición
					// 4 y lo comparamos con el carácter 'F'
					// 'G' == v.getMatricula().charAt(4) --> Leemos la matrícula
					// del vehículo, nos quedamos con el carater de la posición
					// 4 y lo comparamos con el carácter 'G'
					// Arrays.toString(v.getReparaciones()).contains("Cambio de aceite")
					// --> Convertimos el array de reparaciones en una cadena y
					// buscamos que contenga la cadena extacta
					// "Cambio de aceite"
					return v.getAnyo() > 2011
							|| 'F' == v.getMatricula().charAt(4)
							|| 'G' == v.getMatricula().charAt(4)
							|| Arrays.toString(v.getReparaciones()).contains(
									"Cambio de aceite");
				}

			});
			// Para cada objeto de la lista hago print (lo muestra por pantalla)
			for (Vehiculo v : res) {
				v.print();
			}
			// Devuelve el objeto lista
			return res;
		} finally {
			// Cierro la conexión a la base de datos
			bbdd.close();
		}
	}

}
