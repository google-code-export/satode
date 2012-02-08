package fing.satode.constantes;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Unidad implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public static int CENTIMETROS=1;
	public static int METROS=2;
	public static int MILIGRAMOS=3;
	public static int GRAMO=4;
	public static int KILOGRAMO=5;
	public static int UNIDAD=6;
	
	private static String METROS_TXT="Metros";
	private static String CENTIMETROS_TXT="Centimetros";
	private static String MILIGRAMOS_TXT="Miligramos";
	private static String GRAMO_TXT="Gramos";
	private static String KILOGRAMO_TXT="Kilo";
	private static String UNIDAD_TXT="Unidad";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return CENTIMETROS_TXT;
		case 2: return METROS_TXT;
		case 3: return MILIGRAMOS_TXT;
		case 4: return GRAMO_TXT;
		case 5: return KILOGRAMO_TXT;
		case 6: return UNIDAD_TXT;
		default:
			return "";
		}
	}
}
