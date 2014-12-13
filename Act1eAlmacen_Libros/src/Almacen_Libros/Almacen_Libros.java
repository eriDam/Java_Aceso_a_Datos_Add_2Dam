package Almacen_Libros;

/**@author Eri
 * Actividad 1e*/
public class Almacen_Libros {

	public static void main(String[] args) {
		// Crearemos 2 personas y podremos ver su contenido
		Libro l1 = new Libro("1º Juego de Tronos, A game of Thrones","George R.R Martin","2002","GIGAMESH", 800);
		Libro l2 = new Libro("2º Choque de reyes, A clash of Kings","George R.R Martin","2003","GIGAMESH", 928 );
		Libro l3 = new Libro("3º Tormenta de espadas, A storm of swords","George R.R Martin","2005","GIGAMESH", 1216 );
		Libro l4 = new Libro("4º Festin de cuervos, A feast for crows","George R.R Martin","2007","GIGAMESH", 1064 );
		Libro l5 = new Libro("5º Una danza con dragones, A dance with dragons","George R.R Martin","2012","GIGAMESH", 1040 );
		Libro l6 = new Libro();
		

		//Creamos un objeto de Almacen para llamar a la clase
		Almacen almacen = new Almacen();
		
		//Podemos usar el guardar el objeto persona que queramos y en un fichero poniendo el nombre
		almacen.guardar(l1, "almacen.dat");
		
		//Si queremos almacenar varios objetos
		almacen.guardar(l2, "almacen.dat");
		almacen.guardar(l3, "almacen.dat");
		almacen.guardar(l4, "almacen.dat");
		almacen.guardar(l5, "almacen.dat");
		
		//En un objeto l6 realizamos el recuperar, guardamos l1, guardamos l2... y recuperamos un objeto
		l6 = almacen.recuperar("almacen.dat");
		//imprimimos l1 a 15, 
		l1.print();
		l2.print();
		l3.print();
		l4.print();
		l5.print();
		l6.print();
		
	
	}	

}
