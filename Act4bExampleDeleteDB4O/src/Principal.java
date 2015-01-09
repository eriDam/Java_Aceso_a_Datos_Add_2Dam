//import com.db4o.Db4oEmbedded;
//import com.db4o.ObjectContainer;
//import com.db4o.ObjectSet;
//import com.db4o.config.EmbeddedConfiguration;
//import com.db4o.ext.Db4oIOException;
//import com.db4o.query.Constraint;
//import com.db4o.query.Predicate;
//import com.db4o.query.Query;
//
//
//public class Principal {
//	
//	static ObjectContainer bbdd;
//	
//	public void inicializar(){
//		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
//		conf.common().objectClass(Persona.class).cascadeOnDelete(true);	
//		bbdd = Db4oEmbedded.openFile(conf,"prueba.db4o");
//	}
//	
//	public void delete(Object o){
//		try{
//			bbdd.delete(o);
//		}catch(Db4oIOException ex){
//			bbdd.rollback();
//		}
//		bbdd.commit();
//	}
//		
//	public static void main(String[] args) {	
//						
//		try{	
//
//			Query q = bbdd.query();		
//			q.constrain(Persona.class);			
//					
//			
//			ObjectSet<Persona> res = q.execute();
//			
//			for(Persona p2: res){
//				p2.print();				
//			}
//			
//			
//		}finally{
//			bbdd.close();
//		}
//
//	}
//
//}
