package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ConservacionVivienda implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int BUENA=1;
	public final static int NECESITAREPARACIONESPEQ=2;
	public final static int NECESITAREPARACIONESMAYORES=3;
	public final static int VIVIENDARUINOSA=4;
	
	
	public static String BUENA_TXT= "Buena";
	public static String NECESITAREPARACIONESPEQ_TXT= "Necesita reparaciones pequeñas";
	public static String NECESITAREPARACIONESMAYORES_TXT= "Necesita reparaciones importantes";
	public static String VIVIENDARUINOSA_TXT= "Vivienda ruinosa";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return BUENA_TXT;
		case 2: return NECESITAREPARACIONESPEQ_TXT;
		case 3: return NECESITAREPARACIONESMAYORES_TXT;
		case 4: return VIVIENDARUINOSA_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(BUENA, getTXT(BUENA)));
		res.add(new ItemConstante(NECESITAREPARACIONESPEQ, getTXT(NECESITAREPARACIONESPEQ)));
		res.add(new ItemConstante(NECESITAREPARACIONESMAYORES, getTXT(NECESITAREPARACIONESMAYORES)));
		res.add(new ItemConstante(VIVIENDARUINOSA, getTXT(VIVIENDARUINOSA)));
		
		return res;
	}
}
