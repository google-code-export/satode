package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TipoIndice implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int IDL=1;
	public final static int IGR=2;
	
	public static String IDL_TXT="INDICE IDL";
	public static String IGR_TXT="INDICE IGR";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return IDL_TXT;
		case 2: return IGR_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(IDL, getTXT(IDL)));
		res.add(new ItemConstante(IGR, getTXT(IGR)));
		
		return res;
	}

}
