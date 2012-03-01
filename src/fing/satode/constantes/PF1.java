package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PF1 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int ORGANIZACIONBASICA=1;
	public final static int LEGISLACION=2;
	public final static int SISTEMASINTERINTITUCIONALES=3;
	public final static int EJECUCIONCONTINUA=4;
	public final static int PERSONALEXPERTO=5;
	

	public static String ORGANIZACIONBASICA_TXT= "Organizaci\u00F3n b\u00E1sica de entidades a nivel nacional en comisiones y con un enfoque principalmente de respuesta a emergencias.";
	public static String LEGISLACION_TXT= "Legislaci\u00F3n que establece una organizaci\u00F3n descentralizada para gesti\u00F3n integral de riesgos, interinstitucional y multisectorial, y la formulaci\u00F3n de un plan general de gesti\u00F3n de riesgos.";
	public static String SISTEMASINTERINTITUCIONALES_TXT= "Sistemas interinstitucionales de gesti\u00F3n de riesgo activos a nivel local en varias ciudades; trabajo interministerial a nivel nacional para dise\u00F1o de pol\u00EDticas públicas sobre reducci\u00F3n de vulnerabilidad.";
	public static String EJECUCIONCONTINUA_TXT= "Ejecuci\u00F3n continua de proyectos de gesti\u00F3n de riesgos asociados con programas de adaptaci\u00F3n al cambio clim\u00E1tico, protecci\u00F3n ambiental, energ\u00EDa, saneamiento y reducci\u00F3n de la pobreza.";
	public static String PERSONALEXPERTO_TXT= "Personal experto con amplia experiencia incorporando la gesti\u00F3n de riesgos en la planificaci\u00F3n del desarrollo humano sostenible en la mayor\u00EDa de ciudades; sistemas de informaci\u00F3n de alta tecnolog\u00EDa.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return ORGANIZACIONBASICA_TXT;
		case 2: return LEGISLACION_TXT;
		case 3: return SISTEMASINTERINTITUCIONALES_TXT;
		case 4: return EJECUCIONCONTINUA_TXT;
		case 5: return PERSONALEXPERTO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(ORGANIZACIONBASICA, getTXT(ORGANIZACIONBASICA)));
		res.add(new ItemConstante(LEGISLACION, getTXT(LEGISLACION)));
		res.add(new ItemConstante(SISTEMASINTERINTITUCIONALES, getTXT(SISTEMASINTERINTITUCIONALES)));
		res.add(new ItemConstante(EJECUCIONCONTINUA, getTXT(EJECUCIONCONTINUA)));
		res.add(new ItemConstante(PERSONALEXPERTO, getTXT(PERSONALEXPERTO)));
		
		return res;
	}
}
