//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.RollbackException;
//
//
//public class TestMapeo {
//
//public static void main(String[] args)throws RollbackException, Exception {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
//		EntityManager em = emf.createEntityManager();
//		GestionPersistencia gp = new GestionPersistencia();
//		
//	
//		try{
//				Empresa empresa = new Empresa("El Corte Inglés");
//				System.out.println("Creando Empresa");
//				em.getTransaction().begin();
//				em.persist(empresa);
//				em.getTransaction().commit();
//				System.out.println("Empresa creada: "+ empresa.getNombre());
//				
//			  //Creamos objetos para poder probar los metodos
//				Persona p = new Persona("4900980003",30,"Dani", empresa);
//				Persona p2=new Persona("4900980002",42,"Paco",empresa);
//
//				
//				//Usamos los métodos creados
//				gp.insertarP(p);
//				gp.insertarP(p2);
//				System.out.println("***Persona creada ok***");
//				gp.recuperarPersonas();
//				//gp.recuperarPersona("Paco");//No recupera y salta excep
//				
//				//Recupero persona con dni
//				System.out.println("***Recuperando persona con Dni: 4900980003*** ");
//				Persona ps=gp.findPersona("4900980003");
//				ps.print();
//				
//				
//				Persona ps1=gp.findPersona("4900980002");
//				p2.setNombre("Andreu");
//				//Dejo comentado para que funcione borrar si hace una cosa no puede la otra
//				gp.modificar(p2);
//			 
//				p2.print();
//				gp.deletePersona(ps);
//				
//				
//				
//				//usamos para recuperar nominas
////				Nomina n = new Nomina("Mensual");
////				p.addNomina(n);
////				p.getlistaNom();
//	         }
//	         
//	 	      catch(Exception ex)      {
//	 	          System.out.println("***Error***");
//	 	          ex.printStackTrace();
//	 	          System.err.println(ex);
//	 	          
//	 	      }
//			
//		  }
//		  
//
//	}
//
//
//
