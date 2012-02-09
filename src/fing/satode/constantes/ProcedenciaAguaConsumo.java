package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProcedenciaAguaConsumo implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;
	
	public final static int REDGENERAL=1;
	public final static int POZOSURGENTE=2;
	public final static int ALJIBECACHIMBA=3;
	public final static int OTROS=4;
	
	
	public static String REDGENERAL_TXT= "Red General";
	public static String POZOSURGENTE_TXT= "Pozo surgente";
	public static String ALJIBECACHIMBA_TXT= "Aljibe, cachimba";
	public static String OTROS_TXT= "Otro";
	
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return REDGENERAL_TXT;
		case 2: return POZOSURGENTE_TXT;
		case 3: return ALJIBECACHIMBA_TXT;
		case 4: return OTROS_TXT;
		
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(REDGENERAL, getTXT(REDGENERAL)));
		res.add(new ItemConstante(POZOSURGENTE, getTXT(POZOSURGENTE)));
		res.add(new ItemConstante(ALJIBECACHIMBA, getTXT(ALJIBECACHIMBA)));
		res.add(new ItemConstante(OTROS, getTXT(OTROS)));
		
		return res;
	}
}
