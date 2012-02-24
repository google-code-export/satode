package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Sanitaria implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int REDGENERAL=1;
	public final static int FOSASEPTICA=2;
	public final static int ENSUPERFICIE=3;
	public final static int OTROS=4;
	
	
	public static String REDGENERAL_TXT= "Red General";
	public static String FOSASEPTICA_TXT= "Fosa s\u00E9ptica- Pozo negro ";
	public static String ENSUPERFICIE_TXT= "En superficie";
	public static String OTROS_TXT= "Otro";
	
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return REDGENERAL_TXT;
		case 2: return FOSASEPTICA_TXT;
		case 3: return ENSUPERFICIE_TXT;
		case 4: return OTROS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(REDGENERAL, getTXT(REDGENERAL)));
		res.add(new ItemConstante(FOSASEPTICA, getTXT(FOSASEPTICA)));
		res.add(new ItemConstante(ENSUPERFICIE, getTXT(ENSUPERFICIE)));
		res.add(new ItemConstante(OTROS, getTXT(OTROS)));
		
		return res;
	}
}
