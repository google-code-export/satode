package fing.satode.constantes;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MaterialTecho implements Serializable, IsSerializable{

	private static final long serialVersionUID = 1L;

	public final static int LOSAHORMIGONCONPROTECCION=1;
	public final static int LOSAHORMIGONSINPROTECCION=2;
	public final static int CHAPACONCIELORRASO=3;
	public final static int CHAPASINCIELORRASO=4;
	public final static int PAJA=5;
	public final static int MATERIALDEDESECHO=6;
	public final static int OTROS=7;
	
	
	public static String LOSAHORMIGONCONPROTECCION_TXT= "Losa de hormig\u00F3n con protecci\u00F3n";
	public static String LOSAHORMIGONSINPROTECCION_TXT= "Losa de hormig\u00F3n sin protecci\u00F3n";
	public static String CHAPACONCIELORRASO_TXT= "Chapas de zinc con cielorraso";
	public static String CHAPASINCIELORRASO_TXT= "Chapas de zinc con cielorraso";
	public static String PAJA_TXT= "Paja";
	public static String MATERIALDEDESECHO_TXT= "Material de desecho";
	public static String OTROS_TXT= "Otro";
	
	public static String getTXT(int constante){
		switch (constante) {
		case 1: return LOSAHORMIGONCONPROTECCION_TXT;
		case 2: return LOSAHORMIGONSINPROTECCION_TXT;
		case 3: return CHAPACONCIELORRASO_TXT;
		case 4: return CHAPASINCIELORRASO_TXT;
		case 5: return PAJA_TXT;
		case 6: return MATERIALDEDESECHO_TXT;
		case 7: return OTROS_TXT;
		default:
			return "";
		}
	}
	
	public static ArrayList<ItemConstante> getItems(){
		ArrayList<ItemConstante> res= new ArrayList<ItemConstante>();
		res.add(new ItemConstante(LOSAHORMIGONCONPROTECCION, getTXT(LOSAHORMIGONCONPROTECCION)));
		res.add(new ItemConstante(LOSAHORMIGONSINPROTECCION, getTXT(LOSAHORMIGONSINPROTECCION)));
		res.add(new ItemConstante(CHAPACONCIELORRASO, getTXT(CHAPACONCIELORRASO)));
		res.add(new ItemConstante(CHAPASINCIELORRASO, getTXT(CHAPASINCIELORRASO)));
		res.add(new ItemConstante(PAJA, getTXT(PAJA)));
		res.add(new ItemConstante(MATERIALDEDESECHO, getTXT(MATERIALDEDESECHO)));
		res.add(new ItemConstante(OTROS, getTXT(OTROS)));
		
		return res;
	}
}
