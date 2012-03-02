package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RR2 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INVENTARIOALGUNOS=1;
	public final static int EXPEDICIONDISPOSICIONES=2;
	public final static int PLANESORDENAMINETO=3;
	public final static int INVENTARIOSAPRECIABLES=4;
	public final static int CONSIDERABLESINVENTARIOS=5;
	
		
	public static String INVENTARIOALGUNOS_TXT= "Inventario de cuencas y zonas de mayor deterioro ambiental o consideradas de mayor sensitividad.";
	public static String EXPEDICIONDISPOSICIONES_TXT= "Expedici\u00F3n de disposiciones legales de orden nacional y de algunas de nivel local que establecen la obligatoriedad de reforestaci\u00F3n, protecci\u00F3n ambiental y ordenamiento de cuencas.";
	public static String PLANESORDENAMINETO_TXT= "Formulaci\u00F3n de algunos planes de ordenamiento e intervenci\u00F3n de cuencas hidrogr\u00E1ficas estrat\u00E9gicas y de zonas sensitivas, teniendo en cuenta aspectos relacionados con la vulnerabilidad y el riesgo.";
	public static String INVENTARIOSAPRECIABLES_TXT= "Apreciable n\u00FAmero de regiones/cuencas con planes de protecci\u00F3n ambiental, estudios de impacto y ordenamiento de zonas agr\u00EDcolas, que consideran el riesgo como determinante para la intervenci\u00F3n.";
	public static String CONSIDERABLESINVENTARIOS_TXT= "Intervenci\u00F3n de un n\u00FAmero considerable de cuencas deterioradas y de zonas sensitivas y ecosistemas estrat\u00E9gicos; la mayor\u00EDa de los municipios con planes de intervenci\u00F3n y protecci\u00F3n ambiental.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INVENTARIOALGUNOS_TXT;
		case 2: return EXPEDICIONDISPOSICIONES_TXT;
		case 3: return PLANESORDENAMINETO_TXT;
		case 4: return INVENTARIOSAPRECIABLES_TXT;
		case 5: return CONSIDERABLESINVENTARIOS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INVENTARIOALGUNOS, getTXT(INVENTARIOALGUNOS)));
		res.add(new ItemConstante(EXPEDICIONDISPOSICIONES, getTXT(EXPEDICIONDISPOSICIONES)));
		res.add(new ItemConstante(PLANESORDENAMINETO, getTXT(PLANESORDENAMINETO)));
		res.add(new ItemConstante(INVENTARIOSAPRECIABLES, getTXT(INVENTARIOSAPRECIABLES)));
		res.add(new ItemConstante(CONSIDERABLESINVENTARIOS, getTXT(CONSIDERABLESINVENTARIOS)));
		
		return res;
	}
}
