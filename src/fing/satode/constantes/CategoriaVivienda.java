package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CategoriaVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;
	
	public final static int SUNTUARIA=1;
	public final static int CONFORTABLE=2;
	public final static int BUENA=3;
	public final static int ECONOMICA=4;
	public final static int PRECARIA=5;
	
	public static String SUNTUARIA_TXT= "Suntuaria";
	public static String CONFORTABLE_TXT= "Confortable";
	public static String BUENA_TXT= "Buena";
	public static String ECONOMICA_TXT= "Econ\u00F3mica";
	public static String PRECARIA_TXT= "Precaria";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return SUNTUARIA_TXT;
		case 2: return CONFORTABLE_TXT;
		case 3: return BUENA_TXT;
		case 4: return ECONOMICA_TXT;
		case 5: return PRECARIA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(SUNTUARIA, getTXT(SUNTUARIA)));
		res.add(new ItemConstante(CONFORTABLE, getTXT(CONFORTABLE)));
		res.add(new ItemConstante(BUENA, getTXT(BUENA)));
		res.add(new ItemConstante(ECONOMICA, getTXT(ECONOMICA)));
		res.add(new ItemConstante(PRECARIA, getTXT(PRECARIA)));
		
		return res;
	}
}
