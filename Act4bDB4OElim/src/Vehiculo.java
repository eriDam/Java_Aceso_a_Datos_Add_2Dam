public class Vehiculo {

	private String matricula;
	private String marca = null;
	private String modelo = null;

	public Vehiculo() {
	}

	public Vehiculo(String mat, String mar, String mod) {
		matricula = mat;
		marca = mar;
		modelo = mod;
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
	
	public void print(){
		System.out.println("Matricula: "+matricula+" marca "+marca+" modelo "+modelo);
	}
	
}
