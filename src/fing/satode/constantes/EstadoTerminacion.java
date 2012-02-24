package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoTerminacion implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int TERMINADA=1;
	public final static int FALTADETALLES=2;
	public final static int CONCARENCIAS=3;
	public final static int VIVIENDAENOBRA=4;
	
	
	public static String TERMINADA_TXT= "Terminada";
	public static String FALTADETALLES_TXT= "Faltan detalles de terminaci\u00F3n";
	public static String CONCARENCIAS_TXT= "con carencias de terminaci\u00F3n";
	public static String VIVIENDAENOBRA_TXT= "vivienda en obra";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return TERMINADA_TXT;
		case 2: return FALTADETALLES_TXT;
		case 3: return CONCARENCIAS_TXT;
		case 4: return VIVIENDAENOBRA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(TERMINADA, getTXT(TERMINADA)));
		res.add(new ItemConstante(FALTADETALLES, getTXT(FALTADETALLES)));
		res.add(new ItemConstante(CONCARENCIAS, getTXT(CONCARENCIAS)));
		res.add(new ItemConstante(VIVIENDAENOBRA, getTXT(VIVIENDAENOBRA)));
		
		return res;
	}
}
