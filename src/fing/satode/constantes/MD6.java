package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD6 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int PLANESPOSDESASTRE=1;
	public final static int ALGUNARECUPERACION=2;
	public final static int DIAGNOSTICO=3;
	public final static int PLANESRECUPERACION=4;
	public final static int PLANESGENERALIZADOS=5;
	
			
	public static String PLANESPOSDESASTRE_TXT= "Dise\u00F1o e implementaci\u00F3n de planes de rehabilitaci\u00F3n y reconstrucci\u00F3n s\u00F3lo a posteriori de desastres importantes.";
	public static String ALGUNARECUPERACION_TXT= "Planeamiento de algunas medidas de recuperaci\u00F3n provisional por parte de entidades de servicios públicos y encargadas de la evaluaci\u00F3n de da\u00F1os en algunas ciudades.";
	public static String DIAGNOSTICO_TXT= "Procedimientos de diagn\u00F3stico, restablecimiento y reparaci\u00F3n de infraestructura y programas de proyectos productivos para la recuperaci\u00F3n de comunidades, a nivel nacional y en varias ciudades.";
	public static String PLANESRECUPERACION_TXT= "Realizaci\u00F3n ex ante de planes y programas para la recuperaci\u00F3n del tejido social, fuentes de trabajo y de medios productivos de las comunidades en la mayor\u00EDa de ciudades.";
	public static String PLANESGENERALIZADOS_TXT= "Desarrollo generalizado de planes detallados de reconstrucci\u00F3n de da\u00F1os f\u00EDsicos y recuperaci\u00F3n social con base en escenarios de riesgo; legislaci\u00F3n espec\u00EDfica y medidas anticipadas para futura activaci\u00F3n.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return PLANESPOSDESASTRE_TXT;
		case 2: return ALGUNARECUPERACION_TXT;
		case 3: return DIAGNOSTICO_TXT;
		case 4: return PLANESRECUPERACION_TXT;
		case 5: return PLANESGENERALIZADOS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(PLANESPOSDESASTRE, getTXT(PLANESPOSDESASTRE)));
		res.add(new ItemConstante(ALGUNARECUPERACION, getTXT(ALGUNARECUPERACION)));
		res.add(new ItemConstante(DIAGNOSTICO, getTXT(DIAGNOSTICO)));
		res.add(new ItemConstante(PLANESRECUPERACION, getTXT(PLANESRECUPERACION)));
		res.add(new ItemConstante(PLANESGENERALIZADOS, getTXT(PLANESGENERALIZADOS)));
		
		return res;
	}
}
