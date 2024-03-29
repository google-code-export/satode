package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Energia implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int UTE=1;
	public final static int CARGADOR=2;
	public final static int OTROTIPO=3;
	public final static int NOTIENE=4;
	
	
	public static String UTE_TXT= "UTE";
	public static String CARGADOR_TXT= "Cargador";
	public static String OTROTIPO_TXT= "Otro tipo";
	public static String NOTIENE_TXT= "No tiene";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return UTE_TXT;
		case 2: return CARGADOR_TXT;
		case 3: return OTROTIPO_TXT;
		case 4: return NOTIENE_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(UTE, getTXT(UTE)));
		res.add(new ItemConstante(CARGADOR, getTXT(CARGADOR)));
		res.add(new ItemConstante(OTROTIPO, getTXT(OTROTIPO)));
		res.add(new ItemConstante(NOTIENE, getTXT(NOTIENE)));
		
		return res;
	}
}
