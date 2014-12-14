import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;

/*
					 * Para crear un entityManager necesitamos:
					 * 	 -1 fichero persistence.xml donde se especificará un peristence unit
					 *  	o varias si se quiere, ubicado en META-INF.
					 *   -Se necesita agregar al proyecto, en el build path las 3 librerias:
					 * 			 eclipseLink.jar
					 * 			 javax.persistence_2.1.0v...jar
					 * 			 mysql-conector-java5.1.31.jar
					 * 	-Crear un entityManager a partir de esta UP unidad de persistencia(peristence unit)
					 * 	-Obtener el entityManager 		
					 * 		Código:
                     * 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
 	                 *      EntityManager em = emf.createEntityManager();
 	                 *      
 	                 *      Ayuda: https://www.youtube.com/watch?v=8kekLnSUo4o	
 	                 *      http://www.arquitecturajava.com/jpa-onetomany/			 
					 **/
public class GestionPersistencia {
	  private EntityManagerFactory emf;
	  private EntityManager em;
	  private String PERSISTENCE_UNIT_NAME = "UnitPersonas";
	 
	  
	//Método para iniciar el entityManager
	  public void iniciaEntityManager()  {
		  emf = Persistence.createEntityManagerFactory("UnitPersonas");
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
		  EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonas");
		//Se realiza el createEntityManager() para crear un EntityManager
		  EntityManager em1 = emf1.createEntityManager();
		
		//Usamos metodos del entity para crear transaccion y guardar el objeto en la BBDD
		//El entity manager tiene un metodo que nos permite obtener la transaccion en curso o crear una si no existe ninguna
		 try {
		 em1.getTransaction().begin();//se activa la transaccion mediante el método begin 
		 em1.persist(p);//va a almacenar la informacion de p en la bd, el persist se utiliza para persistir nuevas entidades en la bd
		 em1.getTransaction().commit();//y finaliza cuando hay un commit
		 } catch (Exception e) {	 
		 e.printStackTrace();
		 }finally {
		 em1.close();
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
	  public void modificar(Persona p){
		//Creamos objetos entity
			EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonas");
			EntityManager em1 = emf1.createEntityManager();
			
				em1.getTransaction().begin();//se activa la transaccion mediante el método begin
				em1.merge(p);
				em1.getTransaction().commit();//y finaliza cuando hay un commit
				System.out.println("***Modificado ok***");
				  }
	  
	  //RETRIEVE
	  //recuperar persona con dni que es el id
	  public Persona findPersona(String id){
		//Creamos objetos entity
			EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonas");
			EntityManager em1 = emf1.createEntityManager();
			em1.getTransaction().begin();//se activa la transaccion mediante el método begin 
			return (Persona) em1.find(Persona.class, id);
			
			
			
	 }
	  
	//Método para Recuperar Lista de personas
	public List<Persona>recuperarPersonas(){
			 
		//Creamos los objetos EntityManagerFactory y EntityManager
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonas");
		EntityManager em1 = emf1.createEntityManager();
		 
		//hacemos la consulta
	
		Query q = em1.createQuery("SELECT p FROM Persona p");//Probado con SELECT * FROM persona
		  // pasar la clase Persona en el List al arrayList
		 System.out.println("***Recuperando Personas***"); 
		 List <Persona> personas = q.getResultList(); //meter en personas la consulta del query
	      int num_personas=personas.size();
		  for (Persona p: personas){
			  p.print();
			}
		  
		 return personas;
	}//fin metodo recuperarPersonas
	
	// Devuelve la primera persona que coincide con el nombre pasado por parámetro. 
	
//	 public Persona recuperarPersona(String nombre){
//		//Creamos los objetos EntityManagerFactory y EntityManager
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();//se activa la transaccion mediante el método begin 
//		Persona pers2 = em.find(Persona.class, nombre);
//		
//		pers2.print();
//		
//		return pers2;
//
//		}
	
	 //2 intento public  Persona recuperarPersona(String nombre){
		 //Creamos los objetos EntityManagerFactory y EntityManager
//		 EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("UnitPersonas");
//		 EntityManager em2 = emf2.createEntityManager();
//		 Persona pers2 = em2.find(Persona.class, nombre);
//		 String query = "SELECT p.nombre FROM Persona p WHERE p.nombre = '" + nombre+ "'";
       //return em2.createQuery(query, String.class).getSingleResult();
      // return pers2;
	 
    // 3 intento Devuelve la primera persona que coincide con el nombre pasado por parámetro. 
//       public void Persona recuperarPersona(String nombre){
//       //Creamos los objetos EntityManagerFactory y EntityManager
//       EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("UnitPersonas");
//       EntityManager em1 = emf1.createEntityManager();
//       // Query q = em1.createQuery("SELECT p FROM Persona p where nombre").getResultList();
//       Persona pers = em1.find(Persona.class, nombre);
//               
//       }//fin recuperarPersona
//       //eclipse me indica que lo haga void, pero aun así da errores
//	 }//fin recuperarPersona
	 
	 //DELETE
	 	public void deletePersona(Persona p){
	 		//Creamos los objetos EntityManagerFactory y EntityManager
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();//se activa la transaccion mediante el método begin  
			em.remove(em.merge(p));
			em.getTransaction().commit();//y finaliza cuando hay un commit
			System.out.println("***Eliminado ok***");
			em.close();
	 	}
	 	public List<Empresa>recuperarEmpresas(){
	 	//Creamos los objetos EntityManagerFactory y EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();//se activa la transaccion mediante el método begin  
		 
		//hacemos la consulta
	
		Query q = em.createQuery("SELECT e FROM Empresa e");
		  // pasar la clase Persona en el List al arrayList
		 System.out.println("***Recuperando Empresas***"); 
		 List <Empresa> empresas = q.getResultList(); //meter en empresas la consulta del query
	      int num_empresas=empresas.size();
		  for (Empresa e: empresas){
			  e.print();
			}
		  
		 return empresas;
	}//fin metodo recuperarPersonas
}//fin class
