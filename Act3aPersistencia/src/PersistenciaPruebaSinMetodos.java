//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
///*
// * Para crear un entityManager necesitamos:
// * -1 fichero persistence.xml donde se especificará un peristence unit
// *  o varias si se quiere, ubicado en META-INF.
// *  
// * -Se necesita agregar al proyecto, en el build path las 3 librerias:
// * 			 eclipseLink.jar
// * 			 javax.persistence_2.1.0v...jar
// * 			 mysql-conector-java5.1.31.jar
// * 
// * -Crear un entityManager a partir de esta UP unidad de persistencia(peristence unit)
// * 
// * -Obtener el entityManager 
// * 		Código:
// * 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
//		EntityManager em = emf.createEntityManager();							 
// */
//
//public class Persistencia {
//
//	public static void main(String[] args) {
//		//********* OBTENER EL ENTITYMANAGER************
//		
//		//Tenemos un objeto EntityManagerFactory utilizamos con Persistence el create y le 
//		//pasamos el nombre de la persistence unit
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
//		//Se realiza el createEntityManager() para crear un EntityManager
//		EntityManager em = emf.createEntityManager();			
//		
//		//Permite añadir la persona pasada por parámetro. 
//		//public void insertarPersona(Persona p){
//		//Persona p = new Persona("Pedro", 25, "4455786H1");
//		List<Persona> personas = new ArrayList<Persona>();
//		
//		for (int i=0;i<5; i++){
//			//Creo objeto persona en cada iteracion 
//			Persona p = new Persona("Pedro"+i, 20, "0999000"+i);
//			personas.add(p);//añado
//			
//			
//		try {
//			//El entity manager tiene un metodo que nos permite obtener la transaccion en curso o crear una si no existe ninguna
//			em.getTransaction().begin();//se activa la transaccion mediante el método begin 
//			em.persist(p);//utilizo el persist para guardar este objeto en la bd
//			em.getTransaction().commit();//y finaliza cuando hay un commit
//			//encontrar x dni
//			Persona p1 = em.find(Persona.class,"4455786H1");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		finally {
////			
////		}
//		}//fin for1
//		
//		for (Persona p: personas){
//			p.print();
//		
//		}	
//		em.close();     
//		
//	
//		
//	}
//	
//	
//	//Metodo para recuperar personas
//	 //public List<Persona> getPersonaList() {
//	       // return em.createQuery("select p from personas p order by p.dni").getResultList();
//	    //return List<Persona>;
//	// }
//	
//}
//
