package com.eriDam.addAct5;
/**
 * Actividad 5c XQJ con BaseX
 * @author erika_000
 * 
 * Programa   que incluya un menú que muestra varias opciones al usuario, 
 * entre las que se incluye recuperar una persona por
 * DNI, insertar y borrar. Dependiendo de la opción seleccionada, el programa
 * mostrará por pantalla un mensaje con los datos de la persona recuperada (en
 * caso de que seleccione una consulta), o bien un mensaje de confirmación (en
 * caso que realice una inserción o un borrado).
 * 
 * El menu solo puede ejecutar una única opción ya que no lo he conseguido colocar dentro 
 * de un bucle infinito.  Hay que iniciar cada vez la aplicación.
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Connect {

	public static void main(String[] args) {
		//Creo un objeto de la clase GestionBdXQJ
			GestionBdXQJ gestorXQJ = new GestionBdXQJ();
		 gestorXQJ.lanzarMenuPrincipal();
		

	}

}
