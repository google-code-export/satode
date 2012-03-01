package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR4 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int IDENTIFICACIONASENTAMINETOS=1;
	public final static int EXPEDICIONLEGISLACION=2;
	public final static int PROGRAMAMEJORAMINETO=3;
	public final static int PROGRESIVAINTERVENCION=4;
	public final static int NOTABLECONTROL=5;
	
	
	
	public static String IDENTIFICACIONASENTAMINETOS_TXT= "Identificaci\u00F3n e inventario de asentamientos humanos marginales y localizados en \u00E1reas propensas.";
	public static String EXPEDICIONLEGISLACION_TXT= "Expedici\u00F3n de legislaci\u00F3n sobre tratamiento prioritario de \u00E1reas urbanas deterioradas y en riesgo para programas de mejoramiento y desarrollo de vivienda de inter\u00E9s social.";
	public static String PROGRAMAMEJORAMINETO_TXT= "Programas de mejoramiento del entorno, de vivienda existente y de reubicaci\u00F3n por riesgo en las principales ciudades.";
	public static String PROGRESIVAINTERVENCION_TXT= "Progresiva intervenci\u00F3n de asentamientos humanos en riesgo en la mayor\u00EDa de las ciudades y adecuado tratamiento de las \u00E1reas desalojadas.";
	public static String NOTABLECONTROL_TXT= "Notable control de las \u00E1reas de riesgo en todas las ciudades y reubicaci\u00F3n de la mayor\u00EDa de las viviendas construidas en zonas de riesgo no mitigable.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return IDENTIFICACIONASENTAMINETOS_TXT;
		case 2: return EXPEDICIONLEGISLACION_TXT;
		case 3: return PROGRAMAMEJORAMINETO_TXT;
		case 4: return PROGRESIVAINTERVENCION_TXT;
		case 5: return NOTABLECONTROL_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(IDENTIFICACIONASENTAMINETOS, getTXT(IDENTIFICACIONASENTAMINETOS)));
		res.add(new ItemConstante(EXPEDICIONLEGISLACION, getTXT(EXPEDICIONLEGISLACION)));
		res.add(new ItemConstante(PROGRAMAMEJORAMINETO, getTXT(PROGRAMAMEJORAMINETO)));
		res.add(new ItemConstante(PROGRESIVAINTERVENCION, getTXT(PROGRESIVAINTERVENCION)));
		res.add(new ItemConstante(NOTABLECONTROL, getTXT(NOTABLECONTROL)));
		
		return res;
	}
}
