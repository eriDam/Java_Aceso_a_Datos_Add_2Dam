package Almacen_Persona;

public class Almacen_Persona {

	public static void main(String[] args) {
		// Crearemos 2 personas y podremos ver su contenido
		Persona p1 = new Persona("Juan", 20);
		Persona p2 = new Persona("Jose", 10);
		Persona p3 = new Persona();
		
		//No haria falta ya que hemos creado un método print (p1.getNombre();p1.getEdad();)
		//Imprimimos las personas que tenemos.
		//p1.print();
		p2.print();//p2 hace referencia a una persona de nombre Jose y edad 10

		//Creamos un objeto de Almacen para llamar a la clase
		Almacen almacen = new Almacen();
		
		//Podemos usar el guardar el objeto persona que queramos y en un fichero poniendo el nombre
		almacen.guardar(p1, "almacen.dat");
		
		//Si queremos almacenar varios objetos
		almacen.guardar(p2, "almacen.dat");
		
		//En un objeto p3 realizamos el recuperar, guardamos p1, guardamos p2 y recuperamos un objeto
		p3 = almacen.recuperar("almacen.dat");
		//imprimimos p3, se recuperará siempre el último objeto guardado
		p3.print();
		
		//Podemos recuperarlo
		//Asignamos el objeto recuperado al objeto p2 para ver que se modifica este objeto
		//p2= almacen.recuperar("almacen.dat");
		
		//cuando el código llega aquí, p2 hará referencia a un objeto Juan y jose
		//p2.print();
	}	

}
