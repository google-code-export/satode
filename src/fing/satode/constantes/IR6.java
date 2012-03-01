package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR6 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INCIPIENTEINCORPORACION=1;
	public final static int ALGUNASADECUACIONES=2;
	public final static int PROGRESIVAINCORPORACION=3;
	public final static int AMPLIAADECUACION=4;
	public final static int ADECUACIONCURRICULAR=5;
	
		
	public static String INCIPIENTEINCORPORACION_TXT= "Incipiente incorporaci\u00F3n de temas sobre amenazas y desastres en la educaci\u00F3n formal y en programas de capacitaci\u00F3n comunitaria.";
	public static String ALGUNASADECUACIONES_TXT= "Algunas adecuaciones curriculares puntuales en la educaci\u00F3n b\u00E1sica y media; producci\u00F3n de materiales de instrucci\u00F3n para docentes y l\u00EDderes comunitarios en algunos lugares del pa\u00EDs.";
	public static String PROGRESIVAINCORPORACION_TXT= "Progresiva incorporaci\u00F3n de la gesti\u00F3n de riesgo en los programas curriculares; apreciable producci\u00F3n de materiales de instrucci\u00F3n y realizaci\u00F3n de frecuentes cursos de capacitaci\u00F3n de la comunidad.";
	public static String AMPLIAADECUACION_TXT= "Ampliaci\u00F3n de la adecuaci\u00F3n curricular a los programas de educaci\u00F3n superior; ofrecimiento de cursos de especializaci\u00F3n en varias universidades; amplia capacitaci\u00F3n comunitaria a nivel local.";
	public static String ADECUACIONCURRICULAR_TXT= "Adecuaci\u00F3n curricular generalizada en todo el territorio y en todas las etapas de la educaci\u00F3n; amplia producci\u00F3n de materiales de instrucci\u00F3n; permanente capacitaci\u00F3n de la comunidad.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INCIPIENTEINCORPORACION_TXT;
		case 2: return ALGUNASADECUACIONES_TXT;
		case 3: return PROGRESIVAINCORPORACION_TXT;
		case 4: return AMPLIAADECUACION_TXT;
		case 5: return ADECUACIONCURRICULAR_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INCIPIENTEINCORPORACION, getTXT(INCIPIENTEINCORPORACION)));
		res.add(new ItemConstante(ALGUNASADECUACIONES, getTXT(ALGUNASADECUACIONES)));
		res.add(new ItemConstante(PROGRESIVAINCORPORACION, getTXT(PROGRESIVAINCORPORACION)));
		res.add(new ItemConstante(AMPLIAADECUACION, getTXT(AMPLIAADECUACION)));
		res.add(new ItemConstante(ADECUACIONCURRICULAR, getTXT(ADECUACIONCURRICULAR)));
		
		return res;
	}
}
