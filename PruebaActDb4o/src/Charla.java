
public class Charla {
  private String titulo;
  private float duracion;
  private Ponente ponente;
  
  //constructores
  /**
   * Crea un objeto de la clase Charla
   * @param titulo el titulo que llevará la charla
   * @param duracion la duración que tendrá la charla
   * @param ponente  el ponente que impartirá la charla
   */
  public Charla(String titulo, float duracion, Ponente ponente) {
    this.titulo = titulo;
    this.ponente = ponente;
    this.duracion=duracion;
  }
  /**
   * Crea un objeto de la clase Charla
   * @param titulo el titulo que llevará la charla
   * @param duracion la duracion que tendrá la charla
   */
  public Charla(String titulo,float duracion){
      this(titulo,duracion,null);
  }
  /**
   * Permite obtener el ponente que realizará la charla
   * @return el ponente que realizará la charla
   */
  public Ponente getPonente() {
    return ponente;
  }
/**
 * Permite establecer el ponente que realizará la charla
 * @param ponente el ponente que realizará la charla
 */
  public void setPonente(Ponente ponente) {
    this.ponente = ponente;
  }
  /**
   * Permite establecer el titulo que tendrá la charla
   * @param titulo el titulo que tendrá la charla
   */
  public void setTitulo(String titulo){
      this.titulo=titulo;
  }
  /**
   * Permite obtener el titulo que tendrá la charla
   * @return el titulo que tendrá la charla
   */
  public String getTitulo() {
    return titulo;
  }
  /**
   * Permite obtener la duración que tendrá la charla
   * @return la duración que tendrá la charla
   */
  public float getDuracion() {
    return duracion;
  }
  /**
   * Permite establecer la duracion que tendrá la charla
   * @param duracion la duracion que tendrá la charla
   */
  public void setDuracion(float duracion) {
    this.duracion = duracion;
  }
  
  /**
   * Devuelve todos los atributos del objeto representados en un String
   * @return los atributos del objeto en forma de String
   */
  @Override
  public String toString() {
    return "Charla: " + titulo + ", " + duracion + " horas.  PONENTE: " +ponente ;
  }  
}
