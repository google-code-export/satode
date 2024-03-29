package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MaterialPiso implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int MADERA=1;
	public final static int BALDOSA=2;
	public final static int MONOLITICO=3;
	public final static int HORMIGON=4;
	public final static int LADRILLO=5;
	public final static int TIERRAOCASCOTE=6;
	public final static int OTROS=7;
	
	
	public static String MADERA_TXT= "Madera";
	public static String BALDOSA_TXT= "Baldosa";
	public static String MONOLITICO_TXT= "Monol\u00EDtico";
	public static String HORMIGON_TXT= "Hormig\u00F3n";
	public static String LADRILLO_TXT= "Ladrillo";
	public static String TIERRAOCASCOTEO_TXT= "Tierra o cascote";
	public static String OTROS_TXT= "Otro";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return MADERA_TXT;
		case 2: return BALDOSA_TXT;
		case 3: return MONOLITICO_TXT;
		case 4: return HORMIGON_TXT;
		case 5: return LADRILLO_TXT;
		case 6: return TIERRAOCASCOTEO_TXT;
		case 7: return OTROS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(MADERA, getTXT(MADERA)));
		res.add(new ItemConstante(BALDOSA, getTXT(BALDOSA)));
		res.add(new ItemConstante(MONOLITICO, getTXT(MONOLITICO)));
		res.add(new ItemConstante(HORMIGON, getTXT(HORMIGON)));
		res.add(new ItemConstante(LADRILLO, getTXT(LADRILLO)));
		res.add(new ItemConstante(TIERRAOCASCOTE, getTXT(TIERRAOCASCOTE)));
		res.add(new ItemConstante(OTROS, getTXT(OTROS)));
		
		return res;
	}
}
