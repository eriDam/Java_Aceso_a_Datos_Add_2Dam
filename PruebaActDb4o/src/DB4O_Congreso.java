/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

/**
 *
 * @author hugo
 */
public class DB4O_Congreso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        //es necesario realizar los 2 imports anteriores, ya que si no es ponible que automaticamente no los haga produciendose un error.
        ObjectContainer baseDatos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "congreso.db4o");
        */
        //PARA BORRAR EN CASCADA
        //Se indica la nueva configuración de conexión, con borrado en cascada
    EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
    config.common().objectClass(Charla.class).cascadeOnDelete(true);
//Se abre la conexión a la base de objetos congreso.db4o
    ObjectContainer baseDatos = Db4oEmbedded.openFile(config, "congreso.db4o");
        
        //creamos un ponente
        Ponente ponente1 = new Ponente("123654278F", "ponente1", "email@email.com", 10.0f);
        //creamos una serie de ponentes
        Ponente p2 = new Ponente("123456789F", "ponente2", "email2@email.com", 11.0f);
        Ponente p3 = new Ponente("223456789F", "ponente3", "email3@email.com", 12.0f);
        Ponente p4 = new Ponente("333456789F", "ponente4", "email4@email.com", 15.0f);
        Ponente p5 = new Ponente("444456789F", "ponente5", "email5@email.com", 9.0f);
        Ponente p6 = new Ponente("555556789F", "ponente6", "email6@email.com", 25.0f);
        Ponente p7 = new Ponente("666666789F", "ponente7", "email7@email.com", 250.0f);

        Charla c1 = new Charla("charla1", 1f, p2);
        Charla c2 = new Charla("charla2", 3f, p7);
        Charla c3 = new Charla("charla3", 2f);
        c3.setPonente(new Ponente("99999F", "ponente8", "email8@email.com", 9.0f));
        Charla c4 = new Charla("charla4", 5f, p3);
        Charla c5 = new Charla("charla5", 6f, p2);
        try {
            /*
             //Almacena todas las charlas
             Metodos.almacenarCharla(baseDatos, c1);
             Metodos.almacenarCharla(baseDatos, c2);
             Metodos.almacenarCharla(baseDatos, c3);
             Metodos.almacenarCharla(baseDatos, c4);
             Metodos.almacenarCharla(baseDatos, c5);
             */
            /*
            //EJEMPLOS DE ACTUALIZACION
            System.out.println("Actualizar cache de un ponente");
            Metodos.actualizarCachePonente(baseDatos, "ponente3", 987.0f);
            Metodos.consultaSODAPonentesNombre(baseDatos, "ponente3");
            System.out.println("**************************************");

            System.out.println("actualizar cache de un ponente de esta sesion");
            Ponente ejActualizar = new Ponente("dni", "nombreDelPonente", "mail", 0.0f);
            Metodos.almacenarPonente(baseDatos, ejActualizar);
            Metodos.consultaSODAPonentesNombre(baseDatos, "nombreDelPonente");
            System.out.println("actualizo...");
            Metodos.actualizarCachePonente(baseDatos, "nombreDelPonente", 1000f);
            System.out.println("compruebo...");
            Metodos.consultaSODAPonentesNombre(baseDatos, "nombreDelPonente");
            
            System.out.println("**************************************");
            System.out.println("Actualizar titulo de charla");
            Metodos.actualizarTituloCharla(baseDatos, "charla1", "charla1.1");
            Metodos.consultaSODACharla(baseDatos);
            System.out.println("**************************************");
            
             System.out.println("Actualizar ponente de charla");
            Metodos.actualizarPonenteCharla(baseDatos,"charla3", ejActualizar);
            Metodos.consultaSODACharlaTitulo(baseDatos, "charla3");
            */
            /*
             //EJEMPLOS DE BORRADO
            System.out.println("Borrado de los ponente1");
            Metodos.borrarPonentePorNombre(baseDatos, "ponente1");
            Metodos.consultaSODAponentes(baseDatos);
            System.out.println("**************************************");
            
            
            System.out.println("Borrado de la charla1");
            Metodos.borrarCharlaporTitulo(baseDatos,"charla1.1");
            Metodos.consultaSODACharla(baseDatos);
            System.out.println("**************************************");
            
            System.out.println("Borrado de la charla2 en cascada");
            Metodos.borrarCharlaporTitulo(baseDatos,"charla2");*/
            Metodos.consultaSODACharla(baseDatos);
            Metodos.consultaSODAponentes(baseDatos);
        } finally {
            Metodos.cerrarConexion(baseDatos);
        }
    }
}

