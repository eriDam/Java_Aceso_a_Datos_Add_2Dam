import java.util.ArrayList;
import java.util.List;

public class Vehiculo {

	private String matricula;
	private String marca = null;
	private String modelo = null;
	private Persona propietario;
	private int anyo_matr;
	private ArrayList<String> reparaciones;
	

	public void addReparaciones(ArrayList<String> reparacion){
		reparacion.add("");
	}
	

	 

	public ArrayList<String> getReparaciones() {
		return reparaciones;
	}




	public void setReparaciones(ArrayList<String> reparaciones) {
		this.reparaciones = reparaciones;
	}




	public Vehiculo() {
	}

	public Vehiculo(String mat, String mar, String mod, Persona prop,int anyo) {
		this.matricula = mat;
		this.marca = mar;
		this.modelo = mod;
		this.propietario = prop;
		this.anyo_matr = anyo;
		this.reparaciones= new ArrayList();
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
	
	public void setMatricula(String m) {
		matricula = m;
	}

	public void setMarca(String m) {
		marca = m;
	}
	
	public void setModelo(String m) {
		modelo = m;
	}
	
	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public int getAnyo_matr() {
		return anyo_matr;
	}

	public void setAnyo_matr(int anyo_matr) {
		this.anyo_matr = anyo_matr;
	}

	public void print(){
		System.out.println("Matricula: "+matricula+" marca: "+marca+" modelo: "+modelo+"Propietario: "+propietario+" Año de matriculacion: "+anyo_matr);
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca
				+ ", modelo=" + modelo + ", propietario=" + propietario
				+ ", anyo_matr=" + anyo_matr + ", reparaciones=" + reparaciones
				+ "]";
	}
	
}
