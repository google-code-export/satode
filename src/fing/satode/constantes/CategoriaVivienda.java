package fing.satode.constantes;

import java.io.Serializable;

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
	public static String ECONOMICA_TXT= "Económica";
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
}
