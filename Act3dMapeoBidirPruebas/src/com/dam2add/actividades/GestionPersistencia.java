package com.dam2add.actividades;


import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

/**
 * Clase que implementa la gestión de la persistencia en JPA de la clase Persona.

 */
public class GestionPersistencia {
	
	private final static String UNIT = "UnitPersonas3d"; // Nombre de la Persistence Unit
	EntityManagerFactory emf = null;                 // EntityManagerFactory
	EntityManager em = null;                         // EntityManager
	
	/**
	 * Añadir una Persona
	 * 
	 * @param p  Persona
	 */
	public void insertarPersona(Persona p) {
		
		// Abrir EntityManagerFactory y EntityManager,
		// e insertar la persona, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
				em.getTransaction().begin();   // Comienzo de la transacción
				em.persist(p);                 // Persistencia
				em.getTransaction().commit();  // Final de la transacción
				
			  // Controlar posibles excepciones
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(EntityExistsException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(RollbackException e) {
				e.printStackTrace();
			}
		  // Controlar errores en la apertura de emf y em
		} else {
			System.err.println("ERROR No se ha podido abrir");
		}
		
		// Cerrar emf y em,
		// controlando   errores
		if(cerrarEmFactoryEm() == false) {
			System.err.println("ERROR no se ha podido cerrar");
		}
	}
	
	/**
	 * Recuperar todas las personas
	 * 
	 * @return lista de personas,   null si no hay ninguna o hay si error  
	 */
	public List<Persona> recuperarPersonas() {
		
		List<Persona> personas = null;  // Lista de personas a devolver
		TypedQuery<Persona> q = null;   // TypedQuery
		
		// Abrir EntityManagerFactory y EntityManager,
		// y recuperar todas las personas, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
		
				// Ejecutar el Query para recuperar todas las personas
				q = em.createQuery("SELECT p FROM Persona p", Persona.class);
				
				// Tomar la lista de personas
				personas = q.getResultList();
		
			  // Controlar posibles excepciones
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(QueryTimeoutException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(PessimisticLockException e) {
				e.printStackTrace();
			} catch(LockTimeoutException e) {
				e.printStackTrace();
			} catch(PersistenceException e){
				e.printStackTrace();
			}
		}
		
		// Cerrar emf y em,
		// controlando posibles errores
		if(cerrarEmFactoryEm() == false) {
			personas = null;
		}
		
		// Devolver la lista de personas
		return personas;
	}
	
	/**
	 * Devolver la 1ª persona que coincida con el nombre
	 * 
	 * @param nombre Nombre de la persona
	 * @return primera persona encontrada, null en caso de no existir o error
	 */
	public Persona recuperarPersona(String nombre) {
		
		Persona p = null;              // Persona a devolver
		List<Persona> personas = null; // Lista de personas encontradas
		TypedQuery<Persona> q = null;  // TypedQuery
		
		// Abrir EntityManagerFactory y EntityManager,
		// y recuperar la persona, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
		
				// Ejecutar el Query para recuperar la 1ª persona que coincida con el nombre
				q = em.createQuery("SELECT p FROM Persona p WHERE p.nombre ='" + nombre +"'", Persona.class);
				
				// Tomar la lista de resultados
				personas = q.getResultList();
				
				// Si hay algún resultado en la lista, tomar la 1ª persona
				if(personas.isEmpty() == false) {
					
					p = personas.get(0); // Tomar la 1ª persona de la lista
					
				} else {
					
					p = null;            // No hay resultados en la lista, devolver null
				}
				
			  // Controlar posibles excepciones
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(NoResultException e) {
				e.printStackTrace();
			} catch(NonUniqueResultException  e) {
				e.printStackTrace();
			} catch(QueryTimeoutException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(PessimisticLockException e) {
				e.printStackTrace();
			} catch(LockTimeoutException e) {
				e.printStackTrace();
			} catch(PersistenceException e){
				e.printStackTrace();
			}
		}
		
		// Cerrar emf y em,
		// controlando posibles errores
		if(cerrarEmFactoryEm() == false) {
			p = null;
		}
		
		// Devolver la persona encontrada
		return p;
	}
	
	/**
	 * Recuperar una persona mediante su DNI
	 * 
	 * @param  dni  DNI de la persona a recuperar
	 * @return persona en caso de éxito, null en caso de no existir o error
	 */
	public Persona recuperarPersonaconDni(String dni) {
		
		Persona p = null; // Persona a devolver
		
		// Abrir EntityManagerFactory y EntityManager,
		// y recuperar la persona, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
		
				// Buscar y recuperar la persona, o null si no existe
				p = em.find(Persona.class, dni);
				
			  // Controlar posibles excepciones
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		
		// Cerrar emf y em,
		// controlando posibles errores
		if(cerrarEmFactoryEm() == false) {
			p = null;
		}
		
		// Devolver la persona encontrada, o null
		return p;
	}
	
	/**
	 * Eliminar una persona mediante su DNI
	 * 
	 * @param dni  DNI de la persona a eliminar
	 */
	public void eliminarPersona(String dni) {
		
		// Abrir EntityManagerFactory y EntityManager,
		// y eliminar la persona, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
				
				em.getTransaction().begin();                     // Comienzo de la transacción
				Persona p = em.getReference(Persona.class, dni); // Buscar la persona por la clave (dni)
				em.remove(p);                                    // Eliminarla
				em.getTransaction().commit();                    // Final de la transacción
				
			  // Controlar posibles excepciones
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(RollbackException e) {
				e.printStackTrace();
			} catch(EntityNotFoundException e) {
				e.printStackTrace();
			}
		  // Controlar errores en la apertura de EMF y EM
		} else {
			System.err.println("ERROR al abrir emf o em");
		}
		
		// Cerrar EMF y EM,
		// controlando posibles errores
		if(cerrarEmFactoryEm() == false) {
			System.err.println("ERROR no se ha podido cerrar");
		}
	}
	
	/**
	 * Método para  Modificar persona
	 * 
	 * @param p  Persona
	 */
	public void modificarPersona(Persona p) {
		
		// Abrir EntityManagerFactory y EntityManager,
		// y modificar la persona, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
				
				em.getTransaction().begin();  // Comienzo de la transacción
				em.merge(p);                  // Modificar la persona
				em.getTransaction().commit(); // Final de la transacción
				
			  // Controlar posibles excepciones
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(RollbackException e) {
				e.printStackTrace();
			}
			
			  // Controlar errores en la apertura de EMF y EM
				} else {
					System.err.println("ERROR al abrir emf o em");
				}
				
				// Cerrar EMF y EM,
				// controlando posibles errores
				if(cerrarEmFactoryEm() == false) {
					System.err.println("ERROR no se ha podido cerrar");
				}
			}
	
	/**
	 * Añadir una Empresa
	 * 
	 * @param emp  Empresa
	 */
	public void insertarEmpresa(Empresa emp) {
		
		// Abrir EntityManagerFactory y EntityManager,
		// e insertar la empresa, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
				em.getTransaction().begin();   // Comienzo de la transacción
				em.persist(emp);               // Persistencia
				em.getTransaction().commit();  // Final de la transacción
				
			  // Controlar posibles excepciones
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(EntityExistsException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(RollbackException e) {
				e.printStackTrace();
			}
			  // Controlar errores en la apertura de EMF y EM
				} else {
					System.err.println("ERROR al abrir emf o em");
				}
				
				// Cerrar EMF y EM,
				// controlando posibles errores
				if(cerrarEmFactoryEm() == false) {
					System.err.println("ERROR no se ha podido cerrar");
				}
			}
	
	/**
	 * Añadir una Nomina
	 * 
	 * @param nom  Nomina
	 */
	public void insertarNomina(Nomina nom) {
		
		// Abrir EntityManagerFactory y EntityManager,
		// e insertar la nomina, controlando posibles errores.
		if(abrirEmFactoryEm() == true) {
			try {
				em.getTransaction().begin();   // Comienzo de la transacción
				em.persist(nom);               // Persistencia
				em.getTransaction().commit();  // Final de la transacción
				
			  // Controlar posibles excepciones
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(EntityExistsException e) {
				e.printStackTrace();
			} catch(IllegalArgumentException e) {
				e.printStackTrace();
			} catch(TransactionRequiredException e) {
				e.printStackTrace();
			} catch(RollbackException e) {
				e.printStackTrace();
			}
			  // Controlar errores en la apertura de EMF y EM
				} else {
					System.err.println("ERROR al abrir emf o em");
				}
				
				// Cerrar EMF y EM,
				// controlando posibles errores
				if(cerrarEmFactoryEm() == false) {
					System.err.println("ERROR no se ha podido cerrar");
				}
			}

	/**
	 * @method 
	 * Método para abrir EntityManagerFactory y EntityManager
	 * 
	 * @return true en caso de éxito, o false en caso de error
	 */
	private boolean abrirEmFactoryEm() {
		
		emf = abreEMF(UNIT);  // EntityManagerFactory
		em = abreEM(emf);   // EntityManager
		
		// Controlar posibles errores
		if(em == null) {
			cierraEMF(emf);
			
			// Devolver fracaso
			return false;
		}
		
		// Devolver éxito
		return true;
	}
	
	/**
	 * Cerrar EntityManagerFactory y EntityManager
	 * 
	 * @return true en caso de éxito, o false en caso de error
	 */
	private boolean cerrarEmFactoryEm() {
		
		boolean rEm = cierraEM(em);    // Resultado del cierre del EntityManager
		boolean rEmf = cierraEMF(emf); // Resultado del cierre del EntityManagerFactory
		
		// Comprobar resultado, y devolver
		// true en caso de éxito,
		// false en caso de error
		return (rEm == true && rEmf == true) ? true : false;
	}
	
	/**
	 * Abrir EntityManagerFactory
	 * 
	 * @param  unit  Unidad de persistencia
	 * @return EntityManagerFactory
	 */
	private EntityManagerFactory abreEMF(String unit) {
		
		// Crear la EMF y devolverla
		return Persistence.createEntityManagerFactory(unit);
	}
	
	/**
	 * Cerrar la EntityManagerFactory
	 * 
	 * @param  emf EntityManagerFactory
	 * @return true en caso de éxito, o false en caso de error
	 */
	private boolean cierraEMF(EntityManagerFactory emf) {
		
		// Cerrar la EMF controlando posibles excepciones
		try {
			
			// Cerrar la EMF si no es null y está abierta
			if(emf != null && emf.isOpen() == true)
				emf.close();
			
			// Devolver éxito
			return true;
			
		  // Controlar posibles excepciones
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		// Devolver fracaso
		return false;
	}
	
	/**
	 * Abrir EntityManager
	 * 
	 * @param  emf  EntityManagerFactory
	 * @return EntityManager o null en caso de error
	 */
	private EntityManager abreEM(EntityManagerFactory emf) {
		
		EntityManager em = null; // EntityManager a devolver
		
		// Abrir la EM, controlando posibles excepciones
		try {
			
			em = emf.createEntityManager(); // Abrir EM
			
		  // Controlar posibles excepciones
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		// Devolver EntityManager en caso de éxito,
		// o null en caso de error
		return em;
	}
	
	/**
	 * Cerrar EntityManager
	 * 
	 * @param  em  EntityManager
	 * @return true en caso de éxito, o false en caso de error
	 */
	private boolean cierraEM(EntityManager em) {
		
		// Cerrar la EM, controlando
		// posibles excepciones
		try {
			
			// Cerrar la EM si no es null y está abierta
			if(em != null && em.isOpen() == true)
				em.close();
			
			// Devolver éxito
			return true;
			
		  // Controlar posibles excepciones
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		// Devolver fracaso
		return false;
	}
}
