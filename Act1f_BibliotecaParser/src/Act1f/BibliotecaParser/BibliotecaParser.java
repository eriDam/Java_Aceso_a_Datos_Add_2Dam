package Act1f.BibliotecaParser;
 
public class BibliotecaParser {

	public static void main(String[] args) {
		// Desde la clase principal, haremos la llamada a la funcion/método
		// 1 creamos el objeto parser que inicializa el arrayList de libros
		Parser parser = new Parser();
		
		try {
			// y luego hacemos la llamada pasando el fichero xml
			parser.parseFicheroXml("biblioteca.xml");

			// 2 llamamos a parseDocument, dado el objeto documento ir leyendo el
			// objeto y crear una lista de objetos/nodos ( libros, definimos
			// una funcion parseDocument en la clase Parser
			parser.parseDocument();

			// Imprimo lo que he parseado
			parser.print();
		} catch (NullPointerException ne) {
			
			System.out.println("Ha ocurrido un error\n"+ne);
			ne.printStackTrace();
		}
		
	}

}
