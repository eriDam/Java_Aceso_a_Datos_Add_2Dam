import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopiaArchivosbytes {
	//Los defino aquí fuera para que sean accesibles
	FileInputStream fi = null;
	FileOutputStream fo = null;		
	//Declaramos un objeto, utilizamos el objeto de tipo DataOutputStream, para formatear la salida. 
    DataOutputStream dos = null;
	
	// Constructor
	public CopiaArchivosbytes() {
		
	}//fin constructor
	
	
	//Implementamos la función del método intentarCerrar, va a recibir un objeto de tipo closeable, 
	//cualquier stream en este caso y aqui hacemos el try catch
		public static void intentarCerrar(Closeable c){
			try{
				if(c != null) { c.close(); }
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
		//Método para escribir/copiar los bytes del archivo leído
	public static void copiaBytes(String fi1, String fi2) throws FileNotFoundException{
		    FileInputStream fi = new FileInputStream(fi1);
			FileOutputStream fo = new FileOutputStream (fi2);
			//El envoltorio de dataOutputStream nos ofrece otra funcionalidad
			DataOutputStream dos = new DataOutputStream(fo);//recibe el objeto de salida
			try {
			//Definimos un buffer que almacene la lectura, tendrá una capacidad de 2000
			byte[] buffer = new byte[1000];
			//A través de un bucle, hasta que no haya nada que leer, Del flujo de entrada leeremos utilizando la forma de lector de bytes
			//utilizamos el flujo de salida para escribir el mismo buffer
			while(fi.read(buffer) != -1) {
				fo.write(buffer);
				} 
			System.out.println("Leido todo el archivo");
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
	    	System.out.println("Cerrados todos los streams");
		}
	}
	
	

	//Método principal
	public static void main(String[] args) throws FileNotFoundException { //En lugar de try catch capturo así las excepciones
		copiaBytes("lena.jpg", "copiaLena.jpg");

	}//Fin main

}
