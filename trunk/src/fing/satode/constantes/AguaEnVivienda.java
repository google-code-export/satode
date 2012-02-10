package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AguaEnVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int SI=1;
	public final static int SOLOTERRENO=2;
	public final static int OTRO=3;
	
		
	public static String SI_TXT= "Si";
	public static String SOLOTERRENO_TXT= "Solo se inundó el terreno";
	public static String OTRO_TXT= "Otro";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return SI_TXT;
		case 2: return SOLOTERRENO_TXT;
		case 3: return OTRO_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(SI, getTXT(SI)));
		res.add(new ItemConstante(SOLOTERRENO, getTXT(SOLOTERRENO)));
		res.add(new ItemConstante(OTRO, getTXT(OTRO)));
		
		return res;
	}
}
