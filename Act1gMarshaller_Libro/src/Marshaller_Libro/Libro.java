package Marshaller_Libro;

import java.io.Serializable;

public class Libro implements Serializable{
	/**
	 * Atributos del objeto Libro, objeto y no clase por q se va a serializar
	 */
	private String titulo = null;
	private String autor  = null;
	private int    anyo   = 0;
	private String editor = null;
	private int    paginas= 0;
	
	/**
	 * Constructor por defecto para llamar sin parametros
	 */
	public Libro() {
	}
	
	/**
	 * Constructor sobrecargado (con el mismo nombre, pero distintos parámetros) al que le pasamos 
	 * param tit
	 * param any
	 * param aut
	 * param ed
	 * param pgs
	 */
	public Libro(String tit, int any, String aut, String ed, int pgs) {
		this.titulo = tit;
		this.anyo = any;
		this.autor = aut;
		this.editor = ed;
		this.paginas = pgs;
	}

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

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
	/**
	 * Método print para imprimir los datos de libros 
	 */
		public void print(){
			System.out.println("El título es: "+titulo+". El año es: "+anyo+".\nEl autor es: "+autor+" y el editor: "+editor+".\nTiene "+paginas+" páginas.");
		}
}
