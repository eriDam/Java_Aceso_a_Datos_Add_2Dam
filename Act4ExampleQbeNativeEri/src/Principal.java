import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

/**
 * @author erika_000
 * 
 * Alternativas para realizar consultas en nuestra Bd DB4O a parte de SODA
 * 
 * Query by example  QBE: 
 *				 Utiliza un parámetro donde indicamos el objeto que queremos 
 * 				 recuperar y los valores que queremos recuperar.
 * 
 * */
public class Principal {

	public static void main(String[] args) {
		
		ObjectContainer bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
				
		try{	
			/**
			 * Realizamos consulta de personas que tengan cualquier dni, 
			 * cualquier edad, cualquier nombre.
 			 * Cuando ya tenemos la consulta, recuperaremos un objeto set
 			 *  de tipo objetctset que es un tipo de objeto que implementa la interfaz list
 			 *  de java y asi podemos utilizar los métodos de next y has next
 			 * 	 * */
			ObjectSet<Persona> set = bbdd.queryByExample(new Persona(null, 0, null));
			/**Recorremos la lista e imprimimos el resultado*/
			System.out.println("Las personas son: ");
			while (set.hasNext()) {				
				Persona p = set.next();				
				p.print();				
			}

			System.out.println("******************************************");
			System.out.println("Realizamos consulta QBE con Patrón Pepe");
			/**Realizamos consulta QBE con un patron*/
			set = bbdd.queryByExample(new Persona("Pepe",0, null));
			/**Recorremos la lista e imprimimos el resultado*/
			while (set.hasNext()) {				
				Persona p = set.next();				
				p.print();				
			}
			
			/**3 manera de feninir consultas, con las 
			 * Native Query:
			 * 		 definen una interfaz llamada predicate con un metodo match donde 
			 * especificamos las condiciones que tienen que tener los objetos para ser seleccionados*/
			
			set = bbdd.query(new Predicate<Persona>() {
				
				public boolean match(Persona p) {//especificaremos el patron que queremos encontrar
					return p.getNombre().compareTo("Pepe")==0 || p.getEdad()>20;
				}
				
			});
			
			System.out.println("******************************************");
			for(Persona p: set){
				System.out.println("Personas >20 años: ");
				p.print();
				
			}	
			
		}finally{
			bbdd.close();
		}

	}

}
