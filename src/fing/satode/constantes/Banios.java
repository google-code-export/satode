package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Banios implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int ADENTRO=1;
	public final static int AFUERA=2;
	public final static int NOTIENE=3;
	
		
	public static String ADENTRO_TXT= "Adentro";
	public static String AFUERA_TXT= "Afuera";
	public static String NOTIENE_TXT= "No tiene";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return ADENTRO_TXT;
		case 2: return AFUERA_TXT;
		case 3: return NOTIENE_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(ADENTRO, getTXT(ADENTRO)));
		res.add(new ItemConstante(AFUERA, getTXT(AFUERA)));
		res.add(new ItemConstante(NOTIENE, getTXT(NOTIENE)));
		
		return res;
	}
}
