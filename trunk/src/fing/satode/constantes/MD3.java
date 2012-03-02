package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD3 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int DOTACIONBASICA=1;
	public final static int CENTROSRESERVAS=2;
	public final static int OPERACIONES=3;
	public final static int OPERACIONESSISTEMATIZADOS=4;
	public final static int REDESDEAPOYO=5;
	
			
	public static String DOTACIONBASICA_TXT= "Dotaci\u00F3n b\u00E1sica e inventario de los recursos de s\u00F3lo las entidades operativas y comisiones de emergencia.";
	public static String CENTROSRESERVAS_TXT= "Centros de reservas y de equipos especializados de emergencia a nivel nacional y en algunas ciudades; inventarios de recursos de otras entidades p\u00FAblicas y privadas.";
	public static String OPERACIONES_TXT= "Centros de Operaciones de Emergencia bien dotados con equipos de comunicaciones y adecuados sistemas de registro; equipamiento especializado y centros de reservas en varias ciudades.";
	public static String OPERACIONESSISTEMATIZADOS_TXT= "Centros de Operaciones de Emergencia bien dotados y sistematizados en la mayor\u00EDa de ciudades; progresiva dotaci\u00F3n complementaria de las entidades operativas.";
	public static String REDESDEAPOYO_TXT= "Redes de apoyo interinstitucional, de centros de reservas y entre Centros de Operaciones de Emergencia funcionando permanentemente; amplias facilidades de comunicaciones, transporte y abastecimiento en caso de emergencia.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return DOTACIONBASICA_TXT;
		case 2: return CENTROSRESERVAS_TXT;
		case 3: return OPERACIONES_TXT;
		case 4: return OPERACIONESSISTEMATIZADOS_TXT;
		case 5: return REDESDEAPOYO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(DOTACIONBASICA, getTXT(DOTACIONBASICA)));
		res.add(new ItemConstante(CENTROSRESERVAS, getTXT(CENTROSRESERVAS)));
		res.add(new ItemConstante(OPERACIONES, getTXT(OPERACIONES)));
		res.add(new ItemConstante(OPERACIONESSISTEMATIZADOS, getTXT(OPERACIONESSISTEMATIZADOS)));
		res.add(new ItemConstante(REDESDEAPOYO, getTXT(REDESDEAPOYO)));
		
		return res;
	}
}
