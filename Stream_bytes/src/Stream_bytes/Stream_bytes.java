package Stream_bytes;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Stream_bytes {

	//Implementamos la función del método intentarCerrar, va a recibir un objeto de tipo closeable, 
	//cualquier stream en este caso y aqui hacemos el try catch
	public static void intentarCerrar(Closeable c){
		try{
			c.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/* Podemos trabajar con Streams de bytes. 
	 * Si trabajamos con flujos de bytes, tenemos una jerarquía
	 * Leeremos de un fichero y escribiremos en otro*/
	

	public static void main(String[] args) {
		//Los defino aquí fuera para que sean accesibles
		FileInputStream fi = null;
		FileOutputStream fo = null;
		
		//Declaramos un objeto, utilizamos el objeto de tipo DataOutputStream, para formatear la salida. 
		DataOutputStream dos = null;
		
		
		
		//Iniciamos creando el fichero de entrada dentro de un try catch, lo exige eclipse tambien, por si no existe
		//Iniciamos creando el fichero de salida dentro de un try catch, lo exige eclipse tambien, por si no existe
		try {
			 fi = new FileInputStream("entrada");
			 fo = new FileOutputStream ("salida");
			 //El envoltorio de dataOutputStream nos ofrece otra funcionalidad
			 dos = new DataOutputStream(fo);//recibe el objeto de salida
			//Definimos un buffer que almacene la lectura, tendrá una capacidad de 1000
			byte[] buffer = new byte[1000];
			//Del flujo de entrada leeremos utilizando la forma de lector de bytes
			fi.read(buffer);
			//utilizamos el flujo de salida para escribir el mismo buffer
			//fo.write(buffer);
			dos.write(buffer);//Podemos escribir un entero
			//dos.writeDouble(4.5);
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	      catch (IOException e) {
				e.printStackTrace();
	    }finally{
	    //El cierre tiene que garantizarse, se recomienda hacer dentro de la clausula finally,
	    //aunque ocurra alguna excepcion este codigo se realizara
		//Ponemos un try catch, pero como tenemos que cerrar varios streams si falla el primero no se ejecuta el segundo
		//Utilizamos el método creado intentarCerrar, que recibe en este caso un stream.
	    	intentarCerrar(fi);
	    	intentarCerrar(fo);
	    	intentarCerrar(dos);
		}
			
		
	    }
		

}


