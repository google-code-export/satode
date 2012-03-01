package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD1 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int PERSONALVOLUNTARIODIF=1;
	public final static int LEGISLACIONESPECIFICA=2;
	public final static int APRECIABLECONDINACION=3;
	public final static int COORDINACIONPERMANENTE=4;
	public final static int AVANZADAINTEGRACION=5;
	
		
	public static String PERSONALVOLUNTARIODIF_TXT= "Diferentes organismos atienden emergencias, sin mayores recursos y varios de ellos con s\u00F3lo personal voluntario.";
	public static String LEGISLACIONESPECIFICA_TXT= "Legislaci\u00F3n espec\u00EDfica define una estructura interinstitucional, roles de las entidades operativas y establece la coordinaci\u00F3n de comisiones de emergencia en todo el territorio.";
	public static String APRECIABLECONDINACION_TXT= "Apreciable coordinaci\u00F3n, en algunas ciudades, entre las entidades operativas en la preparaci\u00F3n conjunta, comunicaciones, búsqueda y rescate, red de urgencias y manejo de alojamientos temporales.";
	public static String COORDINACIONPERMANENTE_TXT= "Coordinaci\u00F3n permanente para responder en caso de emergencia entre las entidades operativas, de servicios públicos, las autoridades locales y organismos de la sociedad civil en la mayor\u00EDa de ciudades.";
	public static String AVANZADAINTEGRACION_TXT= "Avanzada integraci\u00F3n interinstitucional entre entidades públicas, privadas y comunitarias, con adecuados protocolos de coordinaci\u00F3n horizontal y vertical en todos los niveles territoriales.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return PERSONALVOLUNTARIODIF_TXT;
		case 2: return LEGISLACIONESPECIFICA_TXT;
		case 3: return APRECIABLECONDINACION_TXT;
		case 4: return COORDINACIONPERMANENTE_TXT;
		case 5: return AVANZADAINTEGRACION_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(PERSONALVOLUNTARIODIF, getTXT(PERSONALVOLUNTARIODIF)));
		res.add(new ItemConstante(LEGISLACIONESPECIFICA, getTXT(LEGISLACIONESPECIFICA)));
		res.add(new ItemConstante(APRECIABLECONDINACION, getTXT(APRECIABLECONDINACION)));
		res.add(new ItemConstante(COORDINACIONPERMANENTE, getTXT(COORDINACIONPERMANENTE)));
		res.add(new ItemConstante(AVANZADAINTEGRACION, getTXT(AVANZADAINTEGRACION)));
		
		return res;
	}
}
