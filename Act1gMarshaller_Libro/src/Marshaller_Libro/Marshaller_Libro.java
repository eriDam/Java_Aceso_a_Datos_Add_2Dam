package Marshaller_Libro;

import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;
 

/** Marshaller es el proceso de serialización. Significa crear un documento XML de un árbol de 
 * contenido(En nuestro caso el arbol del contenido seria un Objeto Java).
 * 
 * Pasos:
 * 1- cargar los datos
 * 2- Crear un objeto Document
 * 3- Crear el elemento raíz
 * 4- Para cada item crearemos un elemento<Libro> y lo añadimos al elemento raiz
 * 5- Serializamos el objeto Document para generar un fichero xml
 * 
 * */

public class Marshaller_Libro {

	public static void main(String[] args) {
		 
		/**En primer lugar se define un ArrayList*/ 
				ArrayList<Libro> biblioteca;
				
				/**Cargamos los datos, también se pueden cargar desde una bd*/
				biblioteca =  new ArrayList<Libro>();
				biblioteca.add(new Libro("Introduction to Linux",2008, "Machtelt & Garrels","O'Reilly", 256));
				biblioteca.add(new Libro("El lenguaje de programación C", 1991,"Kernighan & Ritchie","Prentice Hall",294));
				System.out.println("Se han cargado los datos ok.");
				
				/**Creo un objeto Marshaller al que le paso el ArrayList que he creado*/
				Marshaller marshaller = new Marshaller(biblioteca);
				
				
				/**creo el documento*/
				marshaller.crearDocumento();
				
				/**creo el árbol Dom*/
				marshaller.crearArbolDOM();
				
				/**paso de DOM a XML
				creo un fichero */
				File file = new File("biblioteca.xml");
				try {
					marshaller.escribirDocumentAXML(file);
					System.out.println("Fichero generado correctamente");
				} catch (TransformerException te) {
					System.out.println("No se ha podido transformar el archivo Xml"+te); 
					te.printStackTrace();
				}
				
			}

		}
