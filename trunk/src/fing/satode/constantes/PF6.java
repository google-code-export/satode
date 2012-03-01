package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF6 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int BAJOPRIVADOSASEGURADOS=1;
	public final static int REGULACIONSEGUROS=2;
	public final static int ESTUDIOSESEGURAMINETO=3;
	public final static int PROGRMASASEGURAMIENTO=4;
	public final static int PROGRAMASGOBIERNO=5;
	
	
	public static String BAJOPRIVADOSASEGURADOS_TXT= "Bajo porcentaje de bienes privados asegurados; industria de seguros incipiente, poco solvente y sin mayor regulaci\u00F3n.";
	public static String REGULACIONSEGUROS_TXT= "Regulaci\u00F3n de la industria de seguros, vigilancia de su solvencia y legislaci\u00F3n para aseguramiento del sector hipotecario y de vivienda.";
	public static String ESTUDIOSESEGURAMINETO_TXT= "Desarrollo de algunos estudios cuidadosos de aseguramiento, con base en estimaciones probabil\u00EDsticas avanzadas de riesgo, utilizando microzonificaciones; auditoria e inspecci\u00F3n id\u00F3nea de propiedades.";
	public static String PROGRMASASEGURAMIENTO_TXT= "Dise\u00F1o de programas de aseguramiento colectivo de vivienda y de peque\u00F1os negocios entre la mayor\u00EDa de gobiernos locales y las compa\u00F1\u00EDas de seguros, con cobertura autom\u00E1tica de los m\u00E1s pobres.";
	public static String PROGRAMASGOBIERNO_TXT= "Fuerte impulso de programas conjuntos entre el gobierno a las compa\u00F1\u00EDas de seguros para generar incentivos econ\u00F3micos, con el fin de promover la reducci\u00F3n del riesgo y el aseguramiento masivo.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return BAJOPRIVADOSASEGURADOS_TXT;
		case 2: return REGULACIONSEGUROS_TXT;
		case 3: return ESTUDIOSESEGURAMINETO_TXT;
		case 4: return PROGRMASASEGURAMIENTO_TXT;
		case 5: return PROGRAMASGOBIERNO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(BAJOPRIVADOSASEGURADOS, getTXT(BAJOPRIVADOSASEGURADOS)));
		res.add(new ItemConstante(REGULACIONSEGUROS, getTXT(REGULACIONSEGUROS)));
		res.add(new ItemConstante(ESTUDIOSESEGURAMINETO, getTXT(ESTUDIOSESEGURAMINETO)));
		res.add(new ItemConstante(PROGRMASASEGURAMIENTO, getTXT(PROGRMASASEGURAMIENTO)));
		res.add(new ItemConstante(PROGRAMASGOBIERNO, getTXT(PROGRAMASGOBIERNO)));
		
		return res;
	}
}
