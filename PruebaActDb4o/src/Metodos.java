/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
/**
 *
 * @author hugo
 */
public class Metodos {
     /**
     * Permite cerrar la conexion a la base de datos que se esta utilizando
     * @param baseDatos el ObjectContainer de la base de datos
     */
    public static void cerrarConexion(ObjectContainer baseDatos){
        try{
            baseDatos.close();
        }catch(Exception e){System.out.println("error al cerrar la conexion");}
    }
    
    /**
     * Permite almacenar un Ponente en la base de datos
     * @param baseDatos el objeto que representa la base de datos en la que se almacenara el ponente
     * @param ponente el ponente que se desea almacear en la base de datos
     */
    public static void almacenarPonente(ObjectContainer baseDatos, Ponente ponente){
    try{
        baseDatos.store(ponente);
        System.out.println("Se ha almacenado correctamente el ponente");
    }catch(Exception e){System.out.println("Se ha porducido un error en la insercion");}
    }
    
      /**
     * Imprime por pantalla el resultado de una consulta sin importar el metodo
     * de consulta
     *
     * @param resultado el objeto en el que se ha almacenado el resultado de la
     * consulta
     */
    public static void imprimirResultadoConsulta(ObjectSet resultado) {
        System.out.println("Recuperados " + resultado.size() + " Objetos");
        while (resultado.hasNext()) {
            System.out.println(resultado.next());
        }

    }
    
        /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un dni e
     * imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el dni del ponente que se quiere buscar
     */
    public static void consultarQBEPonentesDni(ObjectContainer baseDatos, String dni) {
        Ponente ponente = new Ponente(dni, null, null, 0.0f);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
      /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un
     * nombre e imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se desea encontrar
     */
    public static void consultarQBEPonentesNombre(ObjectContainer baseDatos, String nombre) {
        Ponente ponente = new Ponente(null, nombre, null, 0.0f);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
     /**
     * Permite hacer una consulta mediante Query-by-Example a partir de un cache
     * imprimirla por pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cache el cache del ponente que se desea encontrar
     */
    public static void consultarQBEPonentesCache(ObjectContainer baseDatos, float cache) {
        Ponente ponente = new Ponente(null, null, null, cache);
        ObjectSet resultado = baseDatos.queryByExample(ponente);
        imprimirResultadoConsulta(resultado);

    }
    
    /**
     * Permite realizar una consulta Nativa a partir del dni e imprimirla en
     * pantalla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param dni el dni del ponente que se desea encontrar
     */
    public static void consultarNatPonentesDni(ObjectContainer baseDatos, final String dni) {
        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getDNI().equalsIgnoreCase(dni);
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }
    /**
     * Permite realizar una consulta Nativa y obtener a todos los ponentes que
     * tengan un determinado cache
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cache el cacha que ha de tener el ponente
     */
    public static void consulrtarNatPonentesCacheIgualA(ObjectContainer baseDatos, final float cache) {

        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getCache() == cache;
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }

    /**
     * Permite realizar una consulta Nativa y obtener todos los ponentes que
     * tengan un cache superior al indicado
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param cacheBase el cache base de los ponentes
     */
    public static void consultarNatPonentesCacheSuperiorA(ObjectContainer baseDatos, final float cacheBase) {

        List res = baseDatos.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente ponente) {
                return ponente.getCache() >= cacheBase;
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        imprimirResultadoConsulta((ObjectSet) res);
    }
    
    /**
     * Permite realizar una consulta SODA cuyo resultado sean todos los ponentes
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAponentes(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);//declara las restricciones
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }

    /**
     * Permite realizar una consulta SODA cuyo resultado sea el ponente cuyo
     * nombre se ha introducido por parametro
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se quiere recuperar
     */
   public static void consultaSODAPonentesNombre(ObjectContainer baseDatos, String nombre) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("nombre").constrain(nombre);
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);


    } 
   
     /**
     * Permite realizar una consulta SODA que recupera los ponentes cuyo cache
     * esta entre los indicados por parametro
     *
     * @param baseDatosla base de datos desde la que se va a operar
     * @param cacheInferior el cache que marca limite inferior
     * @param CacheSuperior el cache que marcha el limite superior
     */
    public static void consultaSODAPonentesCacheEntre(ObjectContainer baseDatos, float cacheInferior, float CacheSuperior) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el primer constraint diciendole que el cache ha de ser menor del superior
        Constraint constraint = query.descend("cache").constrain(CacheSuperior).smaller();
        //se enlazan las dos restricciones a aplicar
        query.descend("cache").constrain(cacheInferior).greater().and(constraint);
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }

    /**
     * Consulta SODA que permite recuperar ordenados por cache todos los
     * ponentes
     *
     * @param baseDatos la base de datos desde la que se va a operar
     */
    public static void consultaSODAPonentesOrdenadosCache(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        query.descend("cache").orderDescending();
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);
    }
    
        /**
     * Permite almacenar una charla en la base de datos
     *
     * @param baseDatos el objeto que representa la base de datos en la que se
     * almacenara el ponente
     * @param charla la charla que se desea almacear en la base de datos
     */
    public static void almacenarCharla(ObjectContainer baseDatos, Charla charla) {
        try {
            baseDatos.store(charla);
            System.out.println("Se ha almacenado correctamente la charla");
        } catch (Exception e) {
            System.out.println("Se ha porducido un error en la insercion");
        }
    }
    
       /**
     * Permite actualizar el cache que va a percibir un determinado ponente, que
     * seleccionaremos por su nombre
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombrePonente el nombre del ponente cuyo cache queremos modificar
     * @param nuevoCache el nuevo cache que va a tener el ponente.
     */
    public static void actualizarCachePonente(ObjectContainer baseDatos, String nombrePonente, float nuevoCache) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("nombre").constrain(nombrePonente);
        ObjectSet resultado = query.execute();
        Ponente p = (Ponente) resultado.get(0);
        p.setCache(nuevoCache);
        baseDatos.store(p);

    }

 /**
     * Permitte actualizar el email de un ponente, seleccionando este por su
     * nombre
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombrePonente el nombre del ponente cuyo email queremos modificar
     * @param nuevoEmail el nuevo email que queremos que tenga el ponente
     */
    public static void actualizarEmailPonente(ObjectContainer baseDatos, String nombrePonente, String nuevoEmail) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("email").constrain(nombrePonente);
        ObjectSet resultado = query.execute();
        Ponente p = (Ponente) resultado.get(0);
        p.setEmail(nuevoEmail);
        baseDatos.store(p);
    }

    /**
     * Permite actualizr el titulo de una charla
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param tituloViejoCharla el titulo que tiene la charla en la base de
     * datos
     * @param tituloNuevoCharla el nuevo titulo que va a tener la charla
     */
    public static void actualizarTituloCharla(ObjectContainer baseDatos, String tituloViejoCharla, String tituloNuevoCharla) {
        Query query = baseDatos.query();
        query.constrain(Charla.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("titulo").constrain(tituloViejoCharla);
        ObjectSet resultado = query.execute();
        Charla c = (Charla) resultado.get(0);
        c.setTitulo(tituloNuevoCharla);
        baseDatos.store(c);

    }

    /**
     * Permite actualizar el ponente que va a realizar una determinada charla
     * que seleccionaremos por su titulo
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param tituloCharla el titulo de la charla cuyo ponente queremos
     * modificar
     * @param ponenteNuevo el nue ponente que va a realizar la charla
     */
    public static void actualizarPonenteCharla(ObjectContainer baseDatos, String tituloCharla, Ponente ponenteNuevo) {
        Query query = baseDatos.query();
        query.constrain(Charla.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("titulo").constrain(tituloCharla);
        ObjectSet resultado = query.execute();
        Charla c = (Charla) resultado.get(0);
        c.setPonente(ponenteNuevo);
        baseDatos.store(c);
    }
    
    /**
     * Consulta SODA que devuelve las charlas que tengan el titulo pasado por
     * parametro
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se quiere recuperar
     */
    public static void consultaSODACharlaTitulo(ObjectContainer baseDatos, String titulo) {
        Query query = baseDatos.query();
        query.constrain(Charla.class);
        //creamos el constraint diciendo que el campo donde lo tiene que aplicar es nombre y la restricion es el parametro nombre
        Constraint constraint = query.descend("titulo").constrain(titulo);
        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);


    }

    /**
     * Permite realizar una consulta SODA cuyo resultado sean todas las charlas
     * existentes
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param titulo el titulo de la charla que se quiere recuperar los ponentes
     */
    public static void consultaSODACharla(ObjectContainer baseDatos) {
        Query query = baseDatos.query();
        query.constrain(Charla.class);

        ObjectSet resultado = query.execute();
        imprimirResultadoConsulta(resultado);


    }
    
     /**
     * Permite borrar un ponente de la base de datos
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param nombre el nombre del ponente que se va a borrar
     */
    public static void borrarPonentePorNombre(ObjectContainer baseDatos, String nombre) {
        Query query = baseDatos.query();
        query.constrain(Ponente.class);
        query.descend("nombre").constrain(nombre);
        ObjectSet resul = query.execute();
        while (resul.hasNext()) {
            Ponente ponente = (Ponente) resul.next();
            System.out.println("Eliminando: " + ponente);
            baseDatos.delete(ponente);
        }
    }
    
    
     /**
     * Permite borrar una charla de la base de datos a partir de su titulo
     *
     * @param baseDatos la base de datos desde la que se va a operar
     * @param titulo el titulo de la charla que se quiere eliminar
     */
    public static void borrarCharlaporTitulo(ObjectContainer baseDatos, String titulo) {
        Query query = baseDatos.query(); //declaración de un objeto query(). 
        query.constrain(Charla.class);//establece la clase a la que se aplicará la restricción
        query.descend("titulo").constrain(titulo);//establece la restricción de búsqueda
        ObjectSet resul = query.execute();//ejecuta consulta con restricción búsqueda
        while (resul.hasNext()) { //bucle que recupera los objetos charla y elimina de la BDOO
            Charla charla = (Charla) resul.next();
            System.out.println("Eliminando: " + charla);
            baseDatos.delete(charla);
        }
    }
    
   
}
