package Comparar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Comparar {

	public Comparar() {
	}

	public boolean compararContenido (FileReader fichero1, FileReader fichero2) throws IOException{	
	 boolean ig = true; //almacena si son iguales los archivos
	 boolean dif = false; //almacena si son archivos diferentes
	
	 // Creamos 2 objetos de la clase FileReader y 2 de BufferedReader
	// ya que son dos archivos a comparar.
	FileReader Rfichero1 = new FileReader("fichero1.txt");//abre el fichero de entrada
	BufferedReader br1 = new BufferedReader(Rfichero1);//crea un Buffer de entrada
	FileReader Rfichero2 = new FileReader("fichero2.txt");
	BufferedReader br2 = new BufferedReader(Rfichero2);	
	
	//Creo dos variable string para almacenar el contenido de cada uno
	String str1, str2;
	str1 = br1.readLine();//almaceno en str1 el contenido que leo con readLine
	str2 = br2.readLine();//almaceno en str2 el contenido que leo con readLine
	
	/*Mientras las lineas de los ficheros son iguales seguimos recorriendo o iterando en el fichero linea a linea
	 *hasta llegar a null, que será cuando acaba el fichero. 
	 *La variable ig tomara el  valor de false cuando las lineas
	 *no sean iguales, terminará el bucle*/
	while ((str1!=null) && (str2!=null) && ig) {
	     
	     if (!str1.equals(str2))
	             ig = false;
	     
	     str1 = br1.readLine();
	     str2 = br2.readLine();
	     
	     /*Si lineas son igual a null y la variable aux ig es true, los ficheros
	      * son iguales y devolverá el valor true, sin no lo son devolverá que son dif.*/
	     	if ((ig) && (str1==null) && (str2==null))
	     	{
	         return ig;
	     	}
	
	}
		return dif;
	}
	public static void main(String[] args ) throws IOException  {
		 
		Comparar comparoFicheros = new Comparar();
		FileReader fichero1=null;
		FileReader fichero2=null;
		//En este caso decido sacar el mensaje, pero podria sobreescribir los archivos o realizar otra acción, ya que 
		//el metodo comparar contenido como indica me lo compara y me da un resultado. 
		if (comparoFicheros.compararContenido(fichero1, fichero2) == true){
		System.out.println("El contenido de los ficheros es igual");	
		}else{
		System.out.println("El contenido de los ficheros no es igual");	
		}

	}

}
