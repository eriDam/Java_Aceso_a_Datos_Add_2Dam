public class Principal {

	public static void main(String[] args) {

		// Creo unos arrays que contienen las reparaciones de los vehículos
		
		String arrayV1[] = new String[] {"Cambio de foco","Chapa y pintura","Cambio de luna"};
		String arrayV2[] = new String[] {"Cambio de ruedas","cambio motor","Pintura"};
		String arrayV3[] = new String[] {"Instalación radio","Alerón deportivo","Escapes cromados"};
		String arrayV4[] = new String[] {"Cambio de aceite","Chapa y pintura","Cambio de luna"};
		String arrayV5[] = new String[] {"Cambio motor","Sustitución correa","Calibrado de ruedas"};
		String arrayV6[] = new String[] {"Cambio techo descapotable","Mantenimiento de lujo"};
		
		// Creo unos cuantos vehículos
		Vehiculo v1 = new Vehiculo("0065DFR", "Mazda", "3", 2003, arrayV1);
		Vehiculo v2 = new Vehiculo("0001DFR", "Citroen", "C3", 2004, arrayV2);
		Vehiculo v3 = new Vehiculo("0002FFR", "Ford", "Mondeo", 2005, arrayV3);
		Vehiculo v4 = new Vehiculo("0003DFR", "Peugeot", "407", 2008, arrayV4);
		Vehiculo v5 = new Vehiculo("0004GFR", "Mercedes", "Clase C", 2013, arrayV5);
		Vehiculo v6 = new Vehiculo("0002DFR", "Porsche", "911", 2015, arrayV6);

		// Creo unas cuantas personas
		Persona p1 = new Persona("Pepe", 25, "50667847T", v1);
		Persona p2 = new Persona("Juanmi", 26, "12345678A", v2);
		Persona p3 = new Persona("Oscar", 27, "07985302R", v3);
		Persona p4 = new Persona("Jose", 28, "12345678B", v4);
		Persona p5 = new Persona("Salva", 29, "12345678C", v5);

		// Instancio la clase GestionPersistencia para usar sus métodos
		GestionPersistencia gp = new GestionPersistencia();

		// Inserto los vehiculos creados anteriormente
		gp.insertarVehiculo(v1);
		gp.insertarVehiculo(v2);
		gp.insertarVehiculo(v3);
		gp.insertarVehiculo(v4);
		gp.insertarVehiculo(v5);

		// Inserto las personas creadas anteriormente
		gp.insertarPersona(p1);
		gp.insertarPersona(p2);
		gp.insertarPersona(p3);
		gp.insertarPersona(p4);
		gp.insertarPersona(p5);

		// Hago unas pruebas. Los métodos están comentados en la clase original
		gp.recuperarPersonaPorNombre("Oscar");
		gp.recuperarPersonaPorInicial("J");
		gp.recuperarPersonaPorDNI("50667847T");
		gp.recuperarPersonaPorEdad(26, 28);

		// Pruebas de la tarea 4B. Los métodos están comentados en la clase
		// original
		gp.recuperarVehiculoPorMatricula("0001DFR");
		gp.actualizarModeloVehiculo("0001DFR", "C4 Cactus");
		// Para que se vea que el vehículo se ha modificado veulvo a recuperarlo
		// con el método recuperarVehiculoPorMatricula
		gp.recuperarVehiculoPorMatricula("0001DFR");
		gp.actualizarVehiculoPersona("07985302R", v1);
		gp.recuperarPersonaPorNombre("Oscar");
		gp.borrarVehiculoPorMatricula("0002DFR");
		gp.borrarPersonaPorDni("07985302R");
		
		// Pruebas de la tarea 4C. Los métodos están comentados en la clase
		// original
		gp.recuperarVehiculoPorModeloQbe("C4 Cactus");
		gp.recuperarVehiculoPorMatriculaQbe("0001DFR");
		gp.recuperarVehiculosPorAnyo(2003, 2011);
		System.err.println("=================================================================================================================================");
		gp.recuperarVehiculosAvanzado();
	}

}
