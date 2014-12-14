package com.dam2add.actividades;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
 

public class TestPersistencia {
	 
	public static void main(String[] args) throws RollbackException, Exception {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPersonas");
			
				
		
		try {
		    	
			EntityManager em = emf.createEntityManager();
			Persona p=null;
			
			
			
			//Creo Empresa
			System.out.println("Creando Empresa...");
			Empresa e = new Empresa("El corte");
			em.getTransaction().begin();//se activa la transaccion mediante el método begin 
			em.persist(e);			
			em.getTransaction().commit();
			System.out.println("***Empresa creada ok***");
			e.print();
			
			//creo persona
			System.out.println("Creando persona...");
		    Persona p1=new Persona ("29204528w",32,"Eri",e);	
			em.getTransaction().begin();//se activa la transaccion mediante el método begin 
			em.persist(p1);//va a almacenar la informacion de p1 en la bd, el persist se utiliza para persistir nuevas entidades en la bd
			em.getTransaction().commit();//y finaliza cuando hay un commit
			System.out.println("***Persona creada***"); 
			p1.print();
			
			//Creo nomina
		    Nomina nom= new Nomina(1000,p);
		    em.getTransaction().begin();
			em.persist(nom);
			em.getTransaction().commit();
			System.out.println("*** Nomina creada ok ***");
			
			
			
			//Selecciono de la Bd la primera persona
			Query q = em.createQuery("SELECT p FROM Persona p");
			p = (Persona) q.getResultList().get(0);	
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		//finally {
		//	em.close();
		//}	     

	}
	 
}
