package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR2 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INTRUMENTACIONMINIMA=1;
	public final static int INTRUMENTACIONBASICA=2;
	public final static int ALGUNASREDES=3;
	public final static int BUENASREDES=4;
	public final static int AMPLIACOBERTURA=5;
			
	public static String INTRUMENTACIONMINIMA_TXT= "Instrumentaci\u00F3n m\u00EDnima o deficiente de algunos fen\u00F3menos importantes.";
	public static String INTRUMENTACIONBASICA_TXT= "Redes b\u00E1sicas de instrumentaci\u00F3n con problemas de actualizaci\u00F3n tecnol\u00F3gica y de mantenimiento continuo.";
	public static String ALGUNASREDES_TXT= "Algunas redes con tecnolog\u00EDa avanzada a nivel nacional o de zonas puntuales; pron\u00F3sticos mejorados y protocolos de informaci\u00F3n establecidos para las principales amenazas.";
	public static String BUENASREDES_TXT= "Buena y progresiva cobertura de la instrumentaci\u00F3n a nivel nacional, investigaci\u00F3n avanzada de la mayor\u00EDa de fen\u00F3menos y algunos sistemas de alerta autom\u00E1ticos funcionando.";
	public static String AMPLIACOBERTURA_TXT= "Amplia cobertura de redes de estaciones y sensores para todo tipo de amenaza en todo el territorio, an\u00E1lisis permanente y oportuno de informaci\u00F3n y sistemas de alerta autom\u00E1ticos funcionando continuamente a nivel local, regional y nacional.";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INTRUMENTACIONMINIMA_TXT;
		case 2: return INTRUMENTACIONBASICA_TXT;
		case 3: return ALGUNASREDES_TXT;
		case 4: return BUENASREDES_TXT;
		case 5: return AMPLIACOBERTURA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INTRUMENTACIONMINIMA, getTXT(INTRUMENTACIONMINIMA)));
		res.add(new ItemConstante(INTRUMENTACIONBASICA, getTXT(INTRUMENTACIONBASICA)));
		res.add(new ItemConstante(ALGUNASREDES, getTXT(ALGUNASREDES)));
		res.add(new ItemConstante(BUENASREDES, getTXT(BUENASREDES)));
		res.add(new ItemConstante(AMPLIACOBERTURA, getTXT(AMPLIACOBERTURA)));		
		return res;
	}
}
