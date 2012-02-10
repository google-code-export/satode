package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AlojamientoInundacion implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int INSTITUCION=1;
	public final static int FAMILIAR=2;
	public final static int OTRO=3;
	
		
	public static String INSTITUCION_TXT= "Institución";
	public static String FAMILIAR_TXT= "Casa de un familiar.";
	public static String OTRO_TXT= "Otro";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return INSTITUCION_TXT;
		case 2: return FAMILIAR_TXT;
		case 3: return OTRO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(INSTITUCION, getTXT(INSTITUCION)));
		res.add(new ItemConstante(FAMILIAR, getTXT(FAMILIAR)));
		res.add(new ItemConstante(OTRO, getTXT(OTRO)));
		
		return res;
	}
}
