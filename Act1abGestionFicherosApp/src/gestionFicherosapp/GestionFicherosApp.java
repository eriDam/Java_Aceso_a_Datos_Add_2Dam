//Erika Cebria Actividad 1 clase File Java
//Actividad 1a entregada 14-09
//Actividad 1b entregada 20-09
//Subida Git
package gestionFicherosapp;

import gestionficheros.MainGUI;

public class GestionFicherosApp {

	public static void main(String[] args) {
		// Creo una instancia de GestionFicherosImpl y una nueva interfaz grafica
		GestionFicherosImpl getFicherosImpl = new GestionFicherosImpl();
		//Se encuentra en la libreria que hemos añadido
		new MainGUI(getFicherosImpl).setVisible(true);
		
	}

}
