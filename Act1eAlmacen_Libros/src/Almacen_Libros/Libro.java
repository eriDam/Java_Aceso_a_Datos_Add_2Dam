package Almacen_Libros;

import java.io.Serializable;

import javax.swing.JOptionPane;

//Las clases que queramos serializar, tienen que implementar la clase serializable
public class Libro implements Serializable {

	//Definimos los atributos a libro
	private String titulo=null;
	private String autor=null;
	private String anyoPubli=null;
	private String editor=null;
	private int numPag=0;
	 
	
	public Libro(){
		
	}
	
	//Método dónde le pasamos por parámetros el título, autor, año publicacion, la editorial y las páginas
	public Libro(String ti, String au,String ap,String ed, int np){
		titulo = ti;
		autor = au;
		anyoPubli = ap;
		editor =ed;
		numPag =np;

	}
	
	//Método para imprimir los datos del objeto

	public void print() {
	 
		//Imoprimimos la información de persona cuando llamemos a este método
		JOptionPane.showMessageDialog(null, "El título es: "+titulo+".\nEl autor es: "+autor+".\nEl año de publicación es: "
				+ " "+anyoPubli+". Su editor fue: "+editor+".\nTiene "+numPag+" páginas.");
		
		System.out.println( "El título es: "+titulo+".\nEl autor es: "+autor+".\nEl año de publicación es:"
				+ " "+anyoPubli+". Su editor fue: "+editor+".\nTiene "+numPag+" páginas.");
		
	}

	//Generamos los métodos getters and setters para acceder a los atributos. 
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnyoPubli() {
		return anyoPubli;
	}

	public void setAnyoPubli(String anyoPubli) {
		this.anyoPubli = anyoPubli;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}
	
}
