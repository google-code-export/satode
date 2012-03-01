package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR5 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INFORMACIONESPORADICA=1;
	public final static int DIVULGACIONPRENSA=2;
	public final static int PROGRAMASOPINION=3;
	public final static int DIVULGACIONGENERALIZADA=4;
	public final static int AMPLIAPARTICIPACION=5;
	
	
	public static String INFORMACIONESPORADICA_TXT= "Informaci\u00F3n espor\u00E1dica sobre gesti\u00F3n de riesgos en condiciones de normalidad y m\u00E1s frecuentemente en caso de desastres.";
	public static String DIVULGACIONPRENSA_TXT= "Divulgaci\u00F3n en prensa y emisi\u00F3n de programas de radio y TV orientados hacia la preparaci\u00F3n en caso de emergencia; producci\u00F3n de materiales ilustrativos sobre fen\u00F3menos peligrosos.";
	public static String PROGRAMASOPINION_TXT= "Frecuente realizaci\u00F3n de programas de opini\u00F3n en los medios sobre gesti\u00F3n de riesgos a nivel nacional y local; gu\u00EDas para la reducci\u00F3n de vulnerabilidad; trabajo con comunidades y con ONGs.";
	public static String DIVULGACIONGENERALIZADA_TXT= "Divulgaci\u00F3n generalizada y progresiva toma de conciencia; conformaci\u00F3n de algunas redes sociales de protecci\u00F3n civil y de ONGs que promueven expl\u00EDcitamente la gesti\u00F3n local del riesgo.";
	public static String AMPLIAPARTICIPACION_TXT= "Amplia participaci\u00F3n y apoyo del sector privado a las actividades de divulgaci\u00F3n; consolidaci\u00F3n de redes sociales y participaci\u00F3n notable de profesionales y de ONGs en todos los niveles.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INFORMACIONESPORADICA_TXT;
		case 2: return DIVULGACIONPRENSA_TXT;
		case 3: return PROGRAMASOPINION_TXT;
		case 4: return DIVULGACIONGENERALIZADA_TXT;
		case 5: return AMPLIAPARTICIPACION_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INFORMACIONESPORADICA, getTXT(INFORMACIONESPORADICA)));
		res.add(new ItemConstante(DIVULGACIONPRENSA, getTXT(DIVULGACIONPRENSA)));
		res.add(new ItemConstante(PROGRAMASOPINION, getTXT(PROGRAMASOPINION)));
		res.add(new ItemConstante(DIVULGACIONGENERALIZADA, getTXT(DIVULGACIONGENERALIZADA)));
		res.add(new ItemConstante(AMPLIAPARTICIPACION, getTXT(AMPLIAPARTICIPACION)));
		
		return res;
	}
}
