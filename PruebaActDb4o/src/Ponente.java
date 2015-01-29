public class Ponente {
     
    private String dni;
    private String nombre;
    private String email;
    private float cache;
    
    //constructores
    public Ponente(){}
/**
 * Crea un objeto de la clase Ponente
 * @param dni el DNI del ponente
 * @param nombre el nombre del ponente
 * @param email el correo electronico del ponente
 * @param cache la retribucion economica que recibira el ponente
 */    
    public Ponente (String dni, String nombre, String email, Float cache){
        this.dni=dni;
        this.nombre=nombre;
        this.email=email;
        if(cache>=0){
        this.cache=cache;
        }else{throw new IllegalArgumentException();}
    
    }
    /**
     * Crea un objeto de la clase Ponenete
     * @param dni el DNI del ponente
     * @param nombre el nombre del ponente
     * @param email  el correo electronico del ponente
     */
    public Ponente(String dni, String nombre, String email){
        this(dni,nombre,email,0.0f);
    }
    
    /**
     * Crea un objeto de la clase Ponente
     * @param dni el DNI del ponente
     * @param nombre el nombre del ponente
     */
    public Ponente(String dni, String nombre){
        this(dni,nombre,"",0.0f);
    }
    
    /**
     * Permite modificar el DNI del ponente
     * @param dni el DNI del ponente
     */
    public void setDNI(String dni){
        this.dni=dni;
    }
    /**
     * Devuelve el DNI del ponente
     * @return el DNI del ponente
     */
     public String getDNI() {
    return this.dni;
  }
     /**
      * Permite modificar el nombre del ponente
      * @param nombre el nombre del ponente
      */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  /**
   * Devuelve el nombre del ponente
   * @return el nombre del ponente
   */
  public String getNombre() {
    return this.nombre;
  }
  /**
   * Permite cambiar el correo electronico del ponente
   * @param email el correo electronico del poniente
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * Devuelve el correo electronico del ponente
   * @return el email del ponente
   */
  public String getEmail() {
    return this.email;
  }
  /**
   * Permite modificar los honorarios del ponente
   * @param cache el cache del ponente
   */
  public void setCache(float cache) {
    this.cache = cache;
  }
  /**
   * Devuelve el cache del ponente
   * @return el cache del ponente
   */
  public float getCache() {
    return this.cache;
  }
  
  /**
   * Devuelve todos los atributos(dni, nombre, email y cache) del ponente en forma de String
   * @return los atributos del ponente
   */
    @Override
  public String toString(){
       if (this.cache != 0) {
            return this.dni+" "+this.nombre+" "+this.email+" Caché:"+this.cache;
       }
       else{
      return this.dni+" "+this.nombre +" "+this.email;
    }
  }
   
}
