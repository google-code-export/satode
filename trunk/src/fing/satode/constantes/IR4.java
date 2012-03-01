package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR4 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int IDENTIFICACION=1;
	public final static int ESTUDIOSGENERALES=2;
	public final static int EVALUACIONESCENARIOS=3;
	public final static int ESTUDIOSDETALLADOS=4;
	public final static int EVALUACIONGENERALIZADA=5;
	
	
	public static String IDENTIFICACION_TXT= "Identificaci\u00F3n y mapeo de los principales elementos expuestos en zonas propensas en las principales ciudades y cuencas hidrogr\u00E1ficas.";
	public static String ESTUDIOSGENERALES_TXT= "Estudios generales de vulnerabilidad f\u00EDsica ante las amenazas m\u00E1s reconocidas, utilizando Sistemas de Informaci\u00F3n Geogr\u00E1fica en algunas ciudades y cuencas.";
	public static String EVALUACIONESCENARIOS_TXT= "Evaluaci\u00F3n de escenarios de daños y p\u00E9rdidas potenciales ante algunos fen\u00F3menos peligrosos en las principales ciudades; an\u00E1lisis de la vulnerabilidad f\u00EDsica de algunos edificios esenciales.";
	public static String ESTUDIOSDETALLADOS_TXT= "Estudios detallados de riesgo, utilizando t\u00E9cnicas probabil\u00EDsticas, teniendo en cuenta el impacto econ\u00F3mico y social de la mayor\u00EDa de las amenazas en algunas ciudades; an\u00E1lisis de la vulnerabilidad de la mayor\u00EDa de edificios esenciales y de algunas l\u00EDneas vitales.";
	public static String EVALUACIONGENERALIZADA_TXT= "Evaluaci\u00F3n generalizada de riesgo, considerando factores f\u00EDsicos, sociales, culturales y ambientales; an\u00E1lisis de la vulnerabilidad tambi\u00E9n de edificios privados y de la mayor\u00EDa de las l\u00EDneas vitales.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return IDENTIFICACION_TXT;
		case 2: return ESTUDIOSGENERALES_TXT;
		case 3: return EVALUACIONESCENARIOS_TXT;
		case 4: return ESTUDIOSDETALLADOS_TXT;
		case 5: return EVALUACIONGENERALIZADA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(IDENTIFICACION, getTXT(IDENTIFICACION)));
		res.add(new ItemConstante(ESTUDIOSGENERALES, getTXT(ESTUDIOSGENERALES)));
		res.add(new ItemConstante(EVALUACIONESCENARIOS, getTXT(EVALUACIONESCENARIOS)));
		res.add(new ItemConstante(ESTUDIOSDETALLADOS, getTXT(ESTUDIOSDETALLADOS)));
		res.add(new ItemConstante(EVALUACIONGENERALIZADA, getTXT(EVALUACIONGENERALIZADA)));
		
		return res;
	}
}
