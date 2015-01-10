public class Vehiculo {

	private String matricula;
	private String marca = null;
	private String modelo = null;
	private Persona propietario;

	public Vehiculo() {
	}

	public Vehiculo(String mat, String mar, String mod, Persona prop) {
		this.matricula = mat;
		this.marca = mar;
		this.modelo = mod;
		this.propietario = prop;
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
		System.out.println("Matricula: "+matricula+" marca: "+marca+" modelo: "+modelo);
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca
				+ ", modelo=" + modelo + ", propietario=" + propietario + "]";
	}
	
}
