package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF4 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int SUBVENCIONESESPORADIOAS=1;
	public final static int FONDOINVERSION=2;
	public final static int REDESSOCIALES=3;
	public final static int PROGRAMASREGULARES=4;
	public final static int DESARROLLOGENERALIZADO=5;
	
	
	public static String SUBVENCIONESESPORADIOAS_TXT= "Subvenciones espor\u00E1dicas a comunidades afectadas por desastres o en situaciones cr\u00EDticas de riesgo.";
	public static String FONDOINVERSION_TXT= "Constituci\u00F3n de fondos de inversi\u00F3n social permanentes para el apoyo de comunidades vulnerables con focalizaci\u00F3n en los estratos socio-econ\u00F3micos m\u00E1s pobres.";
	public static String REDESSOCIALES_TXT= "Redes sociales para autoprotecci\u00F3n de los medios de sustento de comunidades en riesgo y realizaci\u00F3n de proyectos productivos de rehabilitaci\u00F3n y recuperaci\u00F3n pos desastre.";
	public static String PROGRAMASREGULARES_TXT= "Programas regulares de microcr\u00E9dito y actividades de g\u00E9nero orientadas a la reducci\u00F3n de la vulnerabilidad humana.";
	public static String DESARROLLOGENERALIZADO_TXT= "Desarrollo generalizado de programas de protecci\u00F3n social y reducci\u00F3n de la pobreza integrados con actividades de mitigaci\u00F3n y prevenci\u00F3n en todo el territorio.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return SUBVENCIONESESPORADIOAS_TXT;
		case 2: return FONDOINVERSION_TXT;
		case 3: return REDESSOCIALES_TXT;
		case 4: return PROGRAMASREGULARES_TXT;
		case 5: return DESARROLLOGENERALIZADO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(SUBVENCIONESESPORADIOAS, getTXT(SUBVENCIONESESPORADIOAS)));
		res.add(new ItemConstante(FONDOINVERSION, getTXT(FONDOINVERSION)));
		res.add(new ItemConstante(REDESSOCIALES, getTXT(REDESSOCIALES)));
		res.add(new ItemConstante(PROGRAMASREGULARES, getTXT(PROGRAMASREGULARES)));
		res.add(new ItemConstante(DESARROLLOGENERALIZADO, getTXT(DESARROLLOGENERALIZADO)));
		
		return res;
	}
}
