import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


public class GestionDB4O {
	static ObjectContainer bbdd;
	
	public void inicializar(){
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().objectClass(Persona.class).cascadeOnDelete(true);	
		bbdd = Db4oEmbedded.openFile(conf,"prueba.db4o");
	}
	
}
