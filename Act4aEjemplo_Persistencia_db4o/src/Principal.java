import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;


public class Principal {

	public static void main(String[] args) {
		/**
		 * Tenemos que llamar al metodo openFile pasandole el nombre del fichero donde queremos guardar
		 * la bd, en los recursos del proyecto, se le asigna a 1 objeto de tipo  ObjectContainer
		 * De esta forma se abre la conexión y permanece abiertohasta q se cierre con el metodo close
		 * lo realizaremos dentro d un finally para asegurarnos que se cierra
		 * */
		ObjectContainer bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
		
		Persona p = new Persona("Pepe", 25, "50667847T");
		
		try{
			bbdd.store(p);
			bbdd.commit();//Para forzar el flujo de datos del contenedor
			System.out.println("Se ha guardado correctamente");
			
			Query q = bbdd.query();		
    		q.constrain(Persona.class);
			q.descend("nombre").constrain("Pepe");
			Constraint c1 = q.descend("edad").constrain(20).greater();
			Constraint c2 = q.descend("edad").constrain(30).smaller();
			c1.or(c2);			
			
			ObjectSet<Persona> res = q.execute();
			
			for(Persona p2: res){
				p2.print();				
			}
			
		}finally{
			bbdd.close();
		}

	}

}
