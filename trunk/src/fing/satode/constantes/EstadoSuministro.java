package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EstadoSuministro implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int BUENO=1;
	public final static int INTERMENDIO=2;
	public final static int MALO=3;
	
	public static String BUENO_TXT="Bueno";
	public static String INTERMEDIO_TXT="Intermedio";
	public static String MALO_TXT="MALO";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return BUENO_TXT;
		case 2: return INTERMEDIO_TXT;
		case 3: return MALO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(BUENO, getTXT(BUENO)));
		res.add(new ItemConstante(INTERMENDIO, getTXT(INTERMENDIO)));
		res.add(new ItemConstante(MALO, getTXT(MALO)));
		return res;
	}
}
