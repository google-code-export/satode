package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Mudarse implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int MEQUEDO=1;
	public final static int MEMUDO=2;
	public final static int DEPENDE=3;
	
		
	public static String MEQUEDO_TXT= "Me quedo aqu\u00ED";
	public static String MEMUDO_TXT= "Me mudo";
	public static String DEPENDE_TXT= "Depende";
		
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return MEQUEDO_TXT;
		case 2: return MEMUDO_TXT;
		case 3: return DEPENDE_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(MEQUEDO, getTXT(MEQUEDO)));
		res.add(new ItemConstante(MEMUDO, getTXT(MEMUDO)));
		res.add(new ItemConstante(DEPENDE, getTXT(DEPENDE)));
		
		return res;
	}
}
