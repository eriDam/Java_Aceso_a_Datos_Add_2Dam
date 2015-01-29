import java.util.Arrays;

public class Vehiculo {

	private String matricula;
	private String marca = null;
	private String modelo = null;
	// He agregado el año y un array de reparaciones
	private int anyo;
	private String[] reparaciones;

	public Vehiculo() {
	}

	public Vehiculo(String mat, String mar, String mod, int an, String[] arreglos) {
		matricula = mat;
		marca = mar;
		modelo = mod;
		anyo = an;
		setReparaciones(arreglos);
	}

	public String getMatricula() {
		return matricula;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}
	
	public int getAnyo() {
		return anyo;
	}
	
	public String[] getReparaciones() {
		return reparaciones;
	}

	public void setMatricula(String m) {
		matricula = m;
	}

	public void setMarca(String m) {
		marca = m;
	}

	public void setModelo(String m) {
		modelo = m;
	}
	
	public void setAnyo(int an) {
		anyo = an;
	}
	
	public void setReparaciones(String[] reparaciones) {
		this.reparaciones = reparaciones;
	}

	// Al hacer print uso Arrays.toString porque creo que es la forma más fácil de mostrar el array
	public void print() {
		System.out.println("Matricula: " + matricula + " marca " + marca
				+ " modelo " + modelo+ " año " + anyo+ ". Reparaciones: " + Arrays.toString(reparaciones));
	}

}
