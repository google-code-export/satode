package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersepcionVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int BUENLUGAR=1;
	public final static int REGULAR=2;
	public final static int MALO=3;
	
		
	public static String BUENLUGAR_TXT= "Un buen lugar para vivir";
	public static String REGULAR_TXT= "Un lugar regular para vivir";
	public static String MALO_TXT= "Un mal lugar para vivir";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return BUENLUGAR_TXT;
		case 2: return REGULAR_TXT;
		case 3: return MALO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(BUENLUGAR, getTXT(BUENLUGAR)));
		res.add(new ItemConstante(REGULAR, getTXT(REGULAR)));
		res.add(new ItemConstante(MALO, getTXT(MALO)));
		
		return res;
	}
}
