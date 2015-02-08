
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

					/*
					 * Para crear un entityManager necesitamos:
					 * 	 -1º fichero persistence.xml donde se especificará un peristence unit
					 *  	o varias si se quiere, ubicado en META-INF.
					 *  
					 *   -2º Si queremos utilizar JPA Se necesita agregar al proyecto, en el build path las 3 librerias
					 *   externas que estan en el eclipse link descargado:
					 * 			 eclipseLink.jar
					 * 			 javax.persistence_2.1.0v...jar
					 * 			 mysql-conector-java5.1.31.jar
					 * 
					 * 	-3º Crear un entityManager a partir de esta UP unidad de persistencia(peristence unit)
					 * 
					 * 	-4º Obtener el entityManager 		
					 * 		Código:
                     * 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
 	                 *      EntityManager em = emf.createEntityManager();
 	                 *  -5º Arrancar el sevidor con Xampp (es el que uso), arrancamos php(para el phpMyadmin)y Mysql
 	                 *  
 	                 *  -6º Run java aplication por si no va bien y conecta y ejecuta la app
 	                 *      
 	                 *      Ayuda: https://www.youtube.com/watch?v=8kekLnSUo4o				 
					 **/
public class GestionPersistencia  {
	  //Creo unas variables que se usarán en los métodos de para iniciar y cerrar el entity manager
	  private EntityManagerFactory emf;
	  private EntityManager em;
      //Importante indicar la persistence unit que tiene que hacer referencia a la definida en el xml
	  private String PERSISTENCE_UNIT_NAME = "UnitPersonasDos";
	 
	  
	//Método para iniciar el entityManager
	  public void iniciaEntityManager()  {
		  //Creo el entity managerFactory y el entity manager le pongo un nombre
		  //a la persistence unit UnitPersonas
		  emf = Persistence.createEntityManagerFactory("UnitPersonasDos");
		  em = emf.createEntityManager();
		   }
	
	  //Método para Cerrar el entityManager y el factory
	  private void cerrarEntityManager()   {
	      em.close(); 
	      emf.close(); 
	      }
	  
	  
	  //CRUD
	  
	  //CREATE
	  public void insertarP (Persona p) throws RollbackException, Exception{
		//Creamos objetos entity
		//Tenemos un objeto EntityManagerFactory utilizamos con Persistence el create y le 
	   
		  //pasamos el nombre de la persistence unit
		  EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonasDos");
		//Se realiza el createEntityManager() para crear un EntityManager
		  EntityManager em1 = emf1.createEntityManager();
		
		//Intentamos dentro de un try Usamor metodos del entity para crear transaccion y guardar el objeto en la BBDD
		//El entity manager tiene un metodo que nos permite obtener la transaccion en curso o crear una si no existe ninguna
		 try {
		 em1.getTransaction().begin();//se activa la transaccion mediante el método begin 
		 em1.persist(p);//va a almacenar la informacion de p en la bd, el persist se utiliza para persistir nuevas entidades en la bd
		 em1.getTransaction().commit();//y finaliza cuando hay un commit
		 } catch (Exception e) {	 
		 e.printStackTrace();
		 }finally {
		 em1.close();//Cerramos ocurra lo que ocurra
		 }
	    }
	  
	//Método para crear persona
//	  public void insertarP (Persona p) {
//		  gP=new GestionPersistencia();
//		  gP.iniciaEntityManager();//Inicio el entityManager y el factory con mi metodo creado expresamente
//		/*El entity manager tiene un metodo que nos permite obtener 
//		*la transaccion en curso o crear una si no existe ninguna*/
//		  em.getTransaction().begin();//se activa la transaccion mediante el método begin 
//	      p.setDni("4455786MH");
//	      p.setNombre("Pedri");
//	      p.setEdad(12);
//	      em.persist(p);//utilizo el persist para guardar este objeto en la bd
//	      em.getTransaction().commit();//y finaliza cuando hay un commit
//	      gP.cerrarEntityManager();//Cierro el entityManager y el factory con mi metodo creado expresamente
//	    }
	 
	  
	//Método para Recuperar Lista de personas
	public List<Persona>recuperarPersonas(){
			 
		//Creamos los objetos EntityManagerFactory y EntityManager
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonasDos");
		EntityManager em1 = emf1.createEntityManager();
		 
		//hacemos la consulta
	
		Query q = em1.createQuery("SELECT p FROM Persona p");//Probado con SELECT * FROM persona
		  // pasar la clase Persona en el List al arrayList
		 System.out.println("Recuperando Personas"); 
		 List <Persona> personas = q.getResultList(); //meter en personas la consulta del query
	      int num_personas=personas.size();
		  for (Persona p: personas){
			  p.print();
			}
		  
		 return personas;
	}//fin metodo recuperarPersonas
	
	// Devuelve la primera persona que coincide con el nombre pasado por parámetro. 
	
//		public Persona recuperarPersona(String nombre){
//		//creo objeto  de la clase persona
//		Persona	persona=null;
//		
//		//Creamos los objetos EntityManagerFactory y EntityManager
//		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("UnitPersonasDos");
//		EntityManager em2 = emf2.createEntityManager();
//		 
//			 
//			// Buscamos  la fila en la base de datos usando el nombre
//			// que ha pasado el usuario como parámetro
//			//hacemos la consulta
//			
//		  List<Persona> personasRec;
//		try {
//			Query qConsultaNombre = em2.createQuery("SELECT * FROM PERSONA WHERE NOMBRE='" + nombre+ "'");
//						 
//			  System.out.println("Recuperando Personas con el nombre: "+qConsultaNombre); 
//				 personasRec = qConsultaNombre.getResultList();
//			      int num_personas=personasRec.size();
//				  for (Persona p: personasRec){
//					  p.print();
//						  
//			 
//			 }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			 em2.close();//Cerramos ocurra lo que ocurra
//			 }
//		return persona;
// 
// } 
		
 		 //recuperar persona con nombre que SS
		  public Persona findPersona(String nombre){
	   System.out.println("Recuperar por nombre");
 
 	   //Creamos objetos entity
	   		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonasDos");
			EntityManager em1 = emf1.createEntityManager();
	
			//Recuperamos la persona pasada por parámetro
			Persona persona = em.getReference(Persona.class, nombre);
			persona.print();
			return persona;		
		}
		  
		//recuperar persona con nombre que SALTA EXCEPC
		  public Persona recuperarPersonaE(String nombre){
			//Creamos los objetos EntityManagerFactory y EntityManager
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonasDos");
			EntityManager em = emf.createEntityManager();

			Persona emp = em.find(Persona.class, nombre);
			emp.print();

			return emp;
		
			
			}
	 //2 intento public  Persona recuperarPersona(String nombre){
		 //Creamos los objetos EntityManagerFactory y EntityManager
//		 EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("UnitPersonasDos");
//		 EntityManager em2 = emf2.createEntityManager();
//		 Persona pers2 = em2.find(Persona.class, nombre);
//		 String query = "SELECT p.nombre FROM Persona p WHERE p.nombre = '" + nombre+ "'";
         //return em2.createQuery(query, String.class).getSingleResult();
        // return pers2;
	 
      // 3 intento Devuelve la primera persona que coincide con el nombre pasado por parámetro. 
//         public void Persona recuperarPersona(String nombre){
//         //Creamos los objetos EntityManagerFactory y EntityManager
//         EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonasDos");
//         EntityManager em1 = emf1.createEntityManager();
//         // Query q = em1.createQuery("SELECT p FROM Persona p where nombre").getResultList();
//         Persona pers = em1.find(Persona.class, nombre);
//                 
//         }//fin recuperarPersona
//         //eclipse me indica que lo haga void, pero aun así da errores
//	 }//fin recuperarPersona
	 
	  
}//fin class