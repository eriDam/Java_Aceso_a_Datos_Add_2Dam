import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Persistencia {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnitPeliculas");
		EntityManager em = emf.createEntityManager();			
		
		Peliculas p = new Peliculas("La comunidad del anillo", 180, "1a");
		try {
			//El entity manager tiene un metodo que nos permite obtener la transaccion en curso o crear una si no existe ninguna
			em.getTransaction().begin();//se activa la transaccion mediante el método begin 
			em.persist(p);//utilizo el persit para guardar este objeto en la bd
			em.getTransaction().commit();//y finaliza cuando hay un commit
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}	     

	}

}

