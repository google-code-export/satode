package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IR3 implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int EVALUACIONSUFERFICIAL=1;
	public final static int ALGUNOSESTUDIOS=2;
	public final static int ALGUNOSMAPAS=3;
	public final static int BASEMETODOLOGICA=4;
	public final static int ESTUDIOSDETALLADOS=5;
	
		
	public static String EVALUACIONSUFERFICIAL_TXT= "Evaluaci\u00F3n superficial y realizaci\u00F3n de mapas b\u00E1sicos de la influencia y susceptibilidad de algunos fen\u00F3menos.";
	public static String ALGUNOSESTUDIOS_TXT= "Algunos estudios descriptivos y cualitativos de susceptibilidad y amenaza de los principales fen\u00F3menos a escala nacional y en algunos sitios espec\u00EDficos.";
	public static String ALGUNOSMAPAS_TXT= "Algunos mapas de amenaza, basados en t\u00E9cnicas probabil\u00EDsticas, para el nivel nacional y para algunas regiones; uso generalizado de SIG para el mapeo de las principales amenazas.";
	public static String BASEMETODOLOGICA_TXT= "Evaluaciones con base en metodolog\u00EDas avanzadas y de adecuada resoluci\u00F3n para la mayor\u00EDa de las amenazas; microzonificaci\u00F3n de algunas ciudades con base en t\u00E9cnicas probabil\u00EDsticas.";
	public static String ESTUDIOSDETALLADOS_TXT= "Estudios detallados de la mayor\u00EDa de los fen\u00F3menos potenciales en todo el territorio; microzonificaci\u00F3n de la mayor\u00EDa de ciudades y mapas de amenaza a nivel subnacional y municipal.";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return EVALUACIONSUFERFICIAL_TXT;
		case 2: return ALGUNOSESTUDIOS_TXT;
		case 3: return ALGUNOSMAPAS_TXT;
		case 4: return BASEMETODOLOGICA_TXT;
		case 5: return ESTUDIOSDETALLADOS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(EVALUACIONSUFERFICIAL, getTXT(EVALUACIONSUFERFICIAL)));
		res.add(new ItemConstante(ALGUNOSESTUDIOS, getTXT(ALGUNOSESTUDIOS)));
		res.add(new ItemConstante(ALGUNOSMAPAS, getTXT(ALGUNOSMAPAS)));
		res.add(new ItemConstante(BASEMETODOLOGICA, getTXT(BASEMETODOLOGICA)));
		res.add(new ItemConstante(ESTUDIOSDETALLADOS, getTXT(ESTUDIOSDETALLADOS)));
		
		return res;
	}
}
