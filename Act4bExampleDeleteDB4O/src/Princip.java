import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

/* 
 * NOTA: Para que funcione esta clase:
 * 1º-Hay que borrar el archivo .db4o
 * 2º-Crear la bd y objetos, quitando de comentarios y comentar linea 81 bbdd.delete(p4);
 * 3º-Para probar el método delete, se coloca en comentarios la creacion de objetos en la bd
 * y se descomenta la linea 81 para poder usar bbdd.delete(p4);
 * 
 * */
public class Princip {
	
	static ObjectContainer bbdd;
	
	/*Hay que definir (especificar las clases)que queremos que se borren en cascada, antes de abrir la base de datos
	 *si estas clases tuvieran otros objetos dentro tendriamos que definir un metodo
	 *para esto, con el metodo inicializar por ejemplo*/
	
	public void inicializar(){
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().objectClass(Persona.class).cascadeOnDelete(true);	
		bbdd = Db4oEmbedded.openFile(conf,"prueba.db4o");
	}
	
	/*No definimos un borrado normal si no que definimos un borrado de un objeto*/
	public void delete(Object o){
		try{
			bbdd.delete(o);
		}catch(Db4oIOException ex){
			//Si se produce un error realizará un rollback para dejar todo como estaba
			bbdd.rollback();
		}
		//Se realiza un commit almacenando los datos.
		bbdd.commit();
		System.out.println("Borrado ok");
	}
	public static void main(String[] args) {
		Princip princ = new Princip();
		//ObjectContainer bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"prueba.db4o");
		
		//Creo los objetos personas
		Persona p = new Persona("Pepe", 25, "50667847T");
		Persona p2 = new Persona("Pablo", 27, "50679999P");
		Persona p3 = new Persona("Ana", 25, "6789700097N");
		try{
			//inicializo 
			princ.inicializar();
			//Paso las personas/objetos a la base de datos
//			bbdd.store(p);
//			bbdd.store(p2);
//			bbdd.store(p3);
			//bbdd.commit();//Para forzar el flujo de datos del contenedor
			
			
			
			System.out.println("Se ha guardado correctamente");
			
			Query q = bbdd.query();		
			q.constrain(Persona.class);
			//Filtramos la consulta por un determinado nombre me recupera 1 objeto q coincide con el filtro
			//q.descend("nombre").constrain("Pepe");
			//Constraint c1 = q.descend("edad").constrain(20).greater();
			//Constraint c2 = q.descend("edad").constrain(30).smaller();
			//c1.or(c2);			
			
			ObjectSet<Persona> res = q.execute();
		
			for(Persona p4: res){
				//Imprimo los registros
				//System.out.println("Los registros son:");
				//p4.print();
				
				//Hacemos la llamada al método delete y le pasamos el objeto recuperado q es p4
				bbdd.delete(p4);
				System.out.println("Borrado:"+p4);
			}
			
		}finally {
			
		bbdd.close();
		}
	}
}
