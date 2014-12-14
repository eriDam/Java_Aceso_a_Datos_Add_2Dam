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
			
			//Selecciono de la Bd la primera persona
			em.getTransaction().begin();
			//Creo Empresa
			System.out.println("Creando Empresa...");
			Empresa e = new Empresa("El corte");
			Query q = em.createQuery("SELECT p FROM Persona p");
			p = (Persona) q.getResultList().get(0);	
			
			Nomina nom= new Nomina(1000,p);
			em.persist(e);
			em.persist(nom);
			em.getTransaction().commit();
			System.out.println("***Empresa creada ok***");
			e.print();
			
			//creo persona
			System.out.println("Creando persona...");
		    p=new Persona ("29204528w",32,"Eri", nom,e);	
			em.getTransaction().begin();//se activa la transaccion mediante el método begin 
			em.persist(p);//va a almacenar la informacion de p en la bd, el persist se utiliza para persistir nuevas entidades en la bd
			em.getTransaction().commit();//y finaliza cuando hay un commit
			System.out.println("***Persona creada***"); 
			p.print();
			 
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		//finally {
		//	em.close();
		//}	     

	}
	 
}
