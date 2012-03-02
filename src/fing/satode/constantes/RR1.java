package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR1 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int CONSIDERACIONALGUNOS=1;
	public final static int PROMULGACION=2;
	public final static int PROGRESIVAFORMULACION=3;
	public final static int AMPLIAFORMULACION=4;
	public final static int CONTROLGENERALIZADO=5;
	
	
	public static String CONSIDERACIONALGUNOS_TXT= "Consideraci\u00F3n de algunos elementos de identificaci\u00F3n de riesgos y protecci\u00F3n ambiental en la planificaci\u00F3n f\u00EDsica.";
	public static String PROMULGACION_TXT= "Promulgaci\u00F3n de legislaci\u00F3n nacional y de algunas regulaciones locales que consideran algunas amenazas como determinantes del ordenamiento territorial y planificaci\u00F3n del desarrollo.";
	public static String PROGRESIVAFORMULACION_TXT= "Progresiva formulaci\u00F3n de reglamentos de uso del suelo en varias ciudades que tienen en cuenta amenazas y riesgos; prescripciones de dise\u00F1o y construcci\u00F3n obligatorias con base en microzonificaciones.";
	public static String AMPLIAFORMULACION_TXT= "Amplia formulaci\u00F3n y actualizaci\u00F3n de planes de ordenamiento territorial con enfoque preventivo en la mayor\u00EDa de los municipios; mayor utilizaci\u00F3n de las microzonificaciones con fines de seguridad.";
	public static String CONTROLGENERALIZADO_TXT= "Aprobaci\u00F3n y control generalizado del cumplimiento de los planes de ordenamiento territorial que incluyen el riesgo como determinante y de las disposiciones de seguridad urbana respectivas.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return CONSIDERACIONALGUNOS_TXT;
		case 2: return PROMULGACION_TXT;
		case 3: return PROGRESIVAFORMULACION_TXT;
		case 4: return AMPLIAFORMULACION_TXT;
		case 5: return CONTROLGENERALIZADO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(CONSIDERACIONALGUNOS, getTXT(CONSIDERACIONALGUNOS)));
		res.add(new ItemConstante(PROMULGACION, getTXT(PROMULGACION)));
		res.add(new ItemConstante(PROGRESIVAFORMULACION, getTXT(PROGRESIVAFORMULACION)));
		res.add(new ItemConstante(AMPLIAFORMULACION, getTXT(AMPLIAFORMULACION)));
		res.add(new ItemConstante(CONTROLGENERALIZADO, getTXT(CONTROLGENERALIZADO)));
		
		return res;
	}
}
