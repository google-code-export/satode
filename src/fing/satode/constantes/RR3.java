package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR3 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int ALGUNOSCONTROLES=1;
	public final static int OBRASCANALIZACION=2;
	public final static int REGLAMENTACION=3;
	public final static int AMPLIAINTERVENCION=4;
	public final static int AMPLIOSCONTROLES=5;
	
	
	public static String ALGUNOSCONTROLES_TXT= "Algunas medidas estructurales de control y estabilidad en algunos lugares de mayor incidencia y peligro.";
	public static String OBRASCANALIZACION_TXT= "Obras de canalizaci\u00F3n, saneamiento y tratamiento de aguas en la mayor\u00EDa de las ciudades, construidas con criterios de seguridad.";
	public static String REGLAMENTACION_TXT= "Establecimiento de medidas y reglamentaciones para el dise\u00F1o y construcci\u00F3n de obras de protecci\u00F3n y control de amenazas en armon\u00EDa con las disposiciones de ordenamiento territorial.";
	public static String AMPLIAINTERVENCION_TXT= "Amplia intervenci\u00F3n de zonas de riesgo mitigable mediante obras de protecci\u00F3n y control en las principales ciudades que lo requieren.";
	public static String AMPLIOSCONTROLES_TXT= "Adecuado dise\u00F1o y construcci\u00F3n de obras de amortiguamiento estabilidad, disipaci\u00F3n y control en la mayor\u00EDa de ciudades con fines de protecci\u00F3n de asentamientos humanos e inversiones sociales.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return ALGUNOSCONTROLES_TXT;
		case 2: return OBRASCANALIZACION_TXT;
		case 3: return REGLAMENTACION_TXT;
		case 4: return AMPLIAINTERVENCION_TXT;
		case 5: return AMPLIOSCONTROLES_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(ALGUNOSCONTROLES, getTXT(ALGUNOSCONTROLES)));
		res.add(new ItemConstante(OBRASCANALIZACION, getTXT(OBRASCANALIZACION)));
		res.add(new ItemConstante(REGLAMENTACION, getTXT(REGLAMENTACION)));
		res.add(new ItemConstante(AMPLIAINTERVENCION, getTXT(AMPLIAINTERVENCION)));
		res.add(new ItemConstante(AMPLIOSCONTROLES, getTXT(AMPLIOSCONTROLES)));
		
		return res;
	}
}
