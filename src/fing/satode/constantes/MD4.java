package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD4 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int ALGUNOSSIMULACROS=1;
	public final static int EJERCICIOSESPORADISOCOS=2;
	public final static int SIMULACIONESESCRITORIO=3;
	public final static int SIMULACIONCONPERSONAS=4;
	public final static int PLANESEMERGENCIA=5;
	
			
	public static String ALGUNOSSIMULACROS_TXT= "Algunos simulacros institucionales internos y en conjunto con otras entidades operativas en algunas ciudades.";
	public static String EJERCICIOSESPORADISOCOS_TXT= "Ejercicios espor\u00E1dicos de simulaci\u00F3n de situaciones emergencia y respuesta interinstitucional con todas las entidades operativas.";
	public static String SIMULACIONESESCRITORIO_TXT= "Simulaciones de escritorio y simulacros con la participaci\u00F3n adicional de las entidades de servicios públicos y de la administraci\u00F3n local en varias ciudades.";
	public static String SIMULACIONCONPERSONAS_TXT= "Coordinaci\u00F3n de simulaciones y simulacros con la participaci\u00F3n de personas de la comunidad, el sector privado y los medios de comunicaci\u00F3n a nivel nacional y en algunas ciudades.";
	public static String PLANESEMERGENCIA_TXT= "Prueba de planes de emergencia y contingencia y actualizaci\u00F3n de procedimientos operativos con base en ejercicios de simulaci\u00F3n y simulacros frecuentes en la mayor\u00EDa de ciudades.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return ALGUNOSSIMULACROS_TXT;
		case 2: return EJERCICIOSESPORADISOCOS_TXT;
		case 3: return SIMULACIONESESCRITORIO_TXT;
		case 4: return SIMULACIONCONPERSONAS_TXT;
		case 5: return PLANESEMERGENCIA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(ALGUNOSSIMULACROS, getTXT(ALGUNOSSIMULACROS)));
		res.add(new ItemConstante(EJERCICIOSESPORADISOCOS, getTXT(EJERCICIOSESPORADISOCOS)));
		res.add(new ItemConstante(SIMULACIONESESCRITORIO, getTXT(SIMULACIONESESCRITORIO)));
		res.add(new ItemConstante(SIMULACIONCONPERSONAS, getTXT(SIMULACIONCONPERSONAS)));
		res.add(new ItemConstante(PLANESEMERGENCIA, getTXT(PLANESEMERGENCIA)));
		
		return res;
	}
}
