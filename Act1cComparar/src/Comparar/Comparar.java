//Érika Cebriá
//Act.1c
//Sin método
package Comparar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Comparar {
	//Gestionamos las excepciones en el main, ya que puede existir o no el fichero, tambien se puede hacer con try catch

	public static void main(String[] args ) throws IOException {
		// Creamos 2 objetos de la clase FileReader y 2 de BufferedReader
		// ya que son dos archivos a comparar.
		FileReader frF1 = new FileReader("Nfichero1.txt");//abre el fichero de entrada
		BufferedReader br1 = new BufferedReader(frF1);//crea un Buffer de entrada. 
		FileReader frF2 = new FileReader("Nfichero2.txt");
		BufferedReader br2 = new BufferedReader(frF2);
		
		//Creamos estas variables temporales donde almacenar la lectura de las lineas del fichero
		String str1;
		String str2;
		boolean ig = true;
		str1 = br1.readLine();
		str2 = br2.readLine();
		
		//Tenemos que ir leyendo, recorreremos los ficheros mientras lo leído no sea null, ya que significará que hemos llegado al final del fichero, 
		//o  los ficheros siguen siendo iguales, lo cual controlaremos mediante una variable que denominaremos ig
		while ((str1!=null) && (str2!=null) && ig) {
			
			//Dentro del bucle compararemos las líneas almacenadas en las cadenas. 
			 //Si las líneas son iguales asumiremos que los ficheros siguen siendo iguales, leeremos e iteraremos otra vez.
			  if (!str2.equals(str1))
			  ig = false;
			 
			  str1 = br1.readLine();
			  str2 = br2.readLine();
			  }
		//los ficheros son iguales si la variable iguales sigue siendo true y si hemos llegado al final de la lectura de los dos 
		//ficheros, que las variables de lectura contengan un valor null
			  if ((ig) && (str1==null) && (str2==null)){
                  System.out.println("Los ficheros son iguales");
			  }else{
                  System.out.println("Los ficheros son diferentes");
			  }
			  //cerramos los archivos y los buffers
			  frF1.close();
			  frF2.close();
			  br1.close();
			  br2.close();
	 		
	} 



}
