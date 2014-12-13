package Almacen_Persona;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Creamos esta clase para gestionar todos los streams y  métodos de guardar y almacenar para no escribir informacion en la otra clase almacen_Persona

public class Almacen {
	
	//Creamos un cosntructor para que pueda ser llamado desde el método principal, creando un objeto
	public void Almacen(){
		
	}
	
	public void guardar(Persona p, String f){
		//Declaramos fuera de try el objeto. 
		ObjectOutputStream out = null;
		//Funcion que guardara una persona y el nombre del fichero donde va aser guardada esta info
		/*Para guardar el objeto persona, creamos un objeto de tipo ObjectOutputStream, este objeto tiene en su constructor 
		 * (hace de envoltura) un objeto de tipo FileOutputStream que creará el fichero*/
		try {
			out = new ObjectOutputStream (new FileOutputStream (f));//Lo inicializamos
			System.out.println("Fichero creado");
			//el método para escribirlo es writeObject
			out.writeObject(p);
			System.out.println("Fichero escrito ok");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("El fichero no se puede crear");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Algo a ocurrido...");
		}finally{
			//Con finally indicamos que este código se ejecute pese a las excepciones
			//Hay que cerrar los streams, usamos el método intentar cerrar
			intentarCerrar(out);
		}
		
	}

	public void intentarCerrar(Closeable aCerrar) {
		// TODO Auto-generated method stub
		try{
			if (aCerrar !=null) {
				aCerrar.close();
				System.out.println("Stream cerrado ok");
			}
		}catch (IOException exc) {
			exc.printStackTrace(System.err);
		}
		
		
	}
	
	//Método que realiza proceso inverso a intentarCerrar, recupera
	public  Persona recuperar(String f){
	Persona p = null;
	ObjectInputStream in=null;
	try{
		in = new ObjectInputStream (new FileInputStream (f));
		
		;//Leerá del fichero al que le pasamos el nombre por parámetro
		//Hay abrá un objeto de tipo Persona almacenado y lo recuperará, hacemos casting por 
		//qué estamos leyendo un objeto de un string y hacemos cast para convertir al tipo de objeto 
		p = (Persona) in.readObject();
		System.out.println("Fichero recuperado ok");
	}catch(ClassNotFoundException ex){
		System.err.println("Error de fichero");
	}catch(IOException ex){
		System.err.println("Error IO");
	}finally{
		intentarCerrar(in);
	}
	return p;
	
}
}
