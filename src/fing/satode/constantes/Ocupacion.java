package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Ocupacion implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int OCUPADAMORADORESPRESENTES=1;
	public final static int OCUPADAMORADORESAUSENTES=2;
	public final static int ENCONTRUCCION=3;
	public final static int ALQUILEROVENTA=4;
	public final static int LOCALNOVIVIENDA=5;
	public final static int OTRACONDICION=6;
	
	
	public static String OCUPADAMORADORESPRESENTES_TXT= "Ocupada con moradores presentes";
	public static String OCUPADAMORADORESAUSENTES_TXT= "Ocupada con moradores ausentes";
	public static String ENCONTRUCCION_TXT= "En contruccion";
	public static String ALQUILEROVENTA_TXT= "Alquiler o venta";
	public static String LOCALNOVIVIENDA_TXT= "Local no vivienda";
	public static String OTRACONDICION_TXT= "Otra condicion";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return OCUPADAMORADORESPRESENTES_TXT;
		case 2: return OCUPADAMORADORESAUSENTES_TXT;
		case 3: return ENCONTRUCCION_TXT;
		case 4: return ALQUILEROVENTA_TXT;
		case 5: return LOCALNOVIVIENDA_TXT;
		case 6: return OTRACONDICION_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(OCUPADAMORADORESPRESENTES, getTXT(OCUPADAMORADORESPRESENTES)));
		res.add(new ItemConstante(OCUPADAMORADORESAUSENTES, getTXT(OCUPADAMORADORESAUSENTES)));
		res.add(new ItemConstante(ENCONTRUCCION, getTXT(ENCONTRUCCION)));
		res.add(new ItemConstante(ALQUILEROVENTA, getTXT(ALQUILEROVENTA)));
		res.add(new ItemConstante(LOCALNOVIVIENDA, getTXT(LOCALNOVIVIENDA)));
		res.add(new ItemConstante(OTRACONDICION, getTXT(OTRACONDICION)));
		
		return res;
	}
}
