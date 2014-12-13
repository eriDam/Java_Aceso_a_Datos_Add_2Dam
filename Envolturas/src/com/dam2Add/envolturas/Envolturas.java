package com.dam2Add.envolturas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*En el anterior proyecto streams, se utilizan los flujos, como mecanismos de entrada y salida, y los flujos suelen 
 * encadenarse o envolverse con otros flujos, para tener otra funcionalidad, como util un buffer para evitar la sobrecarga del S.O
 * */
public class Envolturas {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("entrada.txt");
		//Para hacer una lectura mas eficiente,utilizamos un objeto de tipo bufferedReader, que pueda ser construido con el objeto fileReader de arriba
		//Utiliza un Buffer para almacenar varios cara
		BufferedReader br = new BufferedReader(fr);
		
		//Podemos leer una linea de texto y nos devuelve un string
		//String str = br.readLine();
		//System.out.println("He leído: "+str);
		
		//FileWriter fw = new FileWriter("salida.txt");
		//Podemos crear un bufferedWriter y  usando el envoltorio creamos aquí el FleWriter
		BufferedWriter bw= new BufferedWriter(new FileWriter("salida.txt"));
		//Otro envoltorio de formato
		PrintWriter pw = new PrintWriter(new FileWriter("salida.txt"));
		String str;
		//mientras haya algo distinto de null es que hay algo que leer 
    	while((str=br.readLine())!=null){
			//Mostraremos x pantalla
    		System.out.println("He leído: "+str);
			//Para que lo guarde también en el fichero de salida
    		//bw.write(str+"\n");//usamos salto de linea
    		//pw.write(str+"\n");//usamos salto de linea
    		//ofrece otras opciones
    		pw.println(str);
    	}
    	pw.close();
	    bw.close();
	    br.close();
		fr.close();
	
	}

}
