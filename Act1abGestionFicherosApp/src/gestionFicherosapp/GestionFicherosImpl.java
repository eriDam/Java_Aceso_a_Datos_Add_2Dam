//Erika Cebria Actividad 1 clase File Java
//Subida Git
//Actividad 1a entregada 14-09
//Actividad 1b entregada 20-09

package gestionFicherosapp;

import java.io.File;
import java.io.IOException;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

//Esta clase implementara la interfaz que tenemos definida en una de las librerias 

public class GestionFicherosImpl implements GestionFicheros{
	//Vinculamos el objeto de tipo file que se vincule a la carpeta activa que tenemos
	private File carpetaDeTrabajo = null;
	//Definimos otra variable de clase que sera una matriz de objetos
	private Object[][] contenido;
	
	private int filas = 0;
	private int columnas = 3;
	//Añadimos estos atributos para indicar a la interfaz la vista y el tipo de orden
	private FormatoVistas formatoVistas=FormatoVistas.NOMBRES;
	private TipoOrden ordenado=TipoOrden.DESORDENADO;
	
	
	//constructor de la clase
	public GestionFicherosImpl(){
		//Se inicializa 
		carpetaDeTrabajo = File.listRoots()[0];
		//Llamamos al método actualiza cada vez q haya cambios, por que necesitamos refrescar los datos
		actualiza();
	}
	private void actualiza() {
		// Este metodo actualizará el contenido cada vez q haya cambios
			//Imprimimos un mesaje para ver cual es la carpeta
			System.out.println("La carpeta es: "+carpetaDeTrabajo);
			//Accedemos a la carpeta de trabajo que tengamos en ese momento y obtiene los nombres con el metodo list
			String[] ficheros = carpetaDeTrabajo.list();
			//Calcula el numero de filas necesario
			filas = ficheros.length / columnas;
			if (filas * columnas < ficheros.length){
				filas++;//Si hay resto necesitamos una fila mas
			}
		
		//Dimensionar la matriz contenido según los resultados
			contenido = new String[filas][columnas];
			//Rellena contenido con los nombres obtenidos
			for (int i = 0; i < columnas; i++){
				for (int j = 0; j < filas; j++){
					int ind = j * columnas +i;
					if (ind < ficheros.length){
						contenido[j][i] = ficheros[ind];
					}else{
						contenido[j][i] = "";
					}
				}
			}
		}
	@Override
	public void arriba() {
		System.out.println("Subiendo 1 nivel ");
		//Controlamos que no nos devuelva null, si lo hace es q no hay mas arriba del directorio, 
		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();//nos devuelve la ruta del directorio
			//si es distinto de null actualizará
			actualiza();
		}

		
	}
	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzará una excepción
		if(!carpetaDeTrabajo.canWrite()){
			throw new GestionFicherosException("NO se puede escribir");
		}
		//que no exista -> lanzará una excepción
		if(!carpetaDeTrabajo.exists()){
			throw new GestionFicherosException("Ya existe");
		}
		//crear la carpeta -> lanzará una excepción
		if(!file.mkdir()){
			throw new GestionFicherosException("Tu carpeta no ha sido creada");
		}
		
		actualiza();
		
	}
	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		// Creo un objeto file pasandole los parametros de carpetaTrabajo, arg0
		File file = new File(carpetaDeTrabajo,arg0);
		//capturamos la excepción mediante try catch
		try {//Con try intenta ejecutar el codigo
			if(!file.createNewFile()){
				//En caso de que exista el archivo, saltará una exccepcion con el siguiente mensaje 
				throw new GestionFicherosException("Tu archivo no ha sido creado, por que ya existe");
			}
		} catch (IOException e) {//En caso de no poderlo ejecutar saltará la exccepción.
			e.printStackTrace();
		}
		actualiza();
	}
	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		// Creo un objeto file pasandole los parametros de carpetaTrabajo, arg0
				File file = new File(carpetaDeTrabajo,arg0);
				if(!file.exists()){
					throw new GestionFicherosException("Tu archivo no existe");
				}
				if(!file.delete()){
					throw new GestionFicherosException("No ha sido posible eliminar el archivo");
					}
				actualiza();
	}
	
	@Override
	//Con este método haremos que al hacer doble click nos permita cambiar la carpeta de trabajo
	
	public void entraA(String arg0) throws GestionFicherosException {//Recibe un String que es la carpeta a la q queremos acceder con el nombre relativo
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignación de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

		
	}
	@Override
	public int getColumnas() {
		// 	Modificamos el metodo para que nos devuelva los atributos definidos de columnas
		return columnas;
	}
	@Override
	public Object[][] getContenido() {
		// Modificamos el acceso de getContenido que es el que devuelve el contenido almacenado 
		return contenido;
	}
	@Override
	public String getDireccionCarpeta() {
		// Utilizamos getAbsolutePath para que nos devuelva la ruta absoluta
		return carpetaDeTrabajo.getAbsolutePath();
	}
	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		//getFreeSpace() para que nos devuelva el espacio disponible
		//return carpetaDeTrabajo.getFreeSpace(); 
		//MIRAR *********Me dice que lo cambie a long y si lo hago me pide que lo cambie a String***
		return null;
	}
	
	@Override
	public int getFilas() {
		//Modificamos el metodo para que nos devuelva los atributos definidos de filas
		return filas;
	}
	@Override
	public FormatoVistas getFormatoContenido() {
		// Debemos implementar el metodo para que nos devuelva el atributo que acabamos de definir
		return formatoVistas;
	}
	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		//Utilizamos StringBuilder para concatenar strings de forma mas eficiente
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		
		// Se comprueba  que el nombre exista
				if (!file.exists()) {
					throw new GestionFicherosException("Error has indicado un nombre: "
							+ file.getAbsolutePath()
							+ " y no existe");
				}
				// Se comprueba que se tenga permiso para leer carpeta
				if (!file.canRead()) {
					throw new GestionFicherosException("DENEGADO. No se puede acceder a "
							+ file.getAbsolutePath() + ". No hay permiso");
				}
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");//String que queremos añadir
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);//Lo que nos llega por el parametro 0 es el nombre
		strBuilder.append("\n\n");
		
		//Tipo: fichero o directorio
		if(file.isDirectory()){
			strBuilder.append((arg0)+"  Es un directorio.\n\n");	
		}else{
			strBuilder.append((arg0)+"  Es un archivo.\n\n");
		}
	
		
		//Ubicación
		strBuilder.append((arg0)+" Se encuentra en: "+file.getAbsolutePath()+"\n\n");
		
		//Fecha de última modificación
		strBuilder.append("La última modificación en "+(arg0)+" fue: "+file.lastModified()+"\n\n");//(00:00:00 GMT, January 1, 1970)No me saca este formato
		
		//Si es un fichero oculto o no
		if(file.isHidden()){
			strBuilder.append((arg0)+" Es un fichero oculto.\n\n");
		}else{
			strBuilder.append((arg0)+" No es un fichero oculto.\n\n");
		}
		
		//Si es directorio: Espacio libre, espacio disponible, espacio total
		//bytes
		//Creo un condicional para que compruebe si es un directorio, si lo es ejecutará el código
		if(file.isDirectory()){
			strBuilder.append("El espacio libre es : "+file.getFreeSpace()+" bytes");
			strBuilder.append("\n");
			strBuilder.append("El espacio disponible es : "+file.getUsableSpace()+" bytes");
			strBuilder.append("\n");
			strBuilder.append("El espacio total es : "+file.getTotalSpace()+" bytes");
			//en caso contrario sacará un mensaje en la ventana donde muestro el String informando del error. 	
		}else{
			strBuilder.append(strBuilder.append(arg0)+". No es un directorio, con lo cual \nno hay espacio libre que mostrar\n\n");
		}
		
		
		return strBuilder.toString();
		
	}
	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getNombreCarpeta() {
		// TODO Auto-generated method stub
		return carpetaDeTrabajo.getName();
	}
	@Override
	public TipoOrden getOrdenado() {
		// modificamos este metodo para que siga el orden que le hemos indicado
		return ordenado;
	}
	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	//Este método va a recibir por parámetro el nombre de la carpeta que tenemos y el nuevo que le vamos a asignar
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		// Creamos un objeto 
		File file = new File(carpetaDeTrabajo,arg0);
		File file2 =new File(carpetaDeTrabajo,arg1);
		if(!file.canWrite()){
			throw new GestionFicherosException("No se puede escribir  el directorio o fichero :( ");
		}
		if(!file.renameTo(file2)){
			throw new GestionFicherosException("No se puede renombrar el directorio o fichero :( ");
		}
		actualiza();
	}
	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setColumnas(int arg0) {
		// Las columnas se pueden modificar de 3 a otro valor
		columnas = arg0;
		
	}
	
	@Override
	/*Este método se encarga de recibir un String que hace referencia a una ruta y el objetivo es
	 * poner como carpeta de trabajo*/
	
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);//creamos un nuevo fichero con la ruta que se nos pasa

		// Para gestionar las excepciones, se controla que la dirección exista y sea directorio, mediante la llamada al metodo isDirectory
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// La llamada al metodo canRead, controla que haya permisos para leer la carpeta, en caso coontrario lanza la exccepcion
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// Al actualizar la carpeta hay que actualizar tambien el contenido
		actualiza();
		
	}
	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
