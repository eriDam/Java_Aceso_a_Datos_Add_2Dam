import javax.persistence.RollbackException;


public class TestPersistencia {

	public static void main(String[] args)throws RollbackException, Exception {
		
		try{
			  //Creamos objetos para poder probar los metodos
				Persona p = new Persona("4900980003",30,"Dani");
				Persona p2=new Persona("4900980002",42,"Paco");
				GestionPersistencia gp = new GestionPersistencia();
				
				//Usamos los métodos creados
				gp.insertarP(p);
				gp.insertarP(p2);
				System.out.println("Persona creada ok");
				gp.recuperarPersonas();
				//gp.recuperarPersona("Paco");//No recupera y salta excep
		}
	         
	 	      catch(Exception ex)      {
	 	          System.out.println("Error");
	 	          ex.printStackTrace();
	 	          System.err.println(ex);
	 	          
	 	      }
			
		  }
		  

	}


