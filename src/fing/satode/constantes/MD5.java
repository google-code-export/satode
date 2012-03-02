package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MD5 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int REUNIONESINFORMATIVAS=1;
	public final static int CURSOSEPORADICOS=2;
	public final static int ACTIVIDADESREGULARES=3;
	public final static int CURSOSFRECUENTES=4;
	public final static int CURSOSPERMANENTES=5;
	
			
	public static String REUNIONESINFORMATIVAS_TXT= "Reuniones informativas con comunidades para ilustrar qu\u00E9 se debe hacer en emergencia, usualmente cuando ocurren desastres.";
	public static String CURSOSEPORADICOS_TXT= "Cursos espor\u00E1dicos de capacitaci\u00F3n con organizaciones de la sociedad, con el fin de tratar temas relacionados con desastres.";
	public static String ACTIVIDADESREGULARES_TXT= "Realizaci\u00F3n de cursos frecuentes con comunidades en la mayor\u00EDa de ciudades y municipios sobre preparativos, prevenci\u00F3n y reducci\u00F3n de riesgos.";
	public static String CURSOSFRECUENTES_TXT= "Coordinaci\u00F3n permanente para responder en caso de emergencia entre las entidades operativas, de servicios p\u00FAblicos, las autoridades locales y organismos de la sociedad civil en la mayor\u00EDa de ciudades.";
	public static String CURSOSPERMANENTES_TXT= "Cursos permanentes de prevenci\u00F3n y atenci\u00F3n de desastres en todos los municipios dentro de la programaci\u00F3n de capacitaci\u00F3n en desarrollo comunitario en coordinaci\u00F3n con otras entidades y ONGs.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return REUNIONESINFORMATIVAS_TXT;
		case 2: return CURSOSEPORADICOS_TXT;
		case 3: return ACTIVIDADESREGULARES_TXT;
		case 4: return CURSOSFRECUENTES_TXT;
		case 5: return CURSOSPERMANENTES_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(REUNIONESINFORMATIVAS, getTXT(REUNIONESINFORMATIVAS)));
		res.add(new ItemConstante(CURSOSEPORADICOS, getTXT(CURSOSEPORADICOS)));
		res.add(new ItemConstante(ACTIVIDADESREGULARES, getTXT(ACTIVIDADESREGULARES)));
		res.add(new ItemConstante(CURSOSFRECUENTES, getTXT(CURSOSFRECUENTES)));
		res.add(new ItemConstante(CURSOSPERMANENTES, getTXT(CURSOSPERMANENTES)));
		
		return res;
	}
}
